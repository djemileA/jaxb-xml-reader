# JAXB XML Reader на Kotlin

Проект для чтения XML файлов с использованием JAXB в Kotlin.

## Описание
Программа читает XML файл students.xml с информацией о студентах, парсит его с помощью JAXB и выводит структурированные данные.

## Функциональность
- Чтение XML файла с данными о студентах
- Автоматическое преобразование XML в Kotlin объекты
- Вывод информации о каждом студенте
- Статистика (средний возраст, хобби)

## Структура проекта
- Main.kt - основной файл программы с классами и логикой
- students.xml - XML файл с данными
- build.gradle.kts - конфигурация сборки

## Формат XML
```xml
<students>
    <student>
        <firstName>Имя</firstName>
        <secondName>Фамилия</secondName>
        <age>Возраст</age>
        <hobbies>
            <hobby>Хобби1</hobby>
            <hobby>Хобби2</hobby>
        </hobbies>
    </student>
</students>
