package by.bsuir.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "taskSequence")
@Data
@NoArgsConstructor
public class TaskSequence {
    @Id
    private String id;
    private String userId;
    private String[] taskSequence;
    private Date startDate;
    private Date finishDate;

}
