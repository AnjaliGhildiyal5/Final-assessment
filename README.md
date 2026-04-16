# Final-assessment
Smart Campus Management

 Problem Statement

Managing student records, course details, and enrollments manually can be inefficient and error-prone. There is a need for a simple system that:

-Stores student and course information
-Handles enrollments between students and courses
-Prevents duplicate entries and invalid data
-Provides an easy way to view and manage records

**CampusSys** solves this by providing a console-based management system using Java.

 **Features**

-->Add Student

  -Stores student ID, name, and email
  -Prevents duplicate IDs

-->Add Course

  -Stores course ID, course name, and fee
  -Prevents duplicate course entries

-->Enroll Student

  -Links a student to a course
  -Validates student and course existence
  -Processes enrollment asynchronously using threads

-->View Students

-Displays all added students
-Shows message if no students exist

-->View Enrollments

-Displays all enrollments
 -Shows message if no enrollments exist

-->Error Handling

  -Custom exception for invalid inputs
  -Handles wrong data types and duplicate entries

--------------------------------
Output
--------------------------------
--- SmartCampus Menu ---
1. Add Student
2. Add Course
3. Enroll Student
4. View Students
5. View Enrollments
6. Save Data
7. Load Data
8. Exit
------------------------------
Enter choice: 4
No students available. Please add students first.

Enter choice: 1
Enter Student ID: 1001
Enter Name: Aman
Enter Email: amit@email.com
Student added successfully.


Enter choice: 2
Enter Course ID: 2001
Enter Course Name: Java Programming
Enter Fee: 3000
Course added successfully.
------------------------------------------------------------------------------------------------
