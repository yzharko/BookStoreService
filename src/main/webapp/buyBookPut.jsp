<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PUT Запрос</title>
</head>
<body>
    <h1>Отправка PUT-запроса для ЗАКАЗА</h1>
    <form action="updateBuyBook" method="put">
        buy_book_id:<li><input type="text" name="buyBook.id"></li>
        buy_id:<li><input type="text" name="buy.id"></li>
        description:<li><input type="text" name="description"></li>
        client:<li><input type="text" name="client"></li>
        book_id:<li><input type="text" name="book.id"></li>
        title:<li><input type="text" name="title"></li>
        author_id:<li><input type="text" name="author.id"></li>
        genre:<li><input type="text" name="genre"></li>
        price:<li><input type="text" name="price"></li>
        book.amount:<li><input type="text" name="book.amount"></li>
        buy_book.amount:<li><input type="text" name="buyBook.amount"></li>
        <input type="submit" value="Отправить PUT">
    </form>
</body>
</html>