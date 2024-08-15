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
        <section class="form-centered">
            <h2>Sign Up</h2>
            <c:if test="${not empty message}">
                <div class="error">${message}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/signup" method="post">
                <div class="form-group">
                    <label for="firstName"><span style="font-weight:bold">First Name:</span></label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
                <div class="form-group">
                    <label for="lastName"><span style="font-weight:bold">Last Name:</span></label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="email"><span style="font-weight:bold">Email:</span></label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="username"><span style="font-weight:bold">Username:</span></label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password"><span style="font-weight:bold">Password:</span></label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="address"><span style="font-weight:bold">Address:</span></label>
                    <input type="text" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="phoneNumber"><span style="font-weight:bold">Phone Number:</span></label>
                    <input type="text" id="phoneNumber" name="phoneNumber" required placeholder="(123)456-7890">
                </div>
                <div class="form-group">
                    <label for="isSeller"><span style="font-weight:bold">Are you a seller?</span></label>
                    <input type="checkbox" name="isSeller" id="isSeller">
                </div>
                <button type="submit" class="button">Sign Up</button>
            </form>
            <p>Already have an account? <a href="${pageContext.request.contextPath}/login" class="link">Login here</a></p>
        </section>
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