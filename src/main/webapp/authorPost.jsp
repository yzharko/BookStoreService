<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>POST Запрос</title>
</head>
<body>
    <h1>Отправка POST-запроса для АВТОРА</h1>
    <form action="setAuthor" method="post">
        name:<input type="text" name="name">
        <input type="submit" value="Отправить POST">
    </form>
</body>
</html>