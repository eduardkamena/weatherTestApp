# WeatherTestApp

Проект **WeatherTest** — это веб-приложение для получения информации о погоде по заданным координатам (широта и долгота). Приложение использует внешний API OpenWeatherMap для получения данных о погоде и сохраняет историю запросов в базу данных PostgreSQL. Интерфейс реализован с использованием Vaadin.

## Основные функции

- Получение текущей погоды по координатам (широта и долгота).
- Отображение описания погоды, температуры и ощущаемой температуры.
- Сохранение истории запросов в базу данных.
- Отображение истории запросов на веб-странице.

## Технологии

- **Backend:** Spring Boot (Java 17)
- **Frontend:** Vaadin
- **База данных:** PostgreSQL
- **Тестирование:** JUnit 5, Mockito
- **Сборка:** Maven
- **Контейнеризация:** Docker

## Структура проекта

- **`src/main/java`:** Исходный код приложения.
    - **`ru.intetech.weatherTest`:** Основной пакет.
        - **`configuration`:** Конфигурационные классы (например, `RestTemplateConfig`).
        - **`controller`:** Контроллеры (например, `WeatherController`).
        - **`dto`:** Data Transfer Objects (DTO) для передачи данных.
        - **`entity`:** Сущности базы данных (например, `WeatherRequestHistory`).
        - **`repository`:** Репозитории для работы с базой данных.
        - **`service`:** Сервисы для бизнес-логики.
        - **`frontend`:** Веб-интерфейс на Vaadin.
- **`src/test/java`:** Тесты.
- **`src/main/resources`:** Ресурсы (например, `application.properties`).
- **`Dockerfile`:** Файл для создания Docker-образа приложения.
- **`docker-compose.yml`:** Файл для запуска приложения и базы данных в Docker.

## Запуск проекта

### 1. Запуск через Docker Compose

1. Убедитесь, что у вас установлены Docker и Docker Compose.
2. Соберите проект:
   ```bash
   mvn clean package
   ```
3. Запустите сервисы:
   ```bash
   docker-compose up --build
   ```
4. Приложение будет доступно по адресу: http://localhost:8080.

### 2. Локальный запуск (без Docker)

1. Убедитесь, что у вас установлены:
   - Java 17
   - PostgreSQL
   - Maven
2. Создайте базу данных vaadinWeather в PostgreSQL:
    ```sql
   CREATE DATABASE vaadinWeather;
    ```
3. Настройте подключение к базе данных в файле `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:15432/vaadinWeather
    spring.datasource.username=postgres
    spring.datasource.password=postgres
   ```
4. Соберите и запустите проект:
    ```bash
    mvn clean package
    java -jar target/weatherTest-0.0.1-SNAPSHOT.jar
   ```
5. Приложение будет доступно по адресу: http://localhost:8080.

### 3. Запуск тестов

Для запуска тестов выполните команду:
```bash
mvn test
```

## Конфигурация

Основные настройки приложения находятся в файле `src/main/resources/application.properties`:

- API OpenWeatherMap:
```properties
weather.url=https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&lang=ru&appid={apikey}
weather.api-key=ваш_api_ключ
```
- База данных:
```properties
spring.datasource.url=jdbc:postgresql://localhost:15432/vaadinWeather
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

## Пример использования

1. Откройте приложение в браузере: http://localhost:8080.
2. Введите координаты (широта и долгота) и нажмите кнопку **"Показать погоду!**".
3. На экране отобразится:
   - Описание погоды (например, "ясно").
   - Температура и ощущаемая температура.
   - Изображение, соответствующее погоде.
4. В разделе **"История"** отобразится список всех запросов.

#### Если у вас возникнут вопросы или предложения, пожалуйста, создайте issue в репозитории проекта.