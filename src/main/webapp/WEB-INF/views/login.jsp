<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h2>Welcome to Secondhand Market</h2>
<%--<c:if test="${not empty user}">--%>
<%--    <p>Hello, ${user.username}!</p>--%>
<%--    <a href="<c:url value='/logout' />">Logout</a>--%>
<%--</c:if>--%>
<p>Hello, ${user.username}</p>
<p>${message}</p>
</body>
</html>