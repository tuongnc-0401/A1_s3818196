package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;


public class EnrolmentManagement implements StudentEnrolmentManager{
    private HashSet<StudentEnrolment> enrolments = new HashSet<StudentEnrolment>();
    private HashSet<Student> students = new HashSet<Student>();
    private HashSet<Course> courses = new HashSet<Course>();
    private HashSet<String> sems = new HashSet<String>();
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
        String sem = inputSem(input,sems);

        // new  student enrollment
        StudentEnrolment enrolment = new StudentEnrolment(student, course, sem);
        if (enrolments.contains(enrolment)){
            System.out.println("Enroll failed!!");
            System.out.println("This student has already enrolled this course in this semester!!");
        } else {
            enrolments.add(enrolment);
            System.out.println("Enroll successfully!!");
        }
    }

    private String inputSem(Scanner input, HashSet<String> semsList) {
        String sem;
        while (true){
            System.out.print("Enter a sem: ");
            sem = input.nextLine();
            if (semsList.contains(sem))
                return sem;
            System.out.println("!! This semester is not in System, please enter again!! ");

        }
    }

    private void getAllSems() {
        for (String s:sems) {
            System.out.println(s);
        }
    }

    private Course getCourseByID(String cID) {
        for (Course c: courses) {
            if (c.getcID().equals(cID)){
                return c;
            }
        }
        return null;
    }

    private void getAllCourse() {
        for (Course c: courses) {
            System.out.println(c);
        }
    }

    private String inputCourseId(Scanner input, HashSet<Course> coursesList) {
        String id;
        while (true){
            System.out.print("Enter a course id: ");
            id = input.nextLine();
            for (Course c: coursesList) {
                if (c.getcID().equals(id))
                    return id;
            }

            System.out.println("!! This course id is not in System, please enter again!! ");

        }
    }

    private Student getStudentByID(String id) {
        for (Student s: students) {
            if (s.getsID().equals(id))
                return s;
        }
        return null;
    }

    @Override
    public void update() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Update an enrolment of a student for 1 semester ===");

        HashSet<Student> studentsList = getAllStudentsInEnrollment();
        String sID = inputStudentId(input, studentsList);
        
        HashSet<String> studentSems = getAllSemsBysID(sID);
        String sem = inputSem(input, studentSems);

        HashSet<Course> coursesList = getAllCourseOfOneStudent(sID,sem);
        System.out.println("Choose the course to update.");
        String oldCourseID = inputCourseId(input,coursesList);

        System.out.println("List of courses: ");
        getAllCourse();
        System.out.println("Choose a new course.");
        String newCourseID = inputCourseId(input, courses);
        // update course
        if (oldCourseID.equals(newCourseID)){
            System.out.println("Update failed!!");
            System.out.println("New course is the same as old course");
        } else {
            Course newCourse = getCourseByID(newCourseID);
            for (StudentEnrolment enrollment: enrolments) {
                if (enrollment.getStudent().getsID().equals(sID) && enrollment.getSemester().equals(sem)){
                    enrollment.setCourse(newCourse);
                    System.out.println("Update successfully");
                }
            }
        }

    }

    private HashSet<Course> getAllCourseOfOneStudent(String sID, String sem) {
        HashSet<Course> coursesList = new HashSet<Course>();
        for (StudentEnrolment enrollment: enrolments) {
            if (enrollment.getStudent().getsID().equals(sID) && enrollment.getSemester().equals(sem)){
                coursesList.add(enrollment.getCourse());
            }
        }
        for (Course c: coursesList) {
            System.out.println(c);
        }
        return coursesList;
    }

    private HashSet<String> getAllSemsBysID(String sID) {
        HashSet<String> studentSems = new HashSet<String>();
        for (StudentEnrolment enrollment: enrolments) {
            if (enrollment.getStudent().getsID().equals(sID)){
                studentSems.add(enrollment.getSemester());
            }
        }
        System.out.println("++ Semesters of "+sID+" ++");
        for (String s: studentSems) {
            System.out.println(s);
        }
        return studentSems;
    }

    private HashSet<Student> getAllStudentsInEnrollment() {
        HashSet<Student> studentsInEnrollment = new HashSet<Student>();
        for (StudentEnrolment enrollment: enrolments ) {
                studentsInEnrollment.add(enrollment.getStudent());
        }
        for (Student s: studentsInEnrollment) {
            System.out.println(s);
        }
        return studentsInEnrollment;
    }

    @Override
    public void delete() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Delete an enrolment of a student for 1 semester ===");

        HashSet<Student> studentsList = getAllStudentsInEnrollment();
        String sID = inputStudentId(input, studentsList);

        HashSet<String> studentSems = getAllSemsBysID(sID);
        String sem = inputSem(input, studentSems);

        HashSet<Course> coursesList = getAllCourseOfOneStudent(sID,sem);
        System.out.println("Choose the course to delete.");
        String oldCourseID = inputCourseId(input,coursesList);

        for (StudentEnrolment enrollment: enrolments) {
            if (enrollment.getStudent().getsID().equals(sID) && enrollment.getSemester().equals(sem)){
                enrolments.remove(enrollment);
            }
        }
    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {
        System.out.println("================= All enrollments =================");
        for (StudentEnrolment enrollment: enrolments) {
            System.out.println(enrollment);
        }
        System.out.println("===================================================");
    }
    public void getAllStudents(){
        for (Student s:students) {
            System.out.println(s);
        }
    }
    public EnrolmentManagement() {
        loadFile("default.csv");
    }
    private void loadFile(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNext()){
            String line =  scanner.nextLine();
            String[] tokens = line.split(",");

            //using StringTokenizer
            // add student
            Student student = new Student(tokens[0], tokens[1], tokens[2]);
            students.add(student);
            // add course
            Course course = new Course(tokens[3], tokens[4],Integer.parseInt(tokens[5]));
            courses.add(course);
            // add sem
            String sem = tokens[6];
            sems.add(sem);
            // add enrollment
            StudentEnrolment enrolment = new StudentEnrolment(student, course, sem);
            enrolments.add(enrolment);

        }
        scanner.close();
    }

    private void createData() {
        Student stu1 = new Student("s001","Nguyen Van A","2000/01/01");
        Student stu2 = new Student("s002","Nguyen Van B","2000/01/01");
        Student stu3 = new Student("s003","Nguyen Van C","2000/01/01");
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);

        Course course1 = new Course("c001", "programming 1",12);
        Course course2 = new Course("c002", "programming 2",12);
        Course course3 = new Course("c003", "programming 3",12);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        sems.add("2021A");
        sems.add("2021B");
        sems.add("2021C");
    }


    // input  Student id
    private String inputStudentId(Scanner input, HashSet<Student> studentsList) {
        String id;
        while (true){
            System.out.print("Enter a student id: ");
            id = input.nextLine();
            for (Student s: studentsList) {
                if (s.getsID().equals(id))
                        return id;
            }
            
            System.out.println("!! This student id is not in System, please enter again!! ");
            
        }
    }
}
