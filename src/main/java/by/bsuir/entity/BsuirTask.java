package by.bsuir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "task")
@AllArgsConstructor
@NoArgsConstructor
public class BsuirTask {

    @Id
    private String id;
    private String taskName;
    private String header;
    private String taskRest;
    private String taskType;
    private String[] answers;
    private String rightAnswer;
}
