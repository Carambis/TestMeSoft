package by.bsuir.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userAnswer")
@Data
@NoArgsConstructor
public class UserAnswer {
    @Id
    private String id;
    private String userId;
    private String taskRest;
    private String answer;
}
