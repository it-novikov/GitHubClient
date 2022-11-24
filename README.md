Функционал приложения:
1. При загрузке приложения на холодную вас встречает Splash Screen с асинхронной загрузкой данных из сети в RecyclerView
2. После загрузки приложения вы попадаете на экран с поиском репоризиториев по имени пользователя. На этом же экране доступна кнопка перехода на экран загруженных репозиториев.
3. У каждого репозитория имеется кнопка скачивания, которая загружает репозиторий в папку Downloads и помещает в базу данных Room запись о загруженном репозитории.
4. При клике на репозиторий (за пределами кнопки скачивания) данный репозиторий открывается в браузере.

Используемый стек технологий:
- Kotlin
- Room Database
– Kotlin Coroutines
– Retrofit2 with Gson converter
- MVVM with LiveData
