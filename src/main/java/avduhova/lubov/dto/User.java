package avduhova.lubov.dto;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String login;
    private String password;

    private Map<Budget, Map<String, List<Finances>>> budget;
    private Map<String, Double> categoryLimits;

    public User(String login, String password) {
        this.login = login;
        this.password = password;

        this.budget = new HashMap<>();
        this.categoryLimits = new HashMap<>();

    }

    public User() {
        this.budget = new HashMap<>();
        this.categoryLimits = new HashMap<>();
    }
}
