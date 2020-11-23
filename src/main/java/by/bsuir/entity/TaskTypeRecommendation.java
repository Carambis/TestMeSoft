package by.bsuir.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taskTypeRecommendation")
@Data
public class TaskTypeRecommendation {
    private String taskType;
    private int minimumValue;
    private int maximumValue;
    private String recommendation;
}
