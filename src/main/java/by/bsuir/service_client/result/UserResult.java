package by.bsuir.service_client.result;

import by.bsuir.service_client.data.User;

public class UserResult extends Result<User> {

    public UserResult() {
        super();
    }

    public UserResult(String message, User data) {
        super(message, data);
    }
}
