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
            <button>Search</button>
        </div>
        <div class="nav-menu">
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/browsing">Browse Products</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin">Admin Panel</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile">User Profile</a></li>
                </ul>
            </nav>
        </div>
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/login">Login</a>
            <a href="${pageContext.request.contextPath}/signup">Register</a>
        </div>
        <div class="cart-icon">
            <a href="${pageContext.request.contextPath}/cart"><img src="${pageContext.request.contextPath}/images/shoppingcart.jpg" alt="Shopping Cart"> <span class="cart-count">0</span></a>
        </div>
    </header>

    <main>
        <section class="popular-items">
            <h2>Popular Items</h2>
            <div class="items">
                <c:forEach var="product" items="${products}">
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/${product.id}.jpg" alt="${product.name}">
                        <p>${product.name}</p>
                        <p>$${product.price}</p>
                        <button>Add to Cart</button>
                    </div>
                </c:forEach>
            </div>
        </section>

        <section class="promotional-banners">
            <h2>Special Deals</h2>
            <div class="banners">
                <img src="${pageContext.request.contextPath}/images/banner1.png" alt="Banner 1">
                <img src="${pageContext.request.contextPath}/images/banner2.jpg" alt="Banner 2">
            </div>
        </section>
    </main>

    <footer>
        <div class="footer-links">
            <div class="quick-links">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
                    <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
                    <li><a href="${pageContext.request.contextPath}/location">Store Location</a></li>
                    <li><a href="${pageContext.request.contextPath}/qa">Q&A</a></li>
                    <li><a href="${pageContext.request.contextPath}/claim">Claim Center</a></li>
                </ul>
            </div>
            <div class="membership-info">
                <h3>Membership</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/signup">Registration</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile">My Profile</a></li>
                    <li><a href="${pageContext.request.contextPath}/cancel">Cancel Membership</a></li>
                </ul>
            </div>
            <div class="company-info">
                <h3>Company Info</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/policy">Policy</a></li>
                    <li><a href="${pageContext.request.contextPath}/terms">Terms of Service</a></li>
                    <li><a href="${pageContext.request.contextPath}/privacy">Privacy Policy</a></li>
                </ul>
            </div>
        </div>
        <p>&copy; 2024 Secondhand Market. All rights reserved.</p>
    </footer>
</body>
</html>
