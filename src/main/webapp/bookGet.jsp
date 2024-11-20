<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GET Запрос</title>
</head>
<body>
    <h1>Отправка GET-запроса для КНИГИ</h1>
    <form action="getBook" method="get">
        <input type="text" name="id">
        <input type="submit" value="Отправить GET">
    </form>
</body>
</html>