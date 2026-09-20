# Core Java Student Management System Architecture

## Design Patterns & Core Java Principles Applied

### 1. Object-Oriented Programming (OOP)
- **Encapsulation**: Domain models like \Student\ encapsulate fields with private access modifiers and provide public getters/setters.
- **Inheritance & Abstraction**: Interfaces \StudentRepository\ and \StudentService\ abstract contract specifications from implementation details (\FileStudentRepository\ and \StudentServiceImpl\).
- **Polymorphism**: Interface types used for dependency injection throughout service and UI components.

### 2. Repository Pattern
- Decouples data storage layer from business domain logic.
- \FileStudentRepository\ manages custom file serialization/deserialization.

### 3. Custom Exception Hierarchy
- \StudentNotFoundException\, \DuplicateStudentException\, and \InvalidDataException\ handle operational and validation failures gracefully.

### 4. Java Collections API & Streams
- Utilizes \Map<String, Student>\, \List<Student>\, and \Stream API\ for efficient searching, filtering, and aggregation.
