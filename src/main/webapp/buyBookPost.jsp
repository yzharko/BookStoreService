<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>POST Запрос</title>
</head>
<body>
    <h1>Отправка POST-запроса для ЗАКАЗА</h1>
    <form action="setBuyBook" method="post">
        buy_description:<li><input type="text" name="description"></li>
        buy_client:<li><input type="text" name="client"></li>
        book_title:<li><input type="text" name="title"></li>
        book.author_id:<li><input type="text" name="author.id"></li>
        book.genre:<li><input type="text" name="genre"></li>
        book.price:<li><input type="text" name="price"></li>
        book.amount:<li><input type="text" name="book.amount"></li>
        buyBook.amount:<li><input type="text" name="buyBook.amount"></li>

        <input type="submit" value="Отправить POST">
    </form>
</body>
</html>