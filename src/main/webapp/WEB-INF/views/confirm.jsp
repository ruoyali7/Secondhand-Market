<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation - Secondhand Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Secondhand Market"></a>
        </div>
    </header>

    <main>
        <section class="confirmation">
            <h2>Order Confirmation</h2>
            <p>Thank you, ${user.firstName} ${user.lastName}, for your purchase!</p>
            <p>Your order has been successfully placed, and a confirmation email has been sent to ${user.email}.</p>
            <h3>Order Summary:</h3>
            <ul>
                <c:forEach var="cartItem" items="${cartItems}">
                    <li>${cartItem.product.name} - Quantity: ${cartItem.count}</li>
                </c:forEach>
            </ul>
            <h3>Total: $<c:out value="${totalPrice}"/></h3>
            <button onclick="window.location.href='${pageContext.request.contextPath}/product/browsing'">Continue Shopping</button>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>
</body>
</html>
