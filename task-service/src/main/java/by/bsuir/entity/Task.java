package by.bsuir.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task")
public class Task {

    @Id
    private Long id;
    private String taskName;
    private Byte[] htmlFile;
    private Question[] question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Byte[] getHtmlFile() {
        return htmlFile;
    }

    public void setHtmlFile(Byte[] htmlFile) {
        this.htmlFile = htmlFile;
    }

    public Question[] getQuestion() {
        return question;
    }

    public void setQuestion(Question[] question) {
        this.question = question;
    }
}