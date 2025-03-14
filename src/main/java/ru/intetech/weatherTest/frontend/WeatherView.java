package ru.intetech.weatherTest.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import ru.intetech.weatherTest.dto.Weather;
import ru.intetech.weatherTest.entity.WeatherRequestHistory;
import ru.intetech.weatherTest.repository.WeatherRequestRepository;
import ru.intetech.weatherTest.service.WeatherService;

import java.util.List;

/**
 * Класс WeatherView представляет собой веб-интерфейс для отображения информации о погоде.
 * Пользователь может ввести координаты (широту и долготу) и получить текущую погоду для указанного местоположения.
 * Также отображается история запросов погоды.
 */
@Route("")
public class WeatherView extends VerticalLayout {

    private final WeatherService weatherService;
    private final WeatherRequestRepository weatherRequestRepository;

    private final TextField latitudeField = new TextField("Ширина");
    private final TextField longitudeField = new TextField("Долгота");
    private final Button weatherButton = new Button("Показать погоду!");

    private final Span descriptionSpan = new Span();
    private final Span tempSpan = new Span();
    private final Span historyHeader = new Span("История");
    private final VerticalLayout historyLayout = new VerticalLayout();

    private final Image weatherImage = new Image();

    /**
     * Конструктор класса WeatherView.
     *
     * @param weatherService           сервис для получения данных о погоде
     * @param weatherRequestRepository репозиторий для работы с историей запросов погоды
     */
    @Autowired
    public WeatherView(WeatherService weatherService, WeatherRequestRepository weatherRequestRepository) {
        this.weatherService = weatherService;
        this.weatherRequestRepository = weatherRequestRepository;

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        // Создаем таблицу (VerticalLayout)
        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setWidth("1024px");
        tableLayout.setPadding(false);
        tableLayout.setAlignItems(Alignment.CENTER);

        // Первая строка: Ширина (широта) и Долгота
        HorizontalLayout coordinatesLayout = new HorizontalLayout(latitudeField, longitudeField);
        coordinatesLayout.setWidthFull();
        coordinatesLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        coordinatesLayout.setSpacing(true);

        // Вторая строка: Кнопка
        HorizontalLayout buttonLayout = new HorizontalLayout(weatherButton);
        buttonLayout.setWidthFull();
        buttonLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        // Третья строка: Описание погоды
        HorizontalLayout descriptionLayout = new HorizontalLayout(descriptionSpan);
        descriptionLayout.setWidthFull();
        descriptionLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        // Четвертая строка: Температура
        HorizontalLayout tempLayout = new HorizontalLayout(tempSpan);
        tempLayout.setWidthFull();
        tempLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        // Пятая строка: Изображение
        HorizontalLayout imageLayout = new HorizontalLayout(weatherImage);
        imageLayout.setWidthFull();
        imageLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        // Шестая строка: Заголовок истории
        HorizontalLayout historyHeaderLayout = new HorizontalLayout(historyHeader);
        historyHeaderLayout.setWidthFull();
        historyHeaderLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        // Седьмая строка: История запросов
        historyLayout.setWidthFull();
        historyLayout.setDefaultHorizontalComponentAlignment(Alignment.START);
        historyLayout.setSpacing(false);
        historyLayout.setPadding(false);

        // Добавляем строки в таблицу
        tableLayout.add(coordinatesLayout, buttonLayout, descriptionLayout, tempLayout, imageLayout, historyHeaderLayout, historyLayout);
        add(tableLayout);

        // Обработка нажатия кнопки
        weatherButton.addClickListener(event -> showWeather());

        // Настройка стилей
        descriptionSpan.getStyle()
                .set("font-size", "32px")
                .set("font-weight", "bold");

        tempSpan.getStyle()
                .set("font-size", "14px")
                .set("font-weight", "bold");

        historyHeader.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold");

        // Загружаем историю запросов
        loadHistory();
    }

    /**
     * Метод для отображения информации о погоде на основе введенных координат.
     * Если координаты введены некорректно, отображается сообщение об ошибке.
     */
    private void showWeather() {
        try {
            double lat = Double.parseDouble(latitudeField.getValue());
            double lon = Double.parseDouble(longitudeField.getValue());

            Weather weather = weatherService.getWeather(lat, lon);
            String description = weather.getInfo().get(0).description();
            int temp = (int) weather.getMain().temp();
            int feelsLike = (int) weather.getMain().feelsLike();

            descriptionSpan.setText(description);
            tempSpan.setText(String.format("%d градусов, ощущается как %d", temp, feelsLike));
            setWeatherImage(description);

            // Обновляем историю запросов
            loadHistory();

        } catch (NumberFormatException e) {
            descriptionSpan.setText("Ошибка: Введите числовые значения для координат");
            tempSpan.setText("");
            weatherImage.setSrc("");
        } catch (Exception e) {
            descriptionSpan.setText("Ошибка: " + e.getMessage());
            tempSpan.setText("");
            weatherImage.setSrc("");
        }
    }

    /**
     * Метод для установки изображения погоды в зависимости от описания.
     *
     * @param weatherDescription описание погоды
     */
    private void setWeatherImage(String weatherDescription) {
        String imageUrl;
        // Обработка описания погоды с картинками
        if (weatherDescription.contains("небольшой снег")) {
            imageUrl = "https://avatars.mds.yandex.net/get-mpic/3916156/img_id6355055589781025494.jpeg/orig";
        } else if (weatherDescription.contains("ясно")) {
            imageUrl = "https://www.tokyo-city.ru/goods/ovsyanka_yasno_solnyschko_61f27d0c14298.jpg";
        } else if (weatherDescription.contains("пасмурно") || weatherDescription.contains("небольшой дождь")) {
            imageUrl = "https://chgtrk.ru/nimg/2021/11/02/AD622CD3-9A37-40C7-AD8C-255E12A5A0FA.jpg";
        } else {
            imageUrl = "https://a.d-cd.net/QMAAAgCBseA-960.jpg";
        }
        weatherImage.setSrc(imageUrl);
        weatherImage.setWidth("320px");
    }

    /**
     * Метод для загрузки и отображения истории запросов погоды.
     */
    private void loadHistory() {
        historyLayout.removeAll();
        List<WeatherRequestHistory> history = weatherRequestRepository.findAllByOrderByIdDesc();
        for (WeatherRequestHistory request : history) {
            Span historyEntry = new Span(String.format(
                    "%s | ширина: %.2f, долгота: %.2f | ответ: %s",
                    request.getRequestTime(), request.getLatitude(), request.getLongitude(),
                    request.getDescription()
            ));
            historyLayout.add(historyEntry);
        }
    }
}
