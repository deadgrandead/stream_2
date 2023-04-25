import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // Задание 1: Найти количество несовершеннолетних
        long minorsCount = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + minorsCount);

        // Задание 2: Получить список фамилий призывников
        List<String> conscripts = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27 && p.getSex() == Sex.MAN)
                .map(Person::getFamily).toList();
        System.out.println("Список фамилий призывников: " + conscripts);

        // Задание 3: Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        List<Person> workingAgePeople = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.WOMAN && p.getAge() >= 18 && p.getAge() <= 60) ||
                        (p.getSex() == Sex.MAN && p.getAge() >= 18 && p.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println("Список потенциально работоспособных людей с высшим образованием: " + workingAgePeople);
    }
}
