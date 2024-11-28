package avduhova.lubov.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Budget implements Serializable {

    INCOME("доход"),
    EXPENSE("расход");

    private final String description;

    Budget(String description) {
        this.description = description;
    }

}
