<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DELETE Запрос</title>
</head>
<body>
    <h1>Отправка DELETE-запроса для АВТОРА</h1>
    <form action="deleteAuthor" method="delete">
        name:<input type="text" name="name">
        <input type="submit" value="Отправить DELETE">
    </form>
</body>
</html>