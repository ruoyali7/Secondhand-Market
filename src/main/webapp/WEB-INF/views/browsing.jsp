<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Browsing - Secondhand Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Secondhand Market"></a>
        </div>
        <div class="search-bar">
            <form action="${pageContext.request.contextPath}/product/browsing" method="get">
                <input type="text" name="search" placeholder="Search for products..." value="${param.search}">
                <button type="submit">Search</button>
            </form>
        </div>
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
        <div class="profile-icon">
	        <a href="${pageContext.request.contextPath}/user/profile"><img src="${pageContext.request.contextPath}/images/userProfile.png" alt="User Profile"></a>
	    </div>
        <div class="cart-icon">
            <a href="${pageContext.request.contextPath}/cart/list?user_id=${sessionScope.user.id}"><img src="${pageContext.request.contextPath}/images/shoppingcart.jpg" alt="Shopping Cart"> <span class="cart-count">${sessionScope.cartSize}</span></a>
        </div>
    </header>

    <main>
    	<!-- Print user ID for debugging -->
        <section class="debug">
            <p>User ID: ${sessionScope.user.id}</p>
        </section>
        
        <section class="categories">
		    <h2>Browse by Categories</h2>
		    <div class="categories-list">
		        <div class="category">
		            <form action="${pageContext.request.contextPath}/product/browsing" method="get">
		                <input type="hidden" name="category" value="apparel">
		                <button type="submit">Apparel</button>
		            </form>
		        </div>
		        <div class="category">
		            <form action="${pageContext.request.contextPath}/product/browsing" method="get">
		                <input type="hidden" name="category" value="electronics">
		                <button type="submit">Electronics</button>
		            </form>
		        </div>
		        <div class="category">
		            <form action="${pageContext.request.contextPath}/product/browsing" method="get">
		                <input type="hidden" name="category" value="furniture">
		                <button type="submit">Furniture</button>
		            </form>
		        </div>
		        <div class="category">
		            <form action="${pageContext.request.contextPath}/product/browsing" method="get">
		                <input type="hidden" name="category" value="books">
		                <button type="submit">Books</button>
		            </form>
		        </div>
		        <div class="category">
		            <form action="${pageContext.request.contextPath}/product/browsing" method="get">
		                <input type="hidden" name="category" value="toys">
		                <button type="submit">Toys</button>
		            </form>
		        </div>
		    </div>
		</section>
        

        <section class="all-items">
            <h2>All Items</h2>
            <div class="items">
                <c:forEach var="product" items="${products}">
                    <div class="item">
                        <p><a href="#" class="product-link" data-id="${product.id}">${product.name}</a></p>
                        <p>$${product.price}</p>
                        <form action="${pageContext.request.contextPath}/cart/add" method="post">
                            <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                            <input type="hidden" name="product_id" value="${product.id}">
                            <input type="hidden" name="count" value="1">
                            <button type="submit">Add to Cart</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </section>
    </main>

    <!-- Product Details Modal -->
    <div id="product-modal" class="modal" style="display: none;">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 id="product-name"></h2>
            <p id="product-description"></p>
            <p>Price: $<span id="product-price"></span></p>
            <form action="${pageContext.request.contextPath}/cart/add" method="post">
                <input type="hidden" name="user_id" value="${sessionScope.user.id}">
                <input type="hidden" id="modal-product-id" name="product_id">
                <input type="hidden" name="count" value="1">
                <button type="submit">Add to Cart</button>
            </form>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>

    <script>
    $(document).ready(function () {
        $(".product-link").click(function (e) {
            e.preventDefault();
            const productId = $(this).data("id");

            console.log("Product link clicked, product ID:", productId); 

            // Make an AJAX request to get the product details
            $.get("/product/byId?id=" + productId, function (data) {
                console.log("AJAX request successful, data received:", data); 

                // Fill the modal with the product details
                $("#product-name").text(data.name);
                $("#product-description").text(data.description);
                $("#product-price").text(data.price);
                $("#modal-product-id").val(data.id);

                
                $("#product-modal").show();
            }).fail(function() {
                console.log("AJAX request failed."); 
            });
        });

        $(".close").click(function () {
            $("#product-modal").hide();
            console.log("Modal closed."); 
        });
    });
</script>

</body>
</html>
