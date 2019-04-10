package service_client.result;

import service_client.data.Task;

public class TaskResult extends Result<Task> {
    public TaskResult() {
        super();
    }

    public TaskResult(String message, Task data) {
        super(message, data);
    }
}