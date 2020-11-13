package by.bsuir.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userAnswer")
public class UserAnswer {
    @Id
    private String id;
    private String userId;
    private String taskRest;
    private String answer;

    public UserAnswer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskRest() {
        return taskRest;
    }

    public void setTaskRest(String taskRest) {
        this.taskRest = taskRest;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
