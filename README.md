# Домашнее задание 2.8 — Postman Echo

Java 11, REST Assured 5.5.6, JUnit 5.13.4. Ветка `Lesson_2.8` содержит только это домашнее задание.

## Что проверяется

Написаны автотесты для каждого запроса папки **Request Methods** официальной коллекции Postman Echo:

| Запрос | Метод | Адрес | Тело запроса |
|---|---|---|---|
| GET Request | GET | `/get?foo1=bar1&foo2=bar2` | Нет |
| POST Raw Text | POST | `/post` | Исходная форматированная строка с `{"test": "value"}` |
| POST Form Data | POST | `/post` | `foo1=bar1&foo2=bar2`, формат `urlencoded` |
| PUT Request | PUT | `/put` | Исходная текстовая строка коллекции |
| PATCH Request | PATCH | `/patch` | Исходная текстовая строка коллекции |
| DELETE Request | DELETE | `/delete` | Исходная текстовая строка коллекции |

Каждый Java-тест проверяет HTTP **200**, JSON-тип ответа и **полное равенство всего тела ответа** ожидаемому объекту.
Сравниваются все ключи и вложенные значения: `args`, `headers`, `url`, а у запросов с телом — также `data`, `files`, `form`, `json`.
Проверяются пустые объекты, `null`, точный текст, параметры формы и все отражённые заголовки, включая `content-type` и длину тела в байтах UTF-8.
Дополнительный, отсутствующий или неверный ключ приводит к падению теста. Ожидаемые значения строятся из отправленного запроса, а не из полученного ответа.

Echo работает через Cloudflare, который переписывает `Accept-Encoding` в `gzip, br`.
Это значение проверяется явно; в Java порядок этих двух кодеков несущественен, лишние/пропущенные/повторные кодеки не допускаются.
Остальные значения заголовков сравниваются точно. [Документация Cloudflare](https://developers.cloudflare.com/fundamentals/reference/http-headers/).

## Файлы решения

- [PostmanEchoRequestMethodsTest.java](src/test/java/org/example/lesson28/PostmanEchoRequestMethodsTest.java) — шесть тестов REST Assured + JUnit.
- [RequestMethods.postman_collection.json](postman/RequestMethods.postman_collection.json) — импортируемая коллекция только с папкой Request Methods; у каждого запроса две проверки Postman: статус и полное тело.
- [actual-responses.json](docs/actual-responses.json) — реальные ответы всех шести запросов.
- [test-results.json](docs/test-results.json) — результаты запуска, версии инструментов и сведения об источнике.

Java читает методы, URL, параметры и тела из той же коллекции, которая импортируется в Postman.
Для воспроизводимости заданы явные заголовки `User-Agent`, `Accept`, `Accept-Encoding` и `Content-Type` для запросов с телом.
В Postman отключены автоматические cookies и случайные служебные заголовки, чтобы запросы не зависели от порядка запуска.

## Источник коллекции

[Официальная коллекция Postman Echo](https://www.postman.com/collections/631643-f695cab7-6878-eb55-7943-ad88e1ccfd65)
скачана 19.11.2026 через её [публичный экспорт JSON v2.1](https://www.postman.com/_api/collection/631643-f695cab7-6878-eb55-7943-ad88e1ccfd65?populate=true&format=2.1.0).
В решение включена только папка Request Methods. Методы, URL, query-параметры и тела всех шести исходных запросов сохранены.
Коллекция содержит собственные полные проверки ответа; другие папки Echo не включены.
POST Raw Text отправляет JSON-подобную строку как `text/plain`, поэтому ожидаемое поле `json` равно `null`.
У POST Form Data сервис Echo возвращает поля формы одновременно в `form` и `json`; проверяются оба объекта.

## Запуск Java-тестов

Нужны JDK 11, Maven и доступ к `https://postman-echo.com`.
Из корня проекта:

```text
mvn test
```

В IntelliJ IDEA открыть `pom.xml`, выбрать JDK 11, загрузить зависимости Maven и запустить `PostmanEchoRequestMethodsTest`.
Отчёты создаются в `target/surefire-reports/`, полученные JSON-ответы — в `target/echo-responses/`.
Все запросы выполняются к настоящему сервису Postman Echo.

## Запуск коллекции Postman

В своём аккаунте Postman выбрать **Import**, импортировать `postman/RequestMethods.postman_collection.json` и запустить папку **Request Methods** через Collection Runner.
Никакие ключи или дополнительные переменные окружения не нужны.

Дополнительный запуск через Newman при установленных Node.js и npm:

```text
npx newman@6.2.1 run postman/RequestMethods.postman_collection.json
```

## Фактический результат

Проверено 19.11.2026:

- Java 11: **6 тестов, 0 failures, 0 errors, 0 skipped**, Maven `BUILD SUCCESS`.
- Newman 6.2.1: **6 запросов, 12 проверок, 0 failures**, все ответы HTTP 200.
- Тела ответов Java и Newman совпали для каждого запроса.
