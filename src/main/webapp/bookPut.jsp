<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PUT Запрос</title>
</head>
<body>
    <h1>Отправка PUT-запроса для КНИГИ</h1>
    <form action="updateBook" method="put">
        book_id:<li><input type="text" name="book.id"></li>
        title:<li><input type="text" name="title"></li>
        author_id:<li><input type="text" name="author.id"></li>
        genre:<li><input type="text" name="genre"></li>
        price:<li><input type="text" name="price"></li>
        amount:<li><input type="text" name="amount"></li>
        <input type="submit" value="Отправить PUT">
    </form>
</body>
</html>