# Secondhand Market

A web application designed for users to buy and sell secondhand items. The platform allows users to create accounts, list products for sale, and browse or purchase items from other users.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## Features
- User authentication and profile management
- List items for sale with descriptions, prices, and images
- Browse and search for items by category or keywords
- Purchase items directly from other users
- Separate interfaces for buyers and sellers
- Order history and tracking

## Technologies Used
- **Backend:** Spring Boot MVC, Hibernate, MySQL
- **Frontend:** JSP, HTML, CSS, JavaScript
- **Database:** MySQL
- **Tools:** IntelliJ IDEA, Git, Tomcat, MySQL Workbench, phpMyAdmin

## Installation

To run this application locally, follow these steps:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/ruoyali7/Secondhand-Market.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd Secondhand-Market
    ```

3. **Configure application properties:**

    - Edit the `application.properties` file with your MySQL database credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3722431
    spring.datasource.username=sql3722431
    spring.datasource.password=knICu7uWIy
    ```

4. **Build and run the application:**

    - Using IntelliJ IDEA, run the application as a Spring Boot project.

5. **Access the application:**

    - Open your web browser and go to `http://localhost:8443`.

## Usage

- **Buyer Interface:** Browse and purchase items from the marketplace.
- **Seller Interface:** List items for sale and manage your listings.

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request with your changes.

1. Fork the repository.
2. Create your feature branch: `git checkout -b feature/YourFeature`.
3. Commit your changes: `git commit -m 'Add YourFeature'`.
4. Push to the branch: `git push origin feature/YourFeature`.
5. Submit a pull request.

