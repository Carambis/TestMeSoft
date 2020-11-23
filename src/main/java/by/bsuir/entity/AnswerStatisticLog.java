package by.bsuir.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "answersStat")
@Data
public class AnswerStatisticLog {
    private String name;
    private String lastName;
    private String groupName;
    private String duration;
    private int rightUI;
    private int rightFun;
}
