package by.bsuir.entity;

public class AnswerStatistic {
    private TypeTaskResult[] results;
    private String recommendation;
    private String duration;


    public TypeTaskResult[] getResults() {
        return results;
    }

    public void setResults(TypeTaskResult[] results) {
        this.results = results;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
