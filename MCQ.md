Q1 A developer is implementing student enrollments where each student can enroll in multiple courses. The system should allow quick lookup of a student and their enrolled courses.
 
Which is the most optimal data structure?
sol: B. HashMap<Student, ArrayList<Course>>

Q2 During enrollment, a student enters a negative course fee, causing incorrect processing. The developer wants to ensure invalid data is handled properly.
What is the best approach?
sol: C. Throw a custom exception like InvalidFeeException

Q3 The system simulates enrollment processing using a thread. However, multiple threads are accessing the same enrollment list, causing inconsistent results.
What should be done?
sol: B. Use synchronized block or thread-safe collection

Q4 A developer wants to enforce a rule that every type of course must implement a method calculateFee() but allow different implementations.
Which concept should be used?

sol: B. Interface

