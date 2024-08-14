<%--
  Created by IntelliJ IDEA.
  User: Roya
  Date: 7/28/2024
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup - Secondhand Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Secondhand Market"></a>
        </div>
    </header>

    <main>
        <h2>Sign Up</h2>
        <c:if test="${not empty message}">
            <div class="error">${message}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" required placeholder="(123)456-7890">
            </div>
            <div class="form-group">
                <label for="isSeller">Are you a seller?</label>
                <input type="checkbox" id="isSeller" name="isSeller">
            </div>
            <button type="submit">Sign Up</button>
        </form>
        <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
    </main>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>
</body>
</html>



<!-- Role Selection in Signup/Login Form -->
<%--<select name="role">--%>
<%--    <option value="Buyer">Buyer</option>--%>
<%--    <option value="Seller">Seller</option>--%>
<%--</select>--%>