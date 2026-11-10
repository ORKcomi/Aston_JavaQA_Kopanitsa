package org.example.lesson26;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/** Студент с оценкой по каждому предмету и текущим курсом обучения. */
public class Student {
    private final String name;
    private final String group;
    private int course;
    private final Map<String, Integer> grades;

    public Student(String name, String group, int course, Map<String, Integer> grades) {
        this.name = requireText(name, "Имя");
        this.group = requireText(group, "Группа");
        if (course < 1) {
            throw new IllegalArgumentException("Курс должен быть положительным числом.");
        }
        if (grades == null || grades.isEmpty()) {
            throw new IllegalArgumentException("Нужна хотя бы одна оценка.");
        }

        Map<String, Integer> copy = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            String subject = requireText(entry.getKey(), "Название предмета");
            Integer grade = entry.getValue();
            if (grade == null || grade < 0) {
                throw new IllegalArgumentException("Оценка по предмету " + subject
                        + " должна быть неотрицательным целым числом.");
            }
            if (copy.containsKey(subject)) {
                throw new IllegalArgumentException("Предмет " + subject + " указан несколько раз.");
            }
            copy.put(subject, grade);
        }
        this.course = course;
        this.grades = Collections.unmodifiableMap(copy);
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        long sum = 0;
        for (int grade : grades.values()) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public void promoteToNextCourse() {
        course = Math.incrementExact(course);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT,
                "%s, группа %s, курс %d, оценки %s, средний балл %.2f",
                name, group, course, grades, getAverageGrade());
    }

    // equals/hashCode не переопределяются: изменение курса не меняет положение объекта в Set.

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " не может быть пустым.");
        }
        return value.trim();
    }
}
