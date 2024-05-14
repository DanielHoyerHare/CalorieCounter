package tec.calories.app.models;

public class Food {
    public Food() {
    }
    public Food(String name, float energy, float fat, float carbohydrate, float protein, float salt) {
        super();
        this.name = name;
        this.energy = energy;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.salt = salt;
    }
    public Food(int id, String name, float energy, float fat, float carbohydrate, float protein, float salt) {
        super();
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.salt = salt;
    }
    int id;
    String name;
    float energy;
    float fat;
    float carbohydrate;
    float protein;
    float salt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getSalt() {
        return salt;
    }

    public void setSalt(float salt) {
        this.salt = salt;
    }
}
