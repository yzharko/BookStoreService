<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PUT Запрос</title>
</head>
<body>
    <h1>Отправка PUT-запроса для АВТОРА</h1>
    <form action="updateAuthor" method="put">
        id:<input type="text" name="id">
        name:<input type="text" name="name">
        <input type="submit" value="Отправить PUT">
    </form>
</body>
</html>