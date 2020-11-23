package by.bsuir.entity;

import lombok.Data;

@Data
public class AnswerStatistic {
    private TypeTaskResult[] results;
    private String duration;
}
