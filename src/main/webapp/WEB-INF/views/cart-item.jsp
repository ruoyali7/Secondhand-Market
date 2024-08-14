<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart - Secondhand Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Secondhand Market"></a>
        </div>
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
        <div class="cart-icon">
            <a href="${pageContext.request.contextPath}/cart/list?user_id=${sessionScope.user.id}"><img src="${pageContext.request.contextPath}/images/shoppingcart.jpg" alt="Shopping Cart"> <span class="cart-count">${sessionScope.cartSize}</span></a>
        </div>
    </header>

    <main>
        <section class="cart-items">
            <h2>Your Cart</h2>
            <c:choose>
                <c:when test="${empty cartItems}">
                    <p>Your cart is empty.</p>
                </c:when>
                <c:otherwise>
                    <div class="cart-items-list">
                        <c:set var="totalPrice" value="0.0"/>
                        <c:forEach var="cartItem" items="${cartItems}">
                            <div class="cart-item">
                                <p>${cartItem.product.name}</p>
                                <p>$${cartItem.totalPrice}</p>
                                <p>Quantity: ${cartItem.count}</p>
                                <form action="${pageContext.request.contextPath}/cart/delete" method="post">
                                    <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                                    <input type="hidden" name="product_it" value="${cartItem.product.id}">
                                    <button type="submit">Delete</button>
                                </form>
                            </div>
                            <c:set var="totalPrice" value="${totalPrice + cartItem.totalPrice}"/>
                        </c:forEach>
                    </div>
                    <div class="cart-summary">
                        <h3>Total: $<c:out value="${totalPrice}"/></h3>
                    </div>
                    <div class="cart-actions">
                        <form action="${pageContext.request.contextPath}/cart/checkOut" method="post">
                            <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                            <button type="submit" class="checkout-btn">Checkout</button>
                        </form>
                        <button class="back-btn" onclick="window.location.href='${pageContext.request.contextPath}/product/browsing'">Back to Browsing</button>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>
</body>
</html>
