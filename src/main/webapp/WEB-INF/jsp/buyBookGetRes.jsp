<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${requestScope.buyBook.buy}</h1>
    <h1>${requestScope.buyBook.book}</h1>
    <h1>${requestScope.buyBook.amount}</h1>
</body>
</html>