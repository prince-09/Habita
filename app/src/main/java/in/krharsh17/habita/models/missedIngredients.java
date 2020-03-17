package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

public class missedIngredients {
    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}