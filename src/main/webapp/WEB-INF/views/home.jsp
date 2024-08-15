<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Secondhand Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/images/logo.png" alt="Secondhand Market"></a>
        </div>
        <div class="search-bar">
            <input type="text" placeholder="Search for products...">
            <button onclick="redirectToLogin()">Search</button>
        </div>
        <div class="nav-menu">
            <nav>
                <ul>
                    <li><a href="javascript:void(0)" onclick="redirectToLogin()">Browse Products</a></li>
                    <li><a href="javascript:void(0)" onclick="redirectToLogin()">Admin Panel</a></li>
                    <li><a href="javascript:void(0)" onclick="redirectToLogin()">User Profile</a></li>
                </ul>
            </nav>
        </div>
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/login">Login</a>
            <a href="${pageContext.request.contextPath}/signup">Register</a>
        </div>
        <div class="cart-icon">
            <a href="javascript:void(0)" onclick="redirectToLogin()"><img src="${pageContext.request.contextPath}/images/shoppingcart.jpg" alt="Shopping Cart"> <span class="cart-count">0</span></a>
        </div>
    </header>

    <main>
        <!-- Welcome Section -->
        <section class="welcome-section">
            <h1>Welcome to Secondhand Market</h1>
            <p>Your one-stop marketplace for buying and selling secondhand items. Explore our wide range of products and find the best deals just for you!</p>
        </section>

        <!-- Featured Products Section -->
        <section class="featured-items">
            <h2>Featured Products</h2>
            <div class="items">
                <c:forEach var="product" items="${products}" begin="0" end="2">
                    <div class="item">
                        
                        <p>${product.name}</p>
                        <p>$${product.price}</p>
                        <button onclick="redirectToLogin()">View Product</button>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!-- Promotional Banners Section -->
        <section class="promotional-banners">
            <h2>Special Deals</h2>
            <div class="banners">
                <a href="javascript:void(0)" onclick="redirectToLogin()"><img src="${pageContext.request.contextPath}/images/banner1.png" alt="Banner 1"></a>
                <a href="javascript:void(0)" onclick="redirectToLogin()"><img src="${pageContext.request.contextPath}/images/banner2.jpg" alt="Banner 2"></a>
            </div>
        </section>
    </main>

    <footer>
        <div class="footer-links">
            <div class="quick-links">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">About Us</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Contact Us</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Store Location</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Q&A</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Claim Center</a></li>
                </ul>
            </div>
            <div class="membership-info">
                <h3>Membership</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/signup">Registration</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">My Profile</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Cancel Membership</a></li>
                </ul>
            </div>
            <div class="company-info">
                <h3>Company Info</h3>
                <ul>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Policy</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Terms of Service</a></li>
                    <li><a href="javascript:void(0)" onclick="notImplemented()">Privacy Policy</a></li>
                </ul>
            </div>
        </div>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>

    <script>
        function redirectToLogin() {
            alert("You need to login to continue.");
            window.location.href = "${pageContext.request.contextPath}/login";
        }

        function notImplemented() {
            alert("This feature is not yet implemented.");
        }
    </script>
</body>
</html>
