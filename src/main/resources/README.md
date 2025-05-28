# Digital Banking Backend Class Diagram

This directory contains a PlantUML class diagram that represents the structure of the Digital Banking Backend project.

## How to View the Diagram

To view the class diagram, you can use one of the following methods:

### Online PlantUML Editor

1. Go to [PlantUML Online Editor](https://www.plantuml.com/plantuml/uml/)
2. Copy the content of the `class-diagram.puml` file
3. Paste it into the editor
4. The diagram will be rendered automatically

### VS Code with PlantUML Extension

1. Install the [PlantUML extension](https://marketplace.visualstudio.com/items?itemName=jebbs.plantuml) for VS Code
2. Open the `class-diagram.puml` file
3. Press Alt+D to preview the diagram

### IntelliJ IDEA with PlantUML Plugin

1. Install the [PlantUML plugin](https://plugins.jetbrains.com/plugin/7017-plantuml-integration) for IntelliJ IDEA
2. Open the `class-diagram.puml` file
3. Right-click in the editor and select "PlantUML" > "Preview"

## Diagram Description

The class diagram shows the following components of the Digital Banking Backend project:

### Entities
- `BankAccount` (abstract): Base class for bank accounts
- `CurrentAccount`: A type of bank account with overdraft capability
- `SavingAccount`: A type of bank account with interest rate
- `Customer`: Represents a bank customer
- `AccountOperation`: Represents operations performed on bank accounts

### Enums
- `AccountStatus`: Possible statuses for a bank account (CREATED, ACTIVATED, SUSPENDED)
- `OperationType`: Types of account operations (DEBIT, CREDIT)

### DTOs (Data Transfer Objects)
- `BankAccountDTO`: Base DTO for bank accounts
- `CurrentBankAccountDTO`: DTO for current accounts
- `SavingBankAccountDTO`: DTO for saving accounts
- `CustomerDTO`: DTO for customers
- `AccountOperationDTO`: DTO for account operations
- `AccountHistoryDTO`: DTO for paginated account operations

### Repositories
- `BankAccountRepository`: Repository for bank accounts
- `CustomerRepository`: Repository for customers
- `AccountOperationRepository`: Repository for account operations

### Services
- `BankAccountService`: Service for bank account operations

### Controllers
- `BankAccountRestController`: REST controller for bank account operations
- `CustomerRestController`: REST controller for customer operations

The diagram also shows the relationships between these components, such as inheritance, associations, and dependencies.