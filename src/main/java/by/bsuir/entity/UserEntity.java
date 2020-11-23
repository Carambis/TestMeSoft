package by.bsuir.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import by.bsuir.service_client.data.User;

@Document(collection = "users")
@Data
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String groupNumber;

    public User toDto() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGroupNumber(groupNumber);
        return user;
    }
}