import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsTest {

    List<Employee> employees = new ArrayList<>();

    @BeforeSuite
    public void setUP(){
        Employee employee1 = new Employee("Mateusz", "Radzio", 30, List.of("Java", "VisualBasic", "C#"));
        Employee employee2 = new Employee("Marcin", "Zakrzewski", 22, List.of("Java", "Pyhton"));
        Employee employee3 = new Employee("Klaudia", "Zenko", 42, List.of("Java", "Scala"));
        Employee employee4 = new Employee("Jan", "Kowalski", 31, List.of("VisualBasic", "C#", "React"));
        Employee employee5 = new Employee("Zbigniew", "Ziobro", 28, List.of("C#", "VisualBasic"));
        Employee employee6 = new Employee("Aleksandra", "Nowak", 32, List.of("Python"));
        Employee employee7 = new Employee("Dominik", "Zakrzewski", 45, List.of("JavaScript", "Spring"));

        Collections.addAll(employees, employee1, employee2, employee3, employee4, employee5, employee6, employee7);
    }

    @Test
    public void filterOperation(){
        employees.stream()
                .filter(emp -> emp.getFirstName().startsWith("M"))
                .forEach(System.out::println);
    }

    @Test
    public void mapOperation(){
        employees.stream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    @Test
    public void sortOperation(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .forEach(System.out::println);
    }

    @Test
    public void limitOperation(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void skipOperation(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .skip(3)
                .forEach(System.out::println);
    }

    @Test
    public void countOperation(){
        long employeesOver30 = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .count();

        System.out.println(employeesOver30);
    }

    @Test
    public void minMaxOperations(){
        Employee youngestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getAge))
                .get();

        System.out.println(youngestEmployee);

        Employee oldestEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getAge))
                .get();

        System.out.println(oldestEmployee);
    }

    @Test
    public void findFirstOperation(){
        Employee employee = employees.stream()
                .filter(emp -> emp.getLastName().startsWith("Z"))
                .findFirst()
                .get();

        System.out.println(employee);
    }

    @Test
    public void findAnyOperation(){
        Employee employee = employees.stream()
                .filter(emp -> emp.getLastName().startsWith("Z"))
                .findAny()
                .get();

        System.out.println(employee);
    }

    @Test
    public void matchOperations(){
        boolean allMatch = employees.stream()
                .allMatch(employee -> employee.getLastName().startsWith("Z"));

        System.out.println(allMatch);

        boolean anyMatch = employees.stream()
                .anyMatch(employee -> employee.getLastName().startsWith("Z"));

        System.out.println(anyMatch);

        boolean noneMatch = employees.stream()
                .noneMatch(employee -> employee.getLastName().startsWith("Z"));

        System.out.println(noneMatch);
    }

    @Test
    public void reduceOperation(){
        Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
                .reduce((age1, age2) -> age1 + age2)
                .get();

        System.out.println(sumOfAllAges);

        Integer sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum)
                .get();

        System.out.println(sumOfAllAges2);

        int sumOfAllAges3 = employees.stream()
                .map(Employee::getAge)
                .reduce(0, Integer::sum);

        System.out.println(sumOfAllAges3);

        Integer sumOfAllAges4 = employees.stream()
                .reduce(0, (age, employee) -> age + employee.getAge(), Integer::sum);

        System.out.println(sumOfAllAges4);

        String allNames = employees.stream()
                .map(Employee::getFirstName)
                .reduce((name1, name2) -> name1 + ", " + name2)
                .get();

        System.out.println(allNames);
    }

    @Test
    public void takeWhileOperation(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .takeWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void dropWhileOperation(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .dropWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void forEachOrderedOperation(){
        String sentence = "MatRad Mateusz Radzio";

        sentence.chars().forEach(c -> System.out.print((char) c));
        System.out.println();
        sentence.chars().parallel().forEach(c -> System.out.print((char) c));
        System.out.println();
        sentence.chars().parallel().forEachOrdered(c -> System.out.print((char) c));
    }

    @Test
    public void peekOperation(){
        List<String> skills = employees.stream()
                .filter(employee -> employee.getSkills().size() >= 2)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(Employee::getSkills)
                .peek(e -> System.out.println("Mapped value: " + e))
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(skills);
    }

}
