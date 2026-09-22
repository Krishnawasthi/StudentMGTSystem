# Core Java Student Management System Architecture

## Design Patterns & Core Java Principles Applied

### 1. Object-Oriented Programming (OOP)
- **Encapsulation**: Domain models like \Student\ encapsulate fields with private access modifiers and provide public getters/setters.
- **Inheritance & Abstraction**: Interfaces \StudentRepository\ and \StudentService\ abstract contract specifications from implementation details (\FileStudentRepository\ and \StudentServiceImpl\).
- **Polymorphism**: Interface types used for dependency injection throughout service and UI components.

### 2. Repository Pattern
- Decouples data storage layer from business domain logic.
- `FileStudentRepository` manages file serialization and storage.
- `InMemoryStudentRepository` enables lightweight in-memory caching for isolated unit tests.

### 3. Custom Exception Hierarchy
- `StudentNotFoundException`, `DuplicateStudentException`, `InvalidDataException`, `InvalidGradeException`, and `StorageException` handle operational, validation, and persistence failures gracefully.

### 4. Data Export & Logging Infrastructure
- `DataExportUtil` supports CSV and JSON serialization.
- `AppLogger` records timestamped log entries (`INFO`, `WARN`, `ERROR`) to `student_mgt.log`.

### 5. Java Collections API & Streams
- Utilizes `Map<String, Student>`, `List<Student>`, and Java `Stream API` for searching by name, filtering by GPA range, and retrieving top performers.
