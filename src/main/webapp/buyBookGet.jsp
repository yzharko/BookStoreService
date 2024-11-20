<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GET Запрос</title>
</head>
<body>
    <h1>Отправка GET-запроса для ЗАКАЗА</h1>
    <form action="getBuyBook" method="get">
        buy_book_id:<input type="text" name="buyBook.id">
        <input type="submit" value="Отправить GET">
    </form>
</body>
</html>