package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("name")
    String name;
    @SerializedName("value")
    float value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}