# Personal Finance Manager

## Overview
The **Personal Finance Manager** is a Spring Boot application designed to help users manage their finances effectively. It provides functionalities to track income, expenses, budgets, and bills while offering insights through reports and analytics. Users receive notifications for critical actions like exceeded budgets and upcoming bills, ensuring better financial planning.

---

## Features

### Core Features:
- **Income Management**: Record and track income from various sources.
- **Expense Tracking**: Log expenses and categorize them.
- **Budget Management**: Define budgets for specific categories and monitor spending.
- **Bill Reminders**: Notify users of bills due in the next few days.
- **Financial Reports**:
  - Generate monthly and annual income vs. expense reports.
  - Show trends like "Spending increasing on categories."
- **Notifications**:
  - Alerts for exceeded budgets.
  - Reminders for upcoming bills.

### Security Features:
- JWT-based authentication for securing REST APIs.
- User token validation endpoint to ensure secure access.

---

## Technology Stack
- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **Security**: JWT (JSON Web Tokens)
- **Scheduling**: Spring Scheduler
- **Email Notifications**: Jakarta Mail API

---

## Prerequisites
- JDK 11 or higher
- Maven
- MySQL Server
- SMTP server credentials for email notifications

---

## Installation and Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/personal-finance-manager.git
   cd personal-finance-manager
   ```

2. **Configure Database:**
   - Update `application.properties` with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Configure Email Notifications:**
   - Update `application.properties` with your SMTP credentials:
     ```properties
     spring.mail.host=smtp.gmail.com
     spring.mail.port=587
     spring.mail.username=your_email@gmail.com
     spring.mail.password=your_email_password
     spring.mail.properties.mail.smtp.auth=true
     spring.mail.properties.mail.smtp.starttls.enable=true
     ```

4. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application:**
   - API endpoints can be accessed via Postman or any HTTP client.

---

## API Endpoints

### Authentication
- **POST /auth/generateToken**: Authenticate user and generate JWT.
- **GET /auth/validate**: Validate JWT token.

### Income
- **POST /api/income**: Add new income.
- **GET /api/income**: Get all income entries for given user id.

### Expense
- **POST /api/expense**: Add new expense.
- **GET /api/expense**: Get all expense entries for given user id.

### Budget
- **POST /api/budget**: Add or update budget for a category.
- **GET /api/budget**: Retrieve all budgets for given user id.

### Bills
- **POST /api/bills**: Add a new bill.
- **GET /api/bills**: Retrieve all bills for given user id.

---

## Scheduled Tasks
- **Upcoming Bill Reminders**:
  - Notifies users of bills due in the next 3 days.
  - Runs daily at 9:00 AM.
- **Budget Exceed Alerts**:
  - Sends alerts when expenses exceed the defined budget.

---

## Future Enhancements
- Add user-friendly UI with React.js or Angular.
- Integrate advanced analytics using machine learning.
- Support for multiple currencies.

---

## Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch.
3. Make your changes and commit.
4. Push your branch and submit a pull request.
---

## Contact
For any questions or suggestions, please reach out to:
- **Email**: joshiaradhana76@gmail.com

