# Observer Pattern Task

## Описание проекта

![Скриншот окна приложения](screenshots/1.png)
Скриншот окна приложения

## Описание проекта

Простой учебный проект, демонстрирующий паттерн проектирования
**Observer (Наблюдатель)**.\
Сервер времени генерирует тики, а наблюдатели реагируют на изменения
состояния.

## Функциональность

-   Логирование текущего тика
-   Запуск и остановка музыки
-   Смена изображений

## Структура проекта
    files/
      1.png
      2.png
      music.mp3
    src/
      App/
        ObserverApplication.java
      Controller/
        ObserverController.java
      Observer/
        IObserver.java
        Subject.java
      Realization/
        MusicObserver.java
        PictureObserver.java
        TimeObserver.java
        TimeServer.java

## Автор

Ирхин Илья
