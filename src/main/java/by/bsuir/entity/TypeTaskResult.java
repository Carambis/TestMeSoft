package by.bsuir.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeTaskResult {
    private String typeTest;
    private int countRight;
    private int allCount;
    private String recommendation;
}
