package by.bsuir.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taskTypeRecommendation")
public class TaskTypeRecommendation {
    private String taskType;
    private int minimumValue;
    private int maximumValue;
    private String recommendation;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
