<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application</title>
</head>
<body>
<h1>TODO list</h1>

<c:forEach items="${list}" var="todo">
    <p>${todo.todo}</p>
</c:forEach>
</body>
</html>