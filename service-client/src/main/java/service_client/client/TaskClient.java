package service_client.client;

import service_client.data.User;
import service_client.data.request.UserCreation;
import service_client.result.TaskResult;
import service_client.data.Task;

public class TaskClient extends Client {
    private static final String SERVICE_PATH = "/task-service";

    public TaskClient() {
        super(SERVICE_PATH);
    }

    public Task get(final Long id, final String token) {
        return get("/get/" + id, TaskResult.class, token).getData();
    }

    public Task update(final User user, final String token) {
        return post("/update", user, TaskResult.class, token).getData();
    }

    public Task add(final UserCreation user, final String token) {
        return post("/add", user, TaskResult.class, token).getData();
    }
}
