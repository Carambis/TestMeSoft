package by.bsuir.service_client.result;

import by.bsuir.service_client.data.Task;

public class TaskResult extends Result<Task> {
    public TaskResult() {
        super();
    }

    public TaskResult(String message, Task data) {
        super(message, data);
    }
}