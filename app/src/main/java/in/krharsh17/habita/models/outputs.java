package in.krharsh17.habita.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class outputs {
    @SerializedName("outputs")
    List<Results> resultsList;

    public List<Results> getResultsList() {
        return resultsList;
    }

    public void setResultsList(List<Results> resultsList) {
        this.resultsList = resultsList;
    }
}