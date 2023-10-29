import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public void updateStudent(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}

class StudentManagement {
    private ArrayList<Student> students = new ArrayList<>();
    private int nextId = 1;

    public void addStudent(String name, int age, String course) {
        students.add(new Student(nextId++, name, age, course));
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> searchStudents(String query) {
        ArrayList<Student> results = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(query) || student.getCourse().contains(query)) {
                results.add(student);
            }
        }
        return results;
    }

    public void updateStudent(int id, String name, int age, String course) {
        Student student = findStudentById(id);
        if (student != null) {
            student.updateStudent(name, age, course);
        }
    }

    public void listStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        StudentManagement studentManagement = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            System.out.println("Student Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Search for Student");
            System.out.println("3. Update Student");
            System.out.println("4. List all Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter course: ");
                    String course = scanner.nextLine();
                    studentManagement.addStudent(name, age, course);
                    System.out.println("Student added.");
                    break;
                case 2:
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    ArrayList<Student> searchResults = studentManagement.searchStudents(query);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching students found.");
                    } else {
                        System.out.println("Search Results:");
                        for (Student result : searchResults) {
                            System.out.println(result);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Student studentToUpdate = studentManagement.findStudentById(id);
                    if (studentToUpdate != null) {
                        System.out.print("Enter new name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter new age: ");
                        age = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Enter new course: ");
                        course = scanner.nextLine();
                        studentManagement.updateStudent(id, name, age, course);
                        System.out.println("Student information updated.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("List of Students:");
                    studentManagement.listStudents();
                    break;
                case 5:
                    System.out.println("Exiting the Student Management System.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}