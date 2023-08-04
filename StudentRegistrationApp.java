import java.util.ArrayList;
import java.util.Scanner;

class Student {   // POJO (Plain Old Java Object) representing student details
    private String name;
    private int age;
    private String email;
    private int marks;

    // Constructor to initialize student details
    public Student(String name, int age, String email, int marks) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.marks = marks;
    }

    // Setters and getters for each attribute
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getMarks() {
        return marks;
    }
}

public class StudentRegistrationApp {
    private ArrayList<Student> students;   // List to store registered students
    private Scanner scanner;

    // Constructor to initialize the list and scanner
    public StudentRegistrationApp() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to add a new student
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();
        System.out.print("Enter student marks: ");
        int marks = Integer.parseInt(scanner.nextLine());

        // Check if the email already exists in the list using Stream API
        boolean emailExists = students.stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(email));
        if (emailExists) {
            System.out.println("Student registration failed. Email already exists!");
        } else {
            Student student = new Student(name, age, email, marks);
            students.add(student);
            System.out.println("Student added successfully!");
        }
    }

    // Method to edit student details
    public void editStudent() {
        System.out.print("Enter student name to edit: ");
        String searchName = scanner.nextLine();
        int index = findStudentIndex(searchName);
        if (index != -1) {
            System.out.print("Enter new student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new student age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new student email: ");
            String email = scanner.nextLine();
            System.out.println("Enter new student marks: ");
            int marks = Integer.parseInt(scanner.nextLine());

            students.get(index).setName(name);
            students.get(index).setAge(age);
            students.get(index).setEmail(email);
            students.get(index).setMarks(marks);;
            System.out.println("Student details updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Method to view all registered students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
        } else {
            System.out.println("Registered Students:");
            for (Student student : students) {
                System.out.println("Name: " + student.getName());
                System.out.println("Age: " + student.getAge());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Marks: " + student.getMarks());
                System.out.println("----------------------");
            }
        }
    }

    // Method to delete a student
    public void deleteStudent() {
        System.out.print("Enter student name to delete: ");
        String searchName = scanner.nextLine();
        int index = findStudentIndex(searchName);
        if (index != -1) {
            students.remove(index);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Method to find the index of a student by name
    private int findStudentIndex(String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // New method to calculate average marks of all students
    public double calculateAverageMarks() {
        int totalMarks = 0;
        for (Student student : students) {
            totalMarks += student.getMarks();
        }
        if (students.isEmpty()) {
            return 0.0;
        }
        return (double) totalMarks / students.size();
    }

    // New method to calculate average age of all students
    public double calculateAverageAge() {
        int totalAge = 0;
        for (Student student : students) {
            totalAge += student.getAge();
        }
        if (students.isEmpty()) {
            return 0.0;
        }
        return (double) totalAge / students.size();
    }

    public static void main(String[] args) {
        StudentRegistrationApp app = new StudentRegistrationApp();

        while (true) {
            System.out.println("\nStudent Registration Application");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. View Students");
            System.out.println("4. Delete Student");
            System.out.println("5. Average Marks");
            System.out.println("6. Average Age");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(new Scanner(System.in).nextLine());

            switch (choice) {
                case 1:
                    app.addStudent();
                    break;
                case 2:
                    app.editStudent();
                    break;
                case 3:
                    app.viewStudents();
                    break;
                case 4:
                    app.deleteStudent();
                    break;
                case 5:
                    double averageMarks = app.calculateAverageMarks();
                    System.out.println("Average Marks: " + averageMarks);
                    break;
                case 6:
                    double averageAge = app.calculateAverageAge();
                    System.out.println("Average Age: " + averageAge);
                    break;
                case 7:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
