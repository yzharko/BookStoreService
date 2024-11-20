<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DELETE Запрос</title>
</head>
<body>
    <h1>Отправка DELETE-запроса для КНИГИ</h1>
    <form action="deleteBook" method="delete">
        title:<input type="text" name="title">
        <input type="submit" value="Отправить DELETE">
    </form>
</body>
</html>