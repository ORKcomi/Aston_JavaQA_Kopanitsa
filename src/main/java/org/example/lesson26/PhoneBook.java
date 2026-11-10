package org.example.lesson26;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Телефонный справочник: одной фамилии может соответствовать несколько номеров.
 * Регистр фамилии учитывается, номера хранятся в порядке добавления.
 */
public class PhoneBook {
    private final Map<String, Set<String>> phoneNumbers = new LinkedHashMap<>();

    /**
     * Добавляет номер для фамилии. Повторное добавление той же пары не создаёт дубликат.
     * Крайние пробелы удаляются, остальное содержимое номера сохраняется.
     *
     * @throws IllegalArgumentException если фамилия или номер равны null, пусты
     *                                  или содержат только пробельные символы
     */
    public void add(String surname, String phoneNumber) {
        String trimmedSurname = validateAndTrim(surname, "Фамилия");
        String trimmedPhoneNumber = validateAndTrim(phoneNumber, "Номер телефона");
        phoneNumbers.computeIfAbsent(trimmedSurname, key -> new LinkedHashSet<>())
                .add(trimmedPhoneNumber);
    }

    /**
     * Возвращает неизменяемый снимок всех номеров фамилии в порядке добавления.
     * Если фамилии нет, возвращает пустой набор. Полученные номера может вывести main.
     *
     * @throws IllegalArgumentException если фамилия равна null, пуста
     *                                  или содержит только пробельные символы
     */
    public Set<String> get(String surname) {
        String trimmedSurname = validateAndTrim(surname, "Фамилия");
        Set<String> numbers = phoneNumbers.get(trimmedSurname);
        if (numbers == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new LinkedHashSet<>(numbers));
    }

    private static String validateAndTrim(String value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + ": значение не может быть null.");
        }
        String trimmedValue = value.trim();
        if (trimmedValue.isBlank()) {
            throw new IllegalArgumentException(name + ": ожидается непустая строка.");
        }
        return trimmedValue;
    }
}
