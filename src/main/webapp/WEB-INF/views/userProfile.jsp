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
        <div class="header-buttons">
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/product/browsing" class="button">Back To Browsing</a>
        </div>
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/logout" class="button">Logout</a>
        </div>
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
                    <div class="order-list">
                        <c:forEach var="order" items="${orders}">
                            <div class="order-card">
                                <a href="#" class="order-link" data-id="${order.id}">
                                    <strong>Order #${order.id}</strong><br>
                                    Date: ${order.orderTime}<br>
                                    Total: $${order.totalPrice}
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>

        <!-- Order Details Modal -->
        <div id="order-modal" class="modal" style="display: none;">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h3 id="order-title"></h3>
                <div id="order-items-container"></div>
            </div>
        </div>
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
                        itemsHtml += "<li>" + item.product.name + ", Quantity: " + item.count + ", Price: $" + item.price + "</li>";
                    });
                    itemsHtml += "</ul>";

                    // Display the order items in the modal
                    $("#order-items-container").html(itemsHtml);
                    $("#order-title").text("Order #" + orderId + " Details");
                    $("#order-modal").show();
                });
            });

            $(".close").click(function () {
                $("#order-modal").hide();
            });
        });
    </script>
</body>
</html>
