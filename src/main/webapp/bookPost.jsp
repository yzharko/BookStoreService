<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>POST Запрос</title>
</head>
<body>
    <h1>Отправка POST-запроса для КНИГИ</h1>
    <form action="setBook" method="post">
        title:<li><input type="text" name="title"></li>
        auhtor_id:<li><input type="text" name="author.id"></li>
        genre:<li><input type="text" name="genre"></li>
        price:<li><input type="text" name="price"></li>
        amount:<li><input type="text" name="amount"></li>

        <input type="submit" value="Отправить POST">
    </form>
</body>
</html>