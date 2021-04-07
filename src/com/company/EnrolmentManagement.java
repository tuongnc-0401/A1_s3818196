package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;


public class EnrolmentManagement implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
    private HashSet<Student> students = new HashSet<Student>();
    private HashSet<Course> courses = new HashSet<Course>();
    private HashSet<String> sems = new HashSet<String>();
    @Override
    public void add() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Enroll a student for 1 semester ===");
        getAllStudents();
        String sID = inputStudentId(input);
        Student student = getStudentByID(sID);

        getAllCourse();
        String cID = inputCourseId(input);
        Course course = getCourseByID(cID);

        getAllSems();
        String sem = inputSem(input);

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

    private String inputSem(Scanner input) {
        String sem;
        while (true){
            System.out.print("Enter a sem: ");
            sem = input.nextLine();
            if (sems.contains(sem))
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

    private String inputCourseId(Scanner input) {
        String id;
        while (true){
            System.out.print("Enter a course id: ");
            id = input.nextLine();
            for (Course c: courses) {
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

        getAllStudentsInEnrollment();
        //String sID = inputStudentId(input);

        //String sem = findAllSemOfOneStudentByID(sID);

    }

    private void getAllStudentsInEnrollment() {
        HashSet<Student> studentsInEnrollment = new HashSet<Student>();
        for (StudentEnrolment enrollment: enrolments ) {
                studentsInEnrollment.add(enrollment.getStudent());
        }
        for (Student s: studentsInEnrollment) {
            System.out.println(s);
        }
    }

    @Override
    public void delete() {

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
        Student stu1 = new Student("s001","Nguyen Van A",stringToDate("2000-01-01"));
        Student stu2 = new Student("s002","Nguyen Van B",stringToDate("2000-02-01"));
        Student stu3 = new Student("s003","Nguyen Van C",stringToDate("2000-03-01"));
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

    /**
     * format a string to a date
     * @param orgDate
     * @return Date
     */
    public static Date stringToDate(String orgDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(orgDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * convert a date into a string
     * @param date
     * @return String
     */
    public static String dateToString(Date date){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }


    // input  Student id
    private String inputStudentId(Scanner input) {
        String id;
        while (true){
            System.out.print("Enter a student id: ");
            id = input.nextLine();
            for (Student s: students) {
                if (s.getsID().equals(id))
                        return id;
            }
            
            System.out.println("!! This student id is not in System, please enter again!! ");
            
        }
    }
}
