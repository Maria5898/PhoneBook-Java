//Реализуйте структуру телефонной книги с помощью HashMap. Программа также должна учитывать, что в во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо считать, как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию числа телефонов//

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PhoneBook {
    private final Map<String, List<Integer>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    // Метод для добавления записи в телефонную книгу
    public void add(String name, Integer phoneNum) {
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, new ArrayList<>());
        }
        phoneBook.get(name).add(phoneNum);
    }

    // Метод для поиска номеров телефона по имени
    public List<Integer> find(String name) {
        return phoneBook.getOrDefault(name, new ArrayList<>());
    }

    // Метод для получения всей телефонной книги
    public Map<String, List<Integer>> getPhoneBook() {
        return phoneBook;
    }

    // Метод для получения отсортированной телефонной книги по убыванию числа телефонов
    public Map<String, List<Integer>> getSortedPhoneBook() {
        return phoneBook.entrySet()
                .stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Мария", 123456789);
        phoneBook.add("Иван", 987654321);
        phoneBook.add("Мария", 111222333);
        phoneBook.add("Алексей", 555666777);
        phoneBook.add("Иван", 333444555);
        phoneBook.add("Иван", 777888999);

        System.out.println("Телефонные номера Марии: " + phoneBook.find("Мария"));
        System.out.println("Телефонные номера Ивана: " + phoneBook.find("Иван"));

        System.out.println("Телефонная книга: " + phoneBook.getPhoneBook());
        System.out.println("Отсортированная телефонная книга: " + phoneBook.getSortedPhoneBook());
    }
}
