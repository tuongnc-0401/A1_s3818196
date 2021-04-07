package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class EnrolmentManagement implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();
    @Override
    public void add() {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Enroll a student for 1 semester ===");
        getAllStudents();
        String sID = inputStudentId(input);
        Student s = getStudentByID(sID);
        getAllCourse();
        String cID = inputCourseId(input);
        
        
    }

    private void getAllCourse() {
    }

    private String inputCourseId(Scanner input) {
        String id;
        while (true){
            System.out.print("Choose a course: ");
            id = input.nextLine();
            for (Course c: courses) {
                if (c.getcID().equals(id))
                    return id;
            }

            System.out.println("!! Course id is not in System, please enter again!! ");

        }
    }

    private Student getStudentByID(String id) {
        for (Student s: students) {
            if (s.getsID().equals(id))
                return s;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

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
            System.out.print("Choose a student: ");
            id = input.nextLine();
            for (Student s: students) {
                if (s.getsID().equals(id))
                        return id;
            }
            
            System.out.println("!! Student id is not in System, please enter again!! ");
            
        }
    }
}
