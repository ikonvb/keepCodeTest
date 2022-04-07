# Outlet

**Описание**    
Приложение для создания заказов, покупателей и регистрации пользователей этого приложения по средством Rest Api.    
Доступ для пользователей осуществляется на основе ролей, для авторизации используется Jwt type Bearer.     
Stack: Java 8, Spring Boot, Spring Security, Spring Data Jpa, H2 database.   
Для правильной работы приложения нужно выполнить два основных действия.    
1. Создать роли (ROLE_USER, ROLE_ADMIN).
2. Создать пользователя(ей).    
Пример:
~~~
{
    "userName":"user1",
    "userEmail":"user1@gmail.com",
    "userPassword": "1234",
    "roles":["admin"]
}
~~~
3. Пользоваться приложением.    
Endpoints для выполнения этих операций можно найти в документации.    
Для работы с REST API рекомендуется использовать [**Postman**](https://www.postman.com/).    
Документация для REST API:
~~~
 {yourlocalhostaddress}/swagger-ui/index.html
~~~
База данных H2:
~~~
 {yourlocalhostaddress}/h2-console
~~~