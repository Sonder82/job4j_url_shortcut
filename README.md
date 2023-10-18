# Сервис UrlShortCut

## О проекте

Сервис работает через REST API

Сервис состоит из:
- регистрация сайта. Сервисом могут пользоваться разные сайты. Каждому сайту выдается пара логин и пароль;
- авторизация. Авторизация выполняется через JWT;
- регистрация URL. После того как пользователь зарегистрировал свой сайт, он может отправлять на сайт
- ссылки и получать преобразованные ссылки;
- переадресация. Выполняется без авторизации;
- статистика. В сервисе считается количество вызовов каждого адреса;

## Стек технологий
- **Java 17**
- **Spring Boot 2.7.6**
- **Maven 3.8**
- **Git**

## Требования к окружению
- **Java 17**
- **Maven 3.8**

## Взаимодействие с сервисом.

### 1. Регистрация сайта
Для регистрации сайта необходимо отправить запрос:

<b>URL:</b>

<pre>POST /registration/sign-up</pre>

<b>Тело запроса в виде JSON object:</b>

<pre>{siteUrl : "venom.ru"}</pre>

<b>Ответ сервиса:</b>

<pre>{registration : true/false, login: UNIQUE_CODE, password : UNIQUE_CODE}</pre>

### 2. Авторизация
Авторизация происходит посредством JWT. Клиент отправляет  POST запрос с login и password.
Сервер возвращает token в HEAD блоке ответа:

<pre>Authorization: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6</pre>

### 3. Регистрация URL
После авторизации пользователь может отправить url ссылку и получить для этой ссылки преобразованный код:

<b>Отправка оригинального URL</b>
<pre>https://venom.ru/profile/exercise/106/task-view/532</pre>

<b>Получение кода для URL ссылки</b>
<pre>ZRUfdD2</pre>

ZRUfD2 ключ ассоциируемый со ссылкой URL.

<b>Request:</b>
<pre>POST /convert</pre>

<b>Body: Json object</b>
<pre>{
    url: "https://venom.ru/profile/exercise/106/task-view/532"
}</pre>

<b>Server Response:</b>
<pre>{code: UNIQUE_CODE}</pre>

### 4. Перенаправление. Выполняется без авторизации
Сайт отправляет запрос с  UNIQUE_CODE, сервер отправляет оригинальный URL и Status 302.

<b>Request:</b>
<pre>GET /redirect/UNIQUE_CODE</pre>

<b>Server response in head block.</b>
<pre>HTTP CODE - 302 REDIRECT URL</pre>

### 5. Статистика
Сервис считает каждое количество вызовов и выводит информацию о количестве вызовов каждого url.

<b>Request:</b>
<pre>GET /statistic</pre>

<b>Response: JSON object.</b>
<pre>{
    {url : URL, total : 0},
    {url : "https://venom.ru/profile/exercise/106/task-view/532", total : 2}
}</pre>

## Как запустить:
<ol>
    <li>Создать базу данных "url_shortcut" с username - "postgres", password - "password":
    <pre>CREATE DATABASE url_shortcut;</pre>
    </li>
    <li>
    Загрузить zip archive проекта.
    </li>
    <li>Распаковать, загрузить в  Intellij Idea.</li>
    <li>В вкладке Maven выполнить, start clean, test commands.</li>
    <li>Открыть Main.class и запустить main метод.</li>
</ol>

## Тестирование приложения в Postman:
<b>Sign up endpoint: <i>POST localhost:8080/registration/sign-up</i></b>


![](images/001_registration.png)

<b>Login endpoint: <i>POST localhost:8080/login</i></b>

![](images/002_login.png)

![](images/003_getToken.png)

<b>Convert endpoint <i>POST localhost:8080/convert/</i></b>

![](images/004convert.png)

![](images/005_getCode.png)

<b>Redirects endpoint <i>GET localhost:8080/redirects/{codeName}</i></b>

![](images/006_redirect.png)

<b>Statistics endpoint <i>GET localhost:8080/statistic/</i></b>

![](images/007_statistic.png)

## Контакты для связи<br>
> Связаться со мной [TELEGRAM](https://t.me/AlekseyNovoselov/).