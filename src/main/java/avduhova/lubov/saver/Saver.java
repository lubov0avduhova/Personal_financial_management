package avduhova.lubov.saver;

import avduhova.lubov.dto.Budget;
import avduhova.lubov.dto.Finances;
import avduhova.lubov.dto.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Saver {
    private static final String dataSavedInFile = "Данные сохранены в файл: %s";
    private static final String dataImportInFile = "Данные загружены из файла: %s";
    private static final String errorSavingDataInFile = "Ошибка при сохранении данных: %s";
    private static final String errorImportDataInFile = "Ошибка при загрузке данных: %s";
    private static final String fileFormat = ".ser";
    private static final String pathToFile = "src/main/resources/";


    // Сохранение данных в файл
    public boolean saveToFile(User user) {
        String fileNameWithFormat = pathToFile + user.getLogin() + fileFormat;
       // user.getBudget().put(Budget.INCOME, Map.of("дом", List.of(new Finances(100, "надо!"))));


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileNameWithFormat))) {
            oos.writeObject(user);
            System.out.printf(dataSavedInFile, fileNameWithFormat);
            return true;
        } catch (IOException e) {
            System.out.printf(errorSavingDataInFile, e.getMessage());
            return false;
        }
    }

    // Загрузка данных из файла
    public Optional<User> loadFromFile(String fileName) {

        //src/main/resources/user1.ser
        String fileNameWithFormat = "src/main/resources/user1.ser";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileNameWithFormat))) {
            User user = (User) ois.readObject();
            System.out.printf(dataImportInFile, fileNameWithFormat);
            return Optional.of(user);
        } catch (ClassNotFoundException | IOException e) {
            System.out.printf(errorImportDataInFile, e.getMessage());
            return Optional.empty();
        }
    }
}
