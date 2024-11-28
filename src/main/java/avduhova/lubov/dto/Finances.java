package avduhova.lubov.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class Finances implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter
    private double amount;
    private LocalDateTime date;
    private String description;

    public Finances(double amount, String description) {
        this.amount = amount;
        date = LocalDateTime.now();
        this.description = description;
    }
}
