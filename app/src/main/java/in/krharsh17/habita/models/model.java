package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class model {
    @SerializedName("inputs")
    List<inputs> inputs;

    public model(List<in.krharsh17.habita.models.inputs> inputs) {
        this.inputs = inputs;
    }

    public List<in.krharsh17.habita.models.inputs> getInputs() {
        return inputs;
    }

    public void setInputs(List<in.krharsh17.habita.models.inputs> inputs) {
        this.inputs = inputs;
    }
}