package com.company;

import javax.xml.transform.Source;
import java.io.*;
import java.util.*;


public class EnrolmentManagement implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<StudentEnrolment>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();
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

        if (!checkDuplicate(enrolment)){
            System.out.println("Enroll failed!!");
            System.out.println("This student has already enrolled this course in this semester!!");
        } else {
            enrolments.add(enrolment);
            System.out.println("Enroll successfully!!");
        }
    }

    private boolean checkDuplicate(StudentEnrolment enrolment) {

        for (StudentEnrolment se: enrolments) {
            if (se.getStudent().getsID().equals(enrolment.getStudent().getsID()) && se.getSemester().equals(enrolment.getSemester()) && se.getCourse().getcID().equals(enrolment.getCourse().getcID())){
                return false;
            }
        }
        return true;
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

    public void getAllCourse() {
        for (Course c: courses) {
            System.out.println(c);
        }
    }

    private String inputCourseId(Scanner input, ArrayList<Course> coursesList) {
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

        getAllStudents();
        String sID = inputStudentId(input, students);
        Student student = getStudentByID(sID);
        

        ArrayList<Course> coursesList = getAllCourseOfOneStudent(sID);
        System.out.println("List of courses of student "+ sID);
        for (Course c1: coursesList) {
            System.out.println(c1);
        }

        int choice = inputUpdateChoice(input);
        if (choice == 1){
            System.out.println("List of courses of student "+ sID);
            for (Course c1: coursesList) {
                System.out.println(c1);
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose a course to delete.");
            String oldCourseID = inputCourseId(sc,coursesList);
            for (StudentEnrolment enrollment: enrolments) {
                if (enrollment.getStudent().getsID().equals(sID) && enrollment.getCourse().getcID().equals(oldCourseID)){
                    enrolments.remove(enrollment);
                    System.out.println("Delete successfully");
                    break;
                }
            }

        }

        if (choice == 2){
            System.out.println("==================================================");
            System.out.println("List of new courses which students did not learn yet: ");
            ArrayList<Course> newCoursesList = getAllCourseBySId(sID);
            for (Course c1: newCoursesList) {
                System.out.println(c1);
            }
            System.out.println("Choose a new course to add.");
            Scanner scanner1 = new Scanner(System.in);
            String newCourseID = inputCourseId(scanner1, newCoursesList);
            Course newCourse = getCourseByID(newCourseID);

            getAllSems();
            String sem = inputSem(scanner1,sems);


            StudentEnrolment newEnrollment = new StudentEnrolment(student, newCourse, sem);
            enrolments.add(newEnrollment);

            System.out.println("Add successfully!!");
        }
    }

    private int inputUpdateChoice(Scanner input) {
        System.out.println("what do you want to do?");
        System.out.println("1. delete");
        System.out.println("2. add");
        System.out.print("Your choice: ");
        int choice;
        choice = input.nextInt();
        while (choice != 1 && choice != 2){
            System.out.println("Incorrect input. Please enter your choice again: ");
            choice = input.nextInt();
        }
        return choice;
    }

    private ArrayList<Course> getAllCourseBySId(String sID) {
        ArrayList<Course> newCourseList = new ArrayList<Course>();
        for (Course c:courses) {
            boolean flags = true;
            for (StudentEnrolment se: enrolments) {
                if (c.getcID().equals(se.getCourse().getcID()) && se.getStudent().getsID().equals(sID)){
                    flags =false;
                }
            }
            if (flags){
                newCourseList.add(c);
            }

        }
        return newCourseList;
    }

    private ArrayList<Course> getAllCourseOfOneStudent(String sID) {
        ArrayList<Course> coursesList = new ArrayList<Course>();
        for (StudentEnrolment se: enrolments  ) {
            if (se.getStudent().getsID().equals(sID)){
                coursesList.add(se.getCourse());
            }
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

    private ArrayList<Student> getAllStudentsInEnrollment() {
        ArrayList<Student> studentsInEnrollment = new ArrayList<Student>();
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
        printAllEnrollment();
        int indexRemove = inputIndexRemove(input);
        enrolments.remove(enrolments.get(indexRemove-1));
        System.out.println("Delete this enrollment successfully !!");

    }

    private int inputIndexRemove(Scanner input) {
        System.out.print("Enter a number of enrollment: ");
        int num;
        num = input.nextInt();
        while (num<1 || num > enrolments.size()){
            System.out.println("!! The number of enrollment you choose is not in system ");
            System.out.print("Enter a number of enrollment again: ");
            num = input.nextInt();
        }
        return num;
    }

    @Override
    public StudentEnrolment getOne(int index) {
        return enrolments.get(index);
    }

    @Override
    public List<StudentEnrolment> getAll() {
        return enrolments;
    }
    public void printAllEnrollment() {
        int count = 1;
        System.out.println("================= All enrollments =================");
        for (StudentEnrolment enrollment: enrolments) {
            System.out.print(count++ +". ");
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

        String fileName = inputFileName();
        loadFile(fileName);
    }
    private String inputFileName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want load the enrolments from any file? (Y/N): ");
        String id;
        String fileName = "default.csv";
        id = input.nextLine();
        while (!id.equals("Y") && !id.equals("N")){
            System.out.println("Incorrect character.Please enter again!!");
            System.out.print("Do you want load the enrolments from any file? (Y/N): ");
            id = input.nextLine();
        }
        if (id.equals("Y")){
            System.out.print("Enter name of file: ");
            fileName = input.nextLine();
            return fileName;
        }
        return fileName;
    }
    private void loadFile(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can not find "+ fileName + " file");
        }
        while(scanner.hasNext()){
            String line =  scanner.nextLine();
            line = line.replace(System.getProperty("line.separator"), "");
            String[] tokens = line.split(",");

            //using StringTokenizer
            // add student
            String sid = tokens[0].replaceAll("[^a-zA-Z0-9]", "");
            Student student = new Student(sid, tokens[1], tokens[2]);
            checkDuplicate(student);

            // add course
            Course course = new Course(tokens[3], tokens[4],Integer.parseInt(tokens[5]));
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

    private void checkDuplicate(Student student) {
        boolean flag = true;
        for (int i = students.size()-1 ; i >=0  ; i--) {
            if(students.get(i).getsID().trim().equals(student.getsID().trim())){
                return;
            }
        }

        if (flag){
            students.add(student);
        }

    }

    private void checkDuplicate(Course course) {
        boolean flag = true;
        for (Course c: courses) {
            if (c.getcID().equals(course.getcID()))
                return;
        }
        if (flag){
            courses.add(course);
        }
    }

    public void printAllCourseOneStudentOneSem() {
        String report = "";
        for (String sem : sems) {
            report += "++++++++ Semester: " + sem + " ++++++++++++++++++\n";

            for (Student s: students) {
                 boolean checkStudent =false;
                 ArrayList<Course> coursesList = new ArrayList<Course>();
                for (StudentEnrolment se: enrolments) {
                    if (se.getSemester().equals(sem) && se.getStudent().getsID().equals(s.getsID())){
                        checkStudent = true;
                        coursesList.add(se.getCourse());
                    }
                }

                if (checkStudent){
                    report+="Student :" +s.getsID() +" " + s.getsName() +"\n";
                    for (Course c:coursesList  ) {
                            report+= "\t\t"+ c +"\n";
                    }
                }




            }
        }
        System.out.println(report);
    }
    public void printAllStudentOfOneCourseInOneSem() {
        String report = "";
        for (String sem : sems) {
            report += "++++++++ Semester: " + sem + " ++++++++++++++++++\n";

            for (Course c: courses) {
                boolean checkCourse =false;
                ArrayList<Student> studentsList = new ArrayList<Student>();
                for (StudentEnrolment se: enrolments) {
                    if (se.getSemester().equals(sem) && se.getCourse().getcID().equals(c.getcID())){
                        checkCourse = true;
                        studentsList.add(se.getStudent());
                    }
                }

                if (checkCourse){
                    report+="Course :" + c +"\n";
                    for (Student s:studentsList  ) {
                        report+= "\t\t"+ s.getsName() +" "+s.getsID() +"\n";
                    }
                }
            }
        }
        System.out.println(report);
    }

    public void printAllCourseInOneSem(){
        String report = "All courses in one semester\n";
        for (String sem : sems) {
            report += "++++++++ Semester: " + sem + " ++++++++++++++++++\n";

            for (Course c: courses) {
                boolean checkCourse =false;

                for (StudentEnrolment se: enrolments) {
                    if (se.getSemester().equals(sem) && se.getCourse().getcID().equals(c.getcID())){
                        checkCourse = true;
                    }
                }

                if (checkCourse){
                    report+="Course :" + c +"\n";
                }
            }
        }
        System.out.println(report);
        String fileName = "report3.csv";

        askWriteFile(report, fileName);

    }

    private void askWriteFile(String report, String fileName) {
        System.out.println(" Do you want to write this report into csv ?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Your choice: ");
        Scanner sc = new Scanner(System.in);
        int input;
        input = sc.nextInt();
        while (input != 1 && input != 2){
            System.out.println("Incorrect input. Please choose again!");
            System.out.print("Your choice: ");
            input = sc.nextInt();
        }
        if (input == 1){
            writeToFile(fileName,report,false);
            System.out.println("Write to file successfully!");
        } else {
            return;
        }
    }

    /**
            * write a line to file
     * @param fileName
     * @param line
     * @param append
     */
    public static void writeToFile(String fileName, String line, boolean append) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(fileName, append));
            output.println(line);
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        finally {
            if (output != null)
                output.close();
        }

    }

    // input  Student id
    private String inputStudentId(Scanner input, ArrayList<Student> studentsList) {
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
