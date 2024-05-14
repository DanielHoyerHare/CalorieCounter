package tec.calories.app.models;

public class Activity_level {
    public Activity_level(String name, Double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    private String name;
    private Double multiplier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }
}
