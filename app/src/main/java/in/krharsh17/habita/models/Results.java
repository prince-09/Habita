package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

public class Results {
    @SerializedName("data")
    Concepts concepts;

    public Results(Concepts concepts) {
        this.concepts = concepts;
    }

    public Concepts getConcepts() {
        return concepts;
    }

    public void setConcepts(Concepts concepts) {
        this.concepts = concepts;
    }
}