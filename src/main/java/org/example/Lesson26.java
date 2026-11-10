package org.example;

import org.example.lesson26.PhoneBook;
import org.example.lesson26.Student;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Lesson26 {
    /** Удаляет из переданной коллекции студентов со средним баллом строго ниже 3. */
    public static void removeUnderperformingStudents(Set<Student> students) {
        Objects.requireNonNull(students, "Коллекция студентов не должна быть null.");
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getAverageGrade() < 3) {
                iterator.remove();
            }
        }
    }

    /** Повышает курс на единицу только студентам со средним баллом не ниже 3. */
    public static void promoteStudents(Set<Student> students) {
        Objects.requireNonNull(students, "Коллекция студентов не должна быть null.");
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.promoteToNextCourse();
            }
        }
    }

    /** Печатает только имена студентов, которые сейчас обучаются на указанном курсе. */
    public static void printStudents(Set<Student> students, int course) {
        Objects.requireNonNull(students, "Коллекция студентов не должна быть null.");
        if (course < 1) {
            throw new IllegalArgumentException("Курс должен быть положительным числом.");
        }
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new LinkedHashSet<>();
        students.add(new Student("Иван Петров", "QA-01", 1, grades(5, 4, 3)));
        students.add(new Student("Анна Иванова", "QA-01", 1, grades(3, 3, 3)));
        students.add(new Student("Пётр Сидоров", "QA-02", 2, grades(2, 3, 3)));
        students.add(new Student("Мария Смирнова", "QA-02", 2, grades(5, 5, 4)));
        students.add(new Student("Алексей Кузнецов", "QA-03", 3, grades(2, 2, 1)));

        System.out.println("ЗАДАНИЕ 1. СТУДЕНТЫ");
        System.out.println("Исходная коллекция:");
        printStudentDetails(students);

        removeUnderperformingStudents(students);
        System.out.println("\nПосле удаления студентов со средним баллом < 3:");
        printStudentDetails(students);

        promoteStudents(students);
        System.out.println("\nПосле перевода студентов со средним баллом >= 3 на следующий курс:");
        printStudentDetails(students);

        System.out.println("\nИмена студентов на 2-м курсе:");
        printStudents(students, 2);
        System.out.println("\nИмена студентов на 3-м курсе:");
        printStudents(students, 3);

        System.out.println("\nЗАДАНИЕ 2. ТЕЛЕФОННЫЙ СПРАВОЧНИК");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "+375291112233");
        phoneBook.add("Петров", "+375292223344");
        phoneBook.add("Иванов", "+375293334455");
        phoneBook.add("Иванов", "+375291112233");

        System.out.println("Иванов: " + phoneBook.get("Иванов"));
        System.out.println("Петров: " + phoneBook.get("Петров"));
        System.out.println("Сидоров: " + phoneBook.get("Сидоров"));
    }

    private static Map<String, Integer> grades(int mathematics, int programming, int english) {
        Map<String, Integer> grades = new LinkedHashMap<>();
        grades.put("Математика", mathematics);
        grades.put("Программирование", programming);
        grades.put("Английский язык", english);
        return grades;
    }

    private static void printStudentDetails(Set<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
