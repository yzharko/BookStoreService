<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DELETE Запрос</title>
</head>
<body>
    <h1>Отправка DELETE-запроса</h1>
    <form action="deleteAuthor" method="delete">
        <input type="text" name="name">
        <input type="submit" value="Отправить DELETE">
    </form>
</body>
</html>