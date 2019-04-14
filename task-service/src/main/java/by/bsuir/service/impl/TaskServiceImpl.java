package by.bsuir.service.impl;

import by.bsuir.dao.*;
import by.bsuir.entity.*;
import by.bsuir.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import service_client.security.TokenUser;

import java.time.Instant;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {
    private final CrudTaskDao crudTaskDao;
    private final TaskSequenceDao taskSequenceDao;
    private final UserAnswerDao userAnswerDao;
    private final TaskDao taskDao;
    private final RecommendationDao recommendationDao;

    @Autowired
    public TaskServiceImpl(CrudTaskDao crudTaskDao, TaskSequenceDao taskSequenceDao,
                           UserAnswerDao userAnswerDao, TaskDao taskDao,
                           RecommendationDao recommendationDao) {
        this.crudTaskDao = crudTaskDao;
        this.taskSequenceDao = taskSequenceDao;
        this.userAnswerDao = userAnswerDao;
        this.taskDao = taskDao;
        this.recommendationDao = recommendationDao;
    }

    @Override
    public Task getTask(String id) {
        return crudTaskDao.findByTaskRest(id);
    }

    @Override
    public void startTest() {
        List<String> taskTypes = taskDao.findAllTestType();
        List<String> taskSequence = new ArrayList<>();
        for (String type : taskTypes) {
            List<Task> taskList = crudTaskDao.findByTaskType(type);
            taskSequence.addAll(getShuffleTask(taskList));
        }
        TokenUser tokenUser = (TokenUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskSequence taskSequenceBean = new TaskSequence();
        taskSequenceBean.setUserId(tokenUser.getId());
        String[] taskSeauenceArray = taskSequence.toArray(new String[0]);
        taskSequenceBean.setTaskSequence(taskSeauenceArray);
        taskSequenceBean.setStartDate(Date.from(Instant.now()));
        taskSequenceDao.save(taskSequenceBean);
    }

    @Override
    public String getNextTask() {
        TokenUser tokenUser = (TokenUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = tokenUser.getId();
        TaskSequence taskSequence = taskSequenceDao.getByUserId(userId);
        String[] tasks = taskSequence.getTaskSequence();
        if (tasks == null || tasks.length == 0) {
            taskSequence.setFinishDate(Date.from(Instant.now()));
            return "finishTest";
        }
        String nextTask = tasks[0];
        tasks = Arrays.copyOfRange(tasks, 1, tasks.length);
        taskSequence.setTaskSequence(tasks);
        taskSequenceDao.delete(taskSequence.getId());
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
        List<String> types = taskDao.findAllTestType();
        for (String type : types) {
            answerMap.put(type, 0);
        }
        List<UserAnswer> userAnswers = userAnswerDao.findAllByUserId(userId);
        for (UserAnswer userAnswer : userAnswers) {
            String taskId = userAnswer.getTaskRest();
            Task task = crudTaskDao.findTaskById(taskId);
            if (userAnswer.getAnswer().equals(task.getRightQuestion())) {
                answerMap.computeIfPresent(task.getTaskType(), (k, v) -> v + 1);
            }
        }
        List<TypeTaskResult> taskResults = new ArrayList<>();
        for (String type : types) {
            TypeTaskResult typeTaskResult = new TypeTaskResult();
            typeTaskResult.setTypeTest(type);
            typeTaskResult.setAllCount(crudTaskDao.countByTaskType(type));
            typeTaskResult.setCountRight(answerMap.get(type));
        }

        answerStatistic.setResults(taskResults.toArray(new TypeTaskResult[0]));
        answerStatistic.setRecommendation(getRecommendation(taskResults));

        TaskSequence taskSequence = taskSequenceDao.getByUserId(userId);

        long duration = taskSequence.getFinishDate().getTime() - taskSequence.getStartDate().getTime();
        long hour = duration / 3600;
        long minute = (duration - hour * 3600) / 60;
        long second = duration - hour * 3600 - minute * 60;
        answerStatistic.setDuration(hour + ":" + minute + ":" + second);
        return answerStatistic;
    }

    private String getRecommendation(List<TypeTaskResult> taskResults) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TypeTaskResult typeTaskResult : taskResults) {
            String type = typeTaskResult.getTypeTest();
            int percent = typeTaskResult.getCountRight() / typeTaskResult.getAllCount() * 100;
            TaskTypeRecommendation taskTypeRecommendation
                    = recommendationDao.findByMaximumValueGreaterThanAndMinimumValueLessThanEqualAndTaskType(percent, percent, type);
            stringBuilder.append(taskTypeRecommendation.getRecommendation());
        }
        return stringBuilder.toString();

    }

    private List<String> getShuffleTask(List<Task> taskList) {
        List<String> taskIds = new ArrayList<>();
        for (Task task : taskList) {
            taskIds.add(task.getTaskRest());
        }
        Collections.shuffle(taskIds);
        return taskIds;
    }
}
