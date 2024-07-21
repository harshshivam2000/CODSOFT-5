import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {
    // Inner class: Course
    private static class Course {
        // Instance variables
        private String courseCode;
        private String title;
        private String description;
        private int capacity;
        private String schedule;
        private int currentEnrollment;

        // Constructor
        public Course(String courseCode, String title, String description, int capacity, String schedule) {
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.schedule = schedule;
            this.currentEnrollment = 0;
        }

        // Getters
        public String getCourseCode() {
            return courseCode;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getCapacity() {
            return capacity;
        }

        public String getSchedule() {
            return schedule;
        }

        public int getCurrentEnrollment() {
            return currentEnrollment;
        }

        // Enrollment methods
        public boolean enrollStudent() {
            if (currentEnrollment < capacity) {
                currentEnrollment++;
                return true;
            }
            return false;
        }

        public boolean dropStudent() {
            if (currentEnrollment > 0) {
                currentEnrollment--;
                return true;
            }
            return false;
        }

        // toString method for displaying course information
        @Override
        public String toString() {
            return "Course: " + courseCode + " - " + title + "\n"
                    + "Description: " + description + "\n"
                    + "Schedule: " + schedule + "\n"
                    + "Capacity: " + capacity + " | Enrolled: " + currentEnrollment + "\n";
        }
    }

    // Inner class: Student
    private static class Student {
        // Instance variables
        private String studentId;
        private String name;

        // Constructor
        public Student(String studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }

        // Getters
        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        // toString method for displaying student information
        @Override
        public String toString() {
            return "Student ID: " + studentId + " | Name: " + name;
        }
    }

    // Instance variables for CourseRegistrationSystem
    private List<Course> courses;
    private List<Student> students;
    private Scanner scanner;

    // Constructor
    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Main method to run the program
    public void run() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    studentMenu();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        } while (choice != 3);
    }

    // Display main menu
    private void displayMenu() {
        System.out.println("Welcome to Course Registration System");
        System.out.println("1. Admin");
        System.out.println("2. Student");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    // Admin menu options
    private void adminMenu() {
        int adminChoice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. List Available Courses");
            System.out.println("3. Remove Course");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    listAvailableCourses();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        } while (adminChoice != 4);
    }

    // Student menu options
    private void studentMenu() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. List Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. Drop a Course");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int studentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (studentChoice) {
            case 1:
                listAvailableCourses();
                break;
            case 2:
                registerForCourse();
                break;
            case 3:
                dropCourse();
                break;
            case 4:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Please enter again.");
                break;
        }
    }

    // Method to add a new course
    private void addCourse() {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Course Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Course Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Course Schedule: ");
        String schedule = scanner.nextLine();

        Course newCourse = new Course(code, title, description, capacity, schedule);
        courses.add(newCourse);
        System.out.println("Course added successfully.");
    }

    // Method to list all available courses
    private void listAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // Method to remove a course
    private void removeCourse() {
        System.out.print("Enter Course Code to remove: ");
        String codeToRemove = scanner.nextLine();

        boolean found = false;
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(codeToRemove)) {
                courses.remove(course);
                found = true;
                System.out.println("Course removed successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
        }
    }

    // Method to register for a course
    private void registerForCourse() {
        System.out.print("Enter Course Code to register: ");
        String codeToRegister = scanner.nextLine();

        Course courseToRegister = null;
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(codeToRegister)) {
                courseToRegister = course;
                break;
            }
        }

        if (courseToRegister != null) {
            if (courseToRegister.enrollStudent()) {
                System.out.println("Successfully registered for " + courseToRegister.getTitle());
            } else {
                System.out.println("Sorry, the course is full. Registration failed.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    // Method to drop a course
    private void dropCourse() {
        System.out.print("Enter Course Code to drop: ");
        String codeToDrop = scanner.nextLine();

        Course courseToDrop = null;
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(codeToDrop)) {
                courseToDrop = course;
                break;
            }
        }

        if (courseToDrop != null) {
            if (courseToDrop.dropStudent()) {
                System.out.println("Successfully dropped " + courseToDrop.getTitle());
            } else {
                System.out.println("You are not enrolled in this course.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        try {
            CourseRegistrationSystem system = new CourseRegistrationSystem();
            system.run();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}