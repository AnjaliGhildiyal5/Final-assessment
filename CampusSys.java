import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Custom Exception
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

// Student Class
class Student {
    private int stId;
    private String stName;
    private String email;

    public Student(int stId, String stName, String email) {
        this.stId = stId;
        this.stName = stName;
        this.email = email;
    }

    public int getStId() {
        return stId;
    }

    public String getStName() {
        return stName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "StudentID: " + stId + ", Name: " + stName + ", Email: " + email;
    }
}

// Course Class
class Course {
    private int coId;
    private String coName;
    private double fee;

    public Course(int coId, String coName, double fee) {
        this.coId = coId;
        this.coName = coName;
        this.fee = fee;
    }

    public int getCoId() {
        return coId;
    }

    public String getCoName() {
        return coName;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return "CourseID: " + coId + ", Course: " + coName + ", Fee: " + fee;
    }
}

// Enrollment Class
class Enrollment {
    private Student ENstu;
    private Course course;

    public Enrollment(Student ENstu, Course course) {
        this.ENstu = ENstu;
        this.course = course;
    }

    public Student getENstu() {
        return ENstu;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Enrollment -> " + ENstu.getStName() + " enrolled in " + course.getCoName();
    }
}

// Main System
public class CampusSys {
    private static Map<Integer, Student> students = new HashMap<>();
    private static Map<Integer, Course> courses = new HashMap<>();
    private static List<Enrollment> enrollments = new ArrayList<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    // Add Student
    public static void addStudent(int id, String name, String email) throws InvalidInputException {
        if (students.containsKey(id)) {
            throw new InvalidInputException("Student ID already exists!");
        }
        students.put(id, new Student(id, name, email));
        System.out.println("Student added successfully.");
    }

    // Add Course
    public static void addCourse(int id, String name, double fee) throws InvalidInputException {
        if (courses.containsKey(id)) {
            throw new InvalidInputException("Course ID already exists!");
        }
        courses.put(id, new Course(id, name, fee));
        System.out.println("Course added successfully.");
    }

    // Enroll Student
    public static void enrollStudent(int stId, int coId) throws InvalidInputException {
        Student ENstu = students.get(stId);
        Course course = courses.get(coId);

        if (ENstu == null)
            throw new InvalidInputException("Student not found!");
        if (course == null)
            throw new InvalidInputException("Course not found!");

        Enrollment enrollment = new Enrollment(ENstu, course);
        enrollments.add(enrollment);
        System.out.println("Enrollment request submitted.");

        executor.submit(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Processed: " + enrollment);
            } catch (InterruptedException e) {
                System.out.println("Error processing enrollment.");
            }
        });
    }

    // View Students
    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available. Please add students first.");
        } else {
            students.values().forEach(System.out::println);
        }
    }

    // View Enrollments
    public static void viewEnrollments() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments available. Please enroll students first.");
        } else {
            enrollments.forEach(System.out::println);
        }
    }

    // Save Data
    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("campusData.ser"))) {
            oos.writeObject(new ArrayList<>(students.values()));
            oos.writeObject(new ArrayList<>(courses.values()));
            oos.writeObject(enrollments);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Read Data
    @SuppressWarnings("unchecked")
    public static void readData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("campusData.ser"))) {
            List<Student> studentList = (List<Student>) ois.readObject();
            List<Course> courseList = (List<Course>) ois.readObject();
            enrollments = (List<Enrollment>) ois.readObject();

            students.clear();
            courses.clear();

            for (Student s : studentList)
                students.put(s.getStId(), s);

            for (Course c : courseList)
                courses.put(c.getCoId(), c);

            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data.");
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n-------------------- SmartCampus Menu ----------------------------");
            System.out.println("  1. Add Student");
            System.out.println("  2. Add Course");
            System.out.println("  3. Enroll Student");
            System.out.println("  4. View Students");
            System.out.println("  5. View Enrollments");
            System.out.println("  6. Save Data");
            System.out.println("  7. Load Data");
            System.out.println("  8. Exit");
            System.out.println("------------------------------------------------------------------");
            System.out.print("---> Enter choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        int sid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String sname = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String semail = sc.nextLine();
                        addStudent(sid, sname, semail);
                        break;

                    case 2:
                        System.out.print("Enter Course ID: ");
                        int cid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Course Name: ");
                        String cname = sc.nextLine();
                        System.out.print("Enter Fee: ");
                        double fee = sc.nextDouble();
                        addCourse(cid, cname, fee);
                        break;

                    case 3:
                        System.out.print("Enter Student ID: ");
                        int esid = sc.nextInt();
                        System.out.print("Enter Course ID: ");
                        int ecid = sc.nextInt();
                        enrollStudent(esid, ecid);
                        break;

                    case 4:
                        viewStudents();
                        break;

                    case 5:
                        viewEnrollments();
                        break;

                    case 6:
                        saveData();
                        break;

                    case 7:
                        readData();
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        executor.shutdown();
                        break;

                    default:
                        throw new InvalidInputException("Invalid choice! Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input type.");
                sc.nextLine();
                choice = -1;
            }

        } while (choice != 8);

        sc.close();
    }
}