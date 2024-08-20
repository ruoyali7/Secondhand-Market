<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Dashboard - Secondhand Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css?v=${now.time}">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<header>
    <div class="logo">
        <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Secondhand Market"></a>
    </div>
    <div class="header-buttons">
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/product/browsing" class="button">Switch to Buying</a>
        </div>
        <div class="profile-icon">
            <a href="${pageContext.request.contextPath}/user/profile"><img src="${pageContext.request.contextPath}/images/userProfile.png" alt="User Profile"></a>
        </div>
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/logout" class="button">Logout</a>
        </div>
    </div>
</header>

<main>
    <section class="add-item form-centered">
        <h2>Add a New Item to Sell</h2>
        <form id="addItemForm">
            <input type="hidden" name="id" value="${sessionScope.user.id}">
            <div class="form-group">
                <label for="name"><span style="font-weight:bold">Item Name:</span></label>
                <input type="text" name="name" id="name" required>
            </div>
            <div class="form-group">
                <label for="description"><span style="font-weight:bold">Description:</span></label>
                <textarea name="description" id="description" required></textarea>
            </div>
            <div class="form-group">
                <label for="category"><span style="font-weight:bold">Category:</span></label>
                <select name="category" id="category" required>
                    <option value="" disabled selected></option>
                    <option value="apparel">Apparel</option>
                    <option value="electronics">Electronics</option>
                    <option value="furniture">Furniture</option>
                    <option value="books">Books</option>
                    <option value="toys">Toys</option>
                </select>
            </div>
            <div class="form-group">
                <label for="price"><span style="font-weight:bold">Price:</span></label>
                <input type="number" step="0.01" name="price" id="price" required>
            </div>
            <div class="form-group">
                <label for="count"><span style="font-weight:bold">Count:</span></label>
                <input type="number" step="1" name="count" id="count" required>
            </div>
            <button type="submit" class="button">Add Item</button>
        </form>
    </section>

    <section class="seller-items">
        <h2>Your Items for Sale</h2>
        <div class="items-grid" id="sellerItems">
            <c:forEach var="product" items="${products}">
                <div class="item-card">
                    <h3>${product.name}</h3>
                    <p class="price">$${product.price}</p>
                    <p class="description">${product.description}</p>
                    <p class="count">Stock: ${product.count}</p>
                    <button class="button update-stock" data-id="${product.id}" data-count="${product.count}">Update Stock</button>
<%--                    <button class="button delete-stock" data-id="${product.id}">Delete Stock</button>--%>

<%--                    <form action="${pageContext.request.contextPath}/seller/update" method="post">--%>
<%--                        <label for="countInput-${product.id}"><span style="font-weight:bold">Modify Stock:</span></label>--%>
<%--                        <input type="number" step="1" name="count" id="countInput-${product.id}" value="${product.count}" required>--%>
<%--                        <input type="hidden" name="productId" value="${product.id}">--%>
<%--                        <button type="submit">Update</button>--%>
<%--                    </form>--%>
                </div>
            </c:forEach>
        </div>
    </section>

    <!-- Update Stock Modal -->
    <div id="update-stock-modal" class="modal" style="display: none;">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Update Stock</h2>
            <form id="updateStockForm">
                <input type="hidden" name="productId" id="update-product-id">
                <div class="form-group">
                    <label for="new-count">New Stock Count:</label>
                    <input type="number" name="count" id="new-count" min="0" required>
                </div>
                <button type="submit" class="button">Update</button>
            </form>
        </div>
    </div>

    <script>
        $(document).ready(function () {

            // Open modal for updating stock
            $(".update-stock").click(function () {
                const productId = $(this).data("id");
                const currentCount = $(this).data("count");
                $("#update-product-id").val(productId);
                $("#new-count").val(currentCount);
                $("#update-stock-modal").show();
            });

            // Handle stock update form submission
            $("#updateStockForm").on('submit', function (e) {
                e.preventDefault();

                $.ajax({
                    url: '${pageContext.request.contextPath}/seller/update',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function (response) {
                        if (response === 'success') {
                            loadSellerItems();
                            $("#update-stock-modal").hide();
                        } else {
                            alert('Failed to update stock');
                        }
                    },
                    error: function () {
                        alert('Error updating stock');
                    }
                });
            });

            // Close the modal
            $(".close").click(function () {
                $("#update-stock-modal").hide();
            });

            // Function to reload seller items
            function loadSellerItems() {
                $.get('${pageContext.request.contextPath}/seller/dashboard', function (data) {
                    var items = $(data).find('#sellerItems').html();
                    $('#sellerItems').html(items);
                });
            }
        });
    </script>
</main>

<footer>
    <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
</footer>
</body>
</html>
