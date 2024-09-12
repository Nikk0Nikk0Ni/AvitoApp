# 🛒 AvitoApp

Приложение для отслеживания цен товаров.

## Стек
 - Язык: kotlin
 - Работа с сетью: Retrofit, OkHttp
 - Работа с базой данных: Room
 - Загрузка изображения с интернета: Picasso
 - Многопоточность: Kotlin Coroutine
 - View: XML
 - Навигация: Jetpack Navigation
 - Архитектура: MVVM
 - DI: Dagger2

## Функциональность
 - Вход осуществляется через запрос к Api, перед этим осуществив проверку на валидность логина и пароля.  
   В случае отсутствия показывается экран с ошибкой, позволяющий повторить попытку входа.
 - Регистрация проходит аналогичным способом, проверяя валидность и отслеживая ошибки
 - Список продуктов загружается с сервера через запрос к API. Реализована поддержка различных категорий  
   продуктов и сортировка по цене. В портретной ориентации отображается список всех продуктов,  
   а в альбомной ориентации — одновременно список продуктов и детальная информация о выбранном продукте.
   При переключениями между категориями продукции, сортировка остается.
- Детальная информация о продукте включает изображения, описание, категорию и цену с возможной скидкой.  
  Также есть возможность вернуться на главный экран с помощью кнопки "Назад", которая обрабатывает нажатие  
  как для портретного, так и для альбомного режимов.
- Так же при открытии детальной информации идет поиск по БД, и если элемент есть, то он отображатся на макете,
  вместо новой загрузки. В ином случае элемент будет загружен с сервера и добавлен в БД, для дальнейшего
  использования, благодаря чему можно оффлайн смотреть на детальную информацию по продуктам.
- В альбомной ориентации детальной информация отображается во `FragmentContainerView`, который находится  
  на той же странице, что и список продуктов. В портретной ориентации происходит полноценная навигация  
  на экран с детальной информацией.
- Реализована обработка сетевых ошибок. В случае отсутствия сети или проблем с сервером отображается  
  соответствующее сообщение с возможностью повторить запрос.

## 📱 Скриншоты

### Экран регистрации

 - Portrait  
![image](https://github.com/user-attachments/assets/42e3384d-4b86-4931-b735-3c295002fe67)
 - Landscape  
![image](https://github.com/user-attachments/assets/f2f9334a-b4d1-48c1-ab54-9b140489d6ef)

### Экран входа
 - Portrait  
![image](https://github.com/user-attachments/assets/50a35b81-f65c-4477-807e-050fab167c1b)
 - Landscape  
![image](https://github.com/user-attachments/assets/058f56de-1e82-4eff-bdc7-b36dd65e3cb4)
### Экран продуктов
 - Portrait  
![image](https://github.com/user-attachments/assets/ef2e851a-2a27-4eb1-80b3-13cc18f586e9)
 - Landscape  
![image](https://github.com/user-attachments/assets/951a9bc2-66ff-47b8-bad7-5e1a606d3d58)
### Экран детальной информации
 - Portrait  
   ![image](https://github.com/user-attachments/assets/94e20e38-c11b-4780-bcb2-b943c0b64e6a)
 - Landscape  
![image](https://github.com/user-attachments/assets/d3458113-b53b-47d9-b325-a2468005c2e5)

## ❌ Неудачи
 - Костыльная обработка ошибок полученных при ответе с сервера
 - Не создавал ветки для логина/регистрации, списка всех продуктов и детальной информации,  
   а пушил в мастер ветку. Это очень плохой тон, больше так не буду :)
 - Дизайн плохой как и мое воображение :(
 - Picasso не обрабатывает все изображения и для некоторых продуктов отображены  
   просто белые картинки.
