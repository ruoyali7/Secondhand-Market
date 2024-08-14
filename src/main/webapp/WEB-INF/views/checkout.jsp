<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Secondhand Market</title>
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
        <section class="checkout">
            <h2>Checkout</h2>
            <form action="${pageContext.request.contextPath}/cart/completeCheckout" method="post">
			    <input type="hidden" name="user_id" value="${sessionScope.user.id}">
			    
			    <h3>Shipping Information</h3>
			    <p>Address: ${sessionScope.user.address}</p>
			    <p>Phone: ${sessionScope.user.phoneNumber}</p>
			
			    <h3>Payment Information</h3>
			    <label for="cardName">Cardholder Name:</label>
			    <input type="text" id="cardName" name="cardName" required>
			
			    <label for="cardNumber">Card Number:</label>
			    <input type="text" id="cardNumber" name="cardNumber" required>
			
			    <label for="expDate">Expiry Date:</label>
			    <input type="text" id="expDate" name="expDate" placeholder="MM/YY" required>
			
			    <label for="cvv">CVV:</label>
			    <input type="text" id="cvv" name="cvv" required>
			
			    <h3>Order Summary</h3>
			    <p>Total: $<c:out value="${totalPrice}"/></p>
			
			    <button type="submit" class="checkout-btn">Complete Checkout</button>
			</form>
			<button class="back-btn" onclick="window.location.href='${pageContext.request.contextPath}/cart/list?user_id=${sessionScope.user.id}'">Back to Cart</button>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>
</body>
</html>
