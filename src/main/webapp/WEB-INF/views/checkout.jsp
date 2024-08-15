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
        <div class="header-buttons">
            <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/logout" class="button">Logout</a>
        </div>
        </div>
    </header>

    <main>
        <section class="checkout-section">
            <h2>Checkout</h2>

            <!-- Error message container -->
            <div class="error" style="display: none;"></div>

            <form id="checkoutForm" action="${pageContext.request.contextPath}/cart/completeCheckout" method="post">
                <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                
                <div class="form-group">
                    <h3>Shipping Information</h3>
                    <p>Address: ${sessionScope.user.address}</p>
                    <p>Phone: ${sessionScope.user.phoneNumber}</p>
                </div>

                <div class="form-centered">
                    <h3>Payment Information</h3>
                    <div class="form-group">
                        <label for="cardName">Cardholder Name:</label>
                        <input type="text" id="cardName" name="cardName" required>
                    </div>
                    <div class="form-group">
                        <label for="cardNumber">Card Number:</label>
                        <input type="text" id="cardNumber" name="cardNumber" maxlength="16" required>
                    </div>
                    <div class="form-group">
                        <label for="expDate">Expiry Date:</label>
                        <input type="text" id="expDate" name="expDate" placeholder="MM/YY" required>
                    </div>
                    <div class="form-group">
                        <label for="cvv">CVV:</label>
                        <input type="text" id="cvv" name="cvv" maxlength="3" required>
                    </div>
                </div>

                <div class="order-summary">
                    <h3>Order Summary</h3>
                    <p>Total: $<c:out value="${totalPrice}"/></p>
                </div>

                <div class="checkout-buttons">
                    <button type="submit" class="button checkout-btn">Complete Checkout</button>
                    <button type="button" class="button back-btn" onclick="window.location.href='${pageContext.request.contextPath}/cart/list?user_id=${sessionScope.user.id}'">Back to Cart</button>
                </div>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#checkoutForm').on('submit', function (e) {
                const cardNumber = $('#cardNumber').val();
                const cvv = $('#cvv').val();
                const expDate = $('#expDate').val();
                const currentDate = new Date();
                const expDateParts = expDate.split('/');
                
                let errorMessage = '';

                if (cardNumber.length !== 16 || isNaN(cardNumber)) {
                    e.preventDefault();
                    errorMessage = 'Invalid card number. It must be 16 digits.';
                }
                
                if (cvv.length !== 3 || isNaN(cvv)) {
                    e.preventDefault();
                    errorMessage = 'Invalid CVV. It must be 3 digits.';
                }
                
                if (expDateParts.length !== 2 || isNaN(expDateParts[0]) || isNaN(expDateParts[1])) {
                    e.preventDefault();
                    errorMessage = 'Invalid expiry date format. Use MM/YY.';
                }
                
                const expMonth = parseInt(expDateParts[0], 10);
                const expYear = parseInt('20' + expDateParts[1], 10);
                
                if (expMonth < 1 || expMonth > 12 || expYear < currentDate.getFullYear() || (expYear === currentDate.getFullYear() && expMonth < (currentDate.getMonth() + 1))) {
                    e.preventDefault();
                    errorMessage = 'Invalid expiry date. The card is expired.';
                }

                if (errorMessage) {
                    $('.error').text(errorMessage).show();
                }
            });
        });
    </script>
</body>
</html>
