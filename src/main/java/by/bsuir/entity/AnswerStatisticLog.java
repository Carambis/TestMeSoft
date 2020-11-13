package by.bsuir.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "answersStat")
public class AnswerStatisticLog {
    private String name;
    private String lastName;
    private String groupName;
    private String duration;
    private int rightUI;
    private int rightFun;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getRightUI() {
        return rightUI;
    }

    public void setRightUI(int rightUI) {
        this.rightUI = rightUI;
    }

    public int getRightFun() {
        return rightFun;
    }

    public void setRightFun(int rightFun) {
        this.rightFun = rightFun;
    }
}
