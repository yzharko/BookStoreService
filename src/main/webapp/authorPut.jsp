<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PUT Запрос</title>
</head>
<body>
    <h1>Отправка PUT-запроса</h1>
    <form action="updateAuthor" method="put">
        <input type="text" name="id">
        <input type="text" name="name">
        <input type="submit" value="Отправить PUT">
    </form>
</body>
</html>