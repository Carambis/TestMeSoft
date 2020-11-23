package by.bsuir.service.impl;

import by.bsuir.dao.*;
import by.bsuir.entity.*;
import by.bsuir.service.TaskService;
import by.bsuir.service_client.security.TokenUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final CrudTaskDao crudTaskDao;
    private final TaskSequenceDao taskSequenceDao;
    private final UserAnswerDao userAnswerDao;
    private final TaskDao taskDao;
    private final RecommendationDao recommendationDao;
    private final AnswerStatisticDao answerStatisticDao;

    @Override
    public Mono<BsuirTask> getTask(String id) {
        return crudTaskDao.findByTaskRest(id);
    }

    @Override
    public Flux<TaskSequence> startTest() {
        return taskDao.findAllTestType()
                .flatMap(crudTaskDao::findByTaskType)
                .map(bsuirTasks -> {
                    TokenUser tokenUser = (TokenUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    TaskSequence taskSequenceBean = new TaskSequence();
                    taskSequenceBean.setUserId(tokenUser.getId());
                    String[] taskSeauenceArray = bsuirTasks.toArray(new String[0]);
                    taskSequenceBean.setTaskSequence(taskSeauenceArray);
                    taskSequenceBean.setStartDate(Date.from(Instant.now()));
                    return taskSequenceBean;
                }).doOnNext(taskSequenceDao::save);
    }

    @Override
    public String getNextTask() {
        TokenUser tokenUser = (TokenUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = tokenUser.getId();
        TaskSequence taskSequence = taskSequenceDao.getByUserId(userId).block();
        String[] tasks = taskSequence.getTaskSequence();
        if (tasks == null || tasks.length == 0) {
            taskSequence.setFinishDate(Date.from(Instant.now()));
            taskSequenceDao.save(taskSequence);
            return "finishTest";
        }
        String nextTask = tasks[0];
        tasks = Arrays.copyOfRange(tasks, 1, tasks.length);
        taskSequence.setTaskSequence(tasks);
        taskSequenceDao.delete(taskSequence);
        taskSequenceDao.save(taskSequence);
        return nextTask;
    }

    @Override
    public void addUserAnswer(String taskRest, String answer) {
        TokenUser tokenUser = (TokenUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = tokenUser.getId();
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setTaskRest(taskRest);
        userAnswer.setUserId(userId);
        userAnswer.setAnswer(answer);
        userAnswerDao.save(userAnswer);
    }

    @Override
    public AnswerStatistic checkUserAnswers() {
        TokenUser tokenUser = (TokenUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = tokenUser.getId();
        AnswerStatistic answerStatistic = new AnswerStatistic();
        Map<String, Integer> answerMap = new HashMap<>();
        List<String> types = taskDao.findAllTestType().collectList().block();
        for (String type : types) {
            answerMap.put(type, 0);
        }
        List<UserAnswer> userAnswers = userAnswerDao.findAllByUserId(userId).block();
        for (UserAnswer userAnswer : userAnswers) {
            String taskId = userAnswer.getTaskRest();
            BsuirTask bsuirTask = crudTaskDao.findTaskByTaskRest(taskId).block();
            if (userAnswer.getAnswer().equals(bsuirTask.getRightAnswer())) {
                answerMap.computeIfPresent(bsuirTask.getTaskType(), (k, v) -> v + 1);
            }
        }
        List<TypeTaskResult> taskResults = new ArrayList<>();
        for (String type : types) {
            TypeTaskResult typeTaskResult = new TypeTaskResult();
            typeTaskResult.setTypeTest(type);
            typeTaskResult.setAllCount(crudTaskDao.countByTaskType(type).block());
            typeTaskResult.setCountRight(answerMap.get(type));
            taskResults.add(typeTaskResult);
        }

        answerStatistic.setResults(taskResults.toArray(new TypeTaskResult[0]));
        getRecommendation(taskResults);

        TaskSequence taskSequence = taskSequenceDao.getByUserId(userId).block();

        long duration = taskSequence.getFinishDate().getTime() - taskSequence.getStartDate().getTime();
        duration = duration / 1000;
        long hour = duration / 3600;
        long minute = (duration - hour * 3600) / 60;
        long second = duration - hour * 3600 - minute * 60;
        answerStatistic.setDuration(hour + ":" + minute + ":" + second);
        AnswerStatisticLog answerStatisticLog = new AnswerStatisticLog();
        answerStatisticLog.setName(tokenUser.getFirstName());
        answerStatisticLog.setLastName(tokenUser.getLastName());
        answerStatisticLog.setGroupName(tokenUser.getGroupNumber());
        answerStatisticLog.setDuration(hour + ":" + minute + ":" + second);
        answerStatisticLog.setRightUI(answerMap.get("UI"));
        answerStatisticLog.setRightFun(answerMap.get("F"));
        answerStatisticDao.save(answerStatisticLog);
        return answerStatistic;
    }

    private void getRecommendation(List<TypeTaskResult> taskResults) {
        for (TypeTaskResult typeTaskResult : taskResults) {
            String type = typeTaskResult.getTypeTest();
            int percent = typeTaskResult.getCountRight() * 100 / typeTaskResult.getAllCount();
            TaskTypeRecommendation taskTypeRecommendation
                    = recommendationDao.findByMaximumValueGreaterThanEqualAndMinimumValueLessThanEqualAndTaskType(percent, percent, type).block();
            typeTaskResult.setRecommendation(taskTypeRecommendation.getRecommendation());
        }
    }

    private List<String> getShuffleTask(List<BsuirTask> bsuirTaskList) {
        List<String> taskIds = new ArrayList<>();
        for (BsuirTask bsuirTask : bsuirTaskList) {
            taskIds.add(bsuirTask.getTaskRest());
        }
        Collections.shuffle(taskIds);
        return taskIds;
    }
}
