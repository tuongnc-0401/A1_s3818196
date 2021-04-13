package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class EnrolmentManagement implements StudentEnrolmentManager {
    private final ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();  // enrollment of system
    private final ArrayList<Student> students = new ArrayList<Student>(); // list of student having enrollment in system
    private final ArrayList<Course> courses = new ArrayList<Course>(); // list of course having in enrollment of system
    private final HashSet<String> sems = new HashSet<String>(); // list of semester having in enrollment

    public EnrolmentManagement() {
        String fileName = inputFileName();
        loadFile(fileName);
    }
    public EnrolmentManagement(String fileName) {
        loadFile(fileName);
    }

    /**
     * add a new enrollment into system
     */
    @Override
    public void add() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Enroll a student for 1 semester ===");
        getAllStudents();
        String sID = inputStudentId(input, students);
        Student student = getStudentByID(sID);

        getAllCourse();
        String cID = inputCourseId(input, courses);
        Course course = getCourseByID(cID);

        getAllSems();
        String sem = inputSem(input, sems);

        // new  student enrollment
        StudentEnrolment enrolment = new StudentEnrolment(student, course, sem);

        if (!checkDuplicate(enrolment)) {
            System.out.println("!!-----------------!!");
            System.out.println("!!  Enroll failed  !!");
            System.out.println("!!-----------------!!");
            System.out.println("This student has already enrolled this course in this semester!!");
        } else {
            enrolments.add(enrolment);
            System.out.println("|-----------------------|");
            System.out.println("|  Enroll successfully  |");
            System.out.println("|-----------------------|");
        }
    }

    /**
     * update (delete or add an enrollment)
     */
    @Override
    public void update() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Update an enrolment of a student for 1 semester ===");

        getAllStudents();
        String sID = inputStudentId(input, students);
        Student student = getStudentByID(sID);


        ArrayList<Course> coursesList = getAllCourseOfOneStudent(sID);
        System.out.println("List of courses of student " + sID);
        for (Course c1 : coursesList) {
            System.out.println(c1);
        }
        // ask user want to do
        String choice = inputUpdateChoice(input);
        // if user choose to detele
        if (choice.equals("1")) {
            System.out.println("List of courses of student " + sID);
            for (Course c1 : coursesList) {
                System.out.println(c1);
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose a course to delete.");
            String oldCourseID = inputCourseId(sc, coursesList);
            for (StudentEnrolment enrollment : enrolments) {
                if (enrollment.getStudent().getsID().equals(sID) && enrollment.getCourse().getcID().equals(oldCourseID)) {
                    enrolments.remove(enrollment);
                    System.out.println("|-----------------------|");
                    System.out.println("|  Delete successfully  |");
                    System.out.println("|-----------------------|");
                    break;
                }
            }

        }
        // if users choose to add
        if (choice.equals("2")) {
            System.out.println("==================================================");
            System.out.println("List of new courses which students did not learn yet: ");
            ArrayList<Course> newCoursesList = getAllCourseBySId(sID);
            for (Course c1 : newCoursesList) {
                System.out.println(c1);
            }
            System.out.println("Choose a new course to add.");
            Scanner scanner1 = new Scanner(System.in);
            String newCourseID = inputCourseId(scanner1, newCoursesList);
            Course newCourse = getCourseByID(newCourseID);

            getAllSems();
            String sem = inputSem(scanner1, sems);


            StudentEnrolment newEnrollment = new StudentEnrolment(student, newCourse, sem);
            enrolments.add(newEnrollment);
            System.out.println("|--------------------|");
            System.out.println("|  Add successfully  |");
            System.out.println("|--------------------|");
        }
    }

    /**
     *  delete an enrollment
     */
    @Override
    public void delete() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Delete an enrolment of a student for 1 semester ===");
        printAllEnrollment();
        int indexRemove = inputIndexRemove(input);
        enrolments.remove(enrolments.get(indexRemove - 1));
        System.out.println("Delete this enrollment successfully !!");

    }

    /**
     * get one enrollment
     * @param index : position of enrollment
     * @return an enrollment
     */
    @Override
    public StudentEnrolment getOne(int index) {
        return enrolments.get(index);
    }

    /**
     * return list of all enrollment in system
     * @return
     */
    @Override
    public List<StudentEnrolment> getAll() {
        return enrolments;
    }

    // Report & Statistic function section

    /**
     *  print all course of one student in one semester
     */
    public void printAllCourseOneStudentOneSem() {
        String report = "All courses of one student in one semester\n";
        for (String sem : sems) {
            report += "++++++++ Semester: " + sem + " ++++++++++++++++++\n";

            for (Student s : students) {
                boolean checkStudent = false;
                ArrayList<Course> coursesList = new ArrayList<Course>();
                for (StudentEnrolment se : enrolments) {
                    if (se.getSemester().equals(sem) && se.getStudent().getsID().equals(s.getsID())) {
                        checkStudent = true;
                        coursesList.add(se.getCourse());
                    }
                }

                if (checkStudent) {
                    report += "Student :" + s.getsID() + " " + s.getsName() + "\n";
                    for (Course c : coursesList) {
                        report += "\t\t" + c + "\n";
                    }
                }


            }
        }
        System.out.println(report);
        String fileName = "report1.csv";
        askWriteFile(report, fileName);
    }

    /**
     * print all students of one course in one semester
     */
    public void printAllStudentOfOneCourseInOneSem() {
        String report = "All students of one course in one semester\n";
        for (String sem : sems) {
            report += "++++++++ Semester: " + sem + " ++++++++++++++++++\n";

            for (Course c : courses) {
                boolean checkCourse = false;
                ArrayList<Student> studentsList = new ArrayList<Student>();
                for (StudentEnrolment se : enrolments) {
                    if (se.getSemester().equals(sem) && se.getCourse().getcID().equals(c.getcID())) {
                        checkCourse = true;
                        studentsList.add(se.getStudent());
                    }
                }

                if (checkCourse) {
                    report += "Course :" + c + "\n";
                    for (Student s : studentsList) {
                        report += "\t\t" + s.getsName() + " " + s.getsID() + "\n";
                    }
                }
            }
        }
        System.out.println(report);
        String fileName = "report2.csv";
        askWriteFile(report, fileName);
    }

    /**
     * print all course in one sem
     */
    public void printAllCourseInOneSem() {
        String report = "All courses in one semester\n";
        for (String sem : sems) {
            report += "++++++++ Semester: " + sem + " ++++++++++++++++++\n";

            for (Course c : courses) {
                boolean checkCourse = false;

                for (StudentEnrolment se : enrolments) {
                    if (se.getSemester().equals(sem) && se.getCourse().getcID().equals(c.getcID())) {
                        checkCourse = true;
                    }
                }

                if (checkCourse) {
                    report += "Course :" + c + "\n";
                }
            }
        }
        System.out.println(report);
        String fileName = "report3.csv";

        askWriteFile(report, fileName);

    }

    /**
     * write a line to file
     *
     * @param fileName : name of file to write into
     * @param line: content to write to file
     * @param append: continue to write or write from beginning
     */
    private static void writeToFile(String fileName, String line, boolean append) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(fileName, append));
            output.println(line);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            if (output != null)
                output.close();
        }

    }

    /**
     * check a enrollment is already in system or not
     * @param enrolment
     * @return true if enrollment is already in system, false if not.
     */
    public boolean checkDuplicate(StudentEnrolment enrolment) {
        for (StudentEnrolment se : enrolments) {
            if (se.getStudent().getsID().equals(enrolment.getStudent().getsID()) && se.getSemester().equals(enrolment.getSemester()) && se.getCourse().getcID().equals(enrolment.getCourse().getcID())) {
                return false;
            }
        }
        return true;
    }

    /**
     * receive a info of sem and check this sem in a list of sem
     * @param input
     * @param semsList : list of sem
     * @return info of sem
     */
    private String inputSem(Scanner input, HashSet<String> semsList) {
        String sem;
        while (true) {
            System.out.print("Enter a sem: ");
            sem = input.nextLine();
            if (semsList.contains(sem))
                return sem;
            System.out.println("!! This semester is not in System, please enter again!! ");

        }
    }

    /**
     *  print of all sem of the system
     */
    private void getAllSems() {
        for (String s : sems) {
            System.out.println(s);
        }
    }

    /**
     * find a course by course id
     * @param cID: id of course
     * @return: one course
     */
    private Course getCourseByID(String cID) {
        for (Course c : courses) {
            if (c.getcID().equals(cID)) {
                return c;
            }
        }
        return null;
    }

    /**
     *  print all courses of system
     */
    public void getAllCourse() {
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    /**
     *  get course id from input of user and check valid
     * @param input
     * @param coursesList
     * @return id of course
     */
    private String inputCourseId(Scanner input, ArrayList<Course> coursesList) {
        String id;
        while (true) {
            System.out.print("Enter a course id: ");
            id = input.nextLine();
            for (Course c : coursesList) {
                if (c.getcID().equals(id))
                    return id;
            }

            System.out.println("!! This course id is not in System, please enter again!! ");

        }
    }

    /**
     * find a student by student id
     * @param id
     * @return one student
     */
    private Student getStudentByID(String id) {
        for (Student s : students) {
            if (s.getsID().equals(id))
                return s;
        }
        return null;
    }

    /**
     * ask whether user want to do in update
     * @param input
     * @return option of users
     */
    private String inputUpdateChoice(Scanner input) {
        System.out.println("what do you want to do?");
        System.out.println("1. delete");
        System.out.println("2. add");
        System.out.print("Your choice: ");
        String choice;
        choice = input.nextLine();
        while (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Incorrect input. Please enter your choice again: ");
            choice = input.nextLine();
        }
        return choice;
    }

    /**
     * return the list of course which this student did not learn before
     * @param sID: student ID
     * @return list of course
     */
    private ArrayList<Course> getAllCourseBySId(String sID) {
        ArrayList<Course> newCourseList = new ArrayList<Course>();
        for (Course c : courses) {
            boolean flags = true;
            for (StudentEnrolment se : enrolments) {
                if (c.getcID().equals(se.getCourse().getcID()) && se.getStudent().getsID().equals(sID)) {
                    flags = false;
                }
            }
            if (flags) {
                newCourseList.add(c);
            }

        }
        return newCourseList;
    }

    /**
     * return list of course which this student learnt
     * @param sID student ID
     * @return list of course
     */
    private ArrayList<Course> getAllCourseOfOneStudent(String sID) {
        ArrayList<Course> coursesList = new ArrayList<Course>();
        for (StudentEnrolment se : enrolments) {
            if (se.getStudent().getsID().equals(sID)) {
                coursesList.add(se.getCourse());
            }
        }
        return coursesList;
    }

    /**
     *  choose index of an enrollment to delete
      * @param input
     * @return
     */
    private int inputIndexRemove(Scanner input) {
        System.out.print("Enter a number of enrollment: ");
        int num;
        num = input.nextInt();
        while (num < 1 || num > enrolments.size()) {
            System.out.println("!! The number of enrollment you choose is not in system !!");
            System.out.print("Enter a number of enrollment again: ");
            num = input.nextInt();
        }
        return num;
    }

    /**
     * print out all enrollments
     */
    public void printAllEnrollment() {
        int count = 1;
        System.out.println("====================== All enrollments ======================");
        for (StudentEnrolment enrollment : enrolments) {
            System.out.print(count++ + ". ");
            System.out.println(enrollment);
        }
        System.out.println("=============================================================");
    }

    /**
     * print out all students
     */
    public void getAllStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * populate data from csv or not
     * @return
     */
    private String inputFileName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want load the enrolments from any file? (Y/N): ");
        String id;
        String fileName = "default.csv";
        id = input.nextLine();
        while (!id.equals("Y") && !id.equals("N")) {
            System.out.println("Incorrect character.Please enter again!!");
            System.out.print("Do you want load the enrolments from any file? (Y/N): ");
            id = input.nextLine();
        }
        if (id.equals("Y")) {
            System.out.print("Enter name of file: ");
            fileName = input.nextLine();
            return fileName;
        }
        return fileName;
    }

    /**
     * read data from csv
     * @param fileName
     */
    private void loadFile(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can not find " + fileName + " file");
        }
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            line = line.replace(System.getProperty("line.separator"), "");
            String[] tokens = line.split(",");

            //using StringTokenizer
            // add student
            String sid = tokens[0].replaceAll("[^a-zA-Z0-9]", "");
            Student student = new Student(sid, tokens[1], tokens[2]);
            checkDuplicate(student);

            // add course
            Course course = new Course(tokens[3], tokens[4], Integer.parseInt(tokens[5]));
            checkDuplicate(course);
            // add sem
            String sem = tokens[6];
            sems.add(sem);
            // add enrollment
            StudentEnrolment enrolment = new StudentEnrolment(student, course, sem);
            enrolments.add(enrolment);

        }
        scanner.close();
    }

    /**
     * check a student is already in system or not
     * @param student
     */
    private void checkDuplicate(Student student) {
        boolean flag = true;
        for (int i = students.size() - 1; i >= 0; i--) {
            if (students.get(i).getsID().trim().equals(student.getsID().trim())) {
                return;
            }
        }

        if (flag) {
            students.add(student);
        }

    }

    /**
     * check a course is already in system or not
     * @param course
     */
    private void checkDuplicate(Course course) {
        boolean flag = true;
        for (Course c : courses) {
            if (c.getcID().equals(course.getcID()))
                return;
        }
        if (flag) {
            courses.add(course);
        }
    }

    /**
     * ask user want to write this report to a file
     * @param report: content of report
     * @param fileName: file name of report
     */
    private void askWriteFile(String report, String fileName) {
        System.out.println(" Do you want to write this report into csv ?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Your choice: ");
        Scanner sc = new Scanner(System.in);
        int input;
        input = sc.nextInt();
        while (input != 1 && input != 2) {
            System.out.println("Incorrect input. Please choose again!");
            System.out.print("Your choice: ");
            input = sc.nextInt();
        }
        if (input == 1) {
            writeToFile(fileName, report, false);
            System.out.println("|------------------------------------------|");
            System.out.println("|  Write to file " + fileName + " successfully  |");
            System.out.println("|------------------------------------------|");
        } else {
            return;
        }
    }

    /**
     * check this student id is on student list or not
     * @param input
     * @param studentsList: list of student to check
     * @return
     */
    private String inputStudentId(Scanner input, ArrayList<Student> studentsList) {
        String id;
        while (true) {
            System.out.print("Enter a student id: ");
            id = input.nextLine();
            for (Student s : studentsList) {
                if (s.getsID().equals(id))
                    return id;
            }

            System.out.println("!! This student id is not in System, please enter again!! ");

        }
    }

    /**
     * get number of enrollments
     */
    public int getNumberEnrollments(){
        return getAll().size();
    }
}
