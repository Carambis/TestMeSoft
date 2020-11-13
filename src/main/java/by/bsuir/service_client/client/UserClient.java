package by.bsuir.service_client.client;

import by.bsuir.service_client.result.UserResult;
import by.bsuir.service_client.data.User;


public class UserClient extends Client {
    private static final String SERVICE_PATH = "/user-service";

    public UserClient() {
        super(SERVICE_PATH);
    }

    public User get(final Long id, final String token) {
        return get("/get/" + id, UserResult.class, token).getData();
    }

    public User update(final User user, final String token) {
        return post("/update", user, UserResult.class, token).getData();
    }

    public User add(final User user, final String token) {
        return post("/add", user, UserResult.class, token).getData();
    }
}