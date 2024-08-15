<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Secondhand Market</title>
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
            <h2>Login</h2>
            <c:if test="${not empty message}">
                <div class="error">${message}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username"><span style="font-weight:bold">Username:</span></label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password"><span style="font-weight:bold">Password:</span></label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="button">Login</button>
            </form>
            <p>Don't have an account? <a href="${pageContext.request.contextPath}/signup" class="link">Register here</a></p>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>
</body>
</html>
