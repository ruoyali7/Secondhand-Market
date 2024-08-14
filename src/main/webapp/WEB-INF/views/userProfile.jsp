<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - Secondhand Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Secondhand Market"></a>
        </div>
    </header>

    <main>
        <section class="user-info">
            <h2>User Profile</h2>
            <p><strong>Name:</strong> ${user.firstName} ${user.lastName}</p>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Address:</strong> ${user.address}</p>
            <p><strong>Phone:</strong> ${user.phoneNumber}</p>
        </section>

        <section class="order-history">
            <h2>Order History</h2>
            <c:choose>
                <c:when test="${empty orders}">
                    <p>No orders found.</p>
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="order" items="${orders}">
                            <li>
                                <a href="#" class="order-link" data-id="${order.id}">Order #${order.id} - ${order.orderTime} - Total: $${order.totalPrice}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </section>

        <section class="order-items">
            <h3>Order Items</h3>
            <div id="order-items-container"></div>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $(".order-link").click(function (e) {
                e.preventDefault();
                const orderId = $(this).data("id");

                // Make an AJAX request to get the order items
                $.get("/orders/listItem?order_id=" + orderId, function (data) {
                    let itemsHtml = "<ul>";
                    $.each(data, function(index, item) {
                        itemsHtml += "<li>Product: " + item.product.name + " - Quantity: " + item.count + " - Price: $" + item.price + "</li>";
                    });
                    itemsHtml += "</ul>";

                    // Display the order items
                    $("#order-items-container").html(itemsHtml);
                });
            });
        });
    </script>
</body>
</html>
