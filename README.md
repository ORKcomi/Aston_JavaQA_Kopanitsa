# Домашнее задание 2.8 — Postman Echo

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
