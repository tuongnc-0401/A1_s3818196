package com.company;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.*;

public class TestLogic {
	private static EnrolmentManagement enrolmentManagement = new EnrolmentManagement("default.csv");
	private static Student student = new Student("S102732", "Mark Duong", "8/28/2001");
	private static Course course = new Course("COSC4030", "Theory of Computation", 12);
	private static StudentEnrolment enrolment = new StudentEnrolment(student,course,"2020C");
	InputStream sysInBackup = System.in;
	
	@BeforeClass
	 public static void setUpBeforeClass() throws Exception {  
        	System.out.println("Before class");  
         }  
	
	@Before
	 public void setUpBeforeMethod() throws Exception {  
       		System.out.println("Before each method");  
    	 }  
	
	@AfterClass
	 public static void setUpAfterClass() throws Exception {  
       		System.out.println("After class");  
   	 }  
	
	@After
	 public void setUpAfterMethod() throws Exception {  
      		System.out.println("After each method");  
  	 }

	@Test
	public void testView() {
		assertEquals("Checking size of List", 15, enrolmentManagement.getNumberEnrollments());
	}

	@Test
	public void testAdd() {
		EnrolmentManagement enrolmentManagement1 = new EnrolmentManagement("default.csv");
		ByteArrayInputStream in = new ByteArrayInputStream("S103192\nCOSC3321\n2021A".getBytes());
		System.setIn(in);
		enrolmentManagement1.add();
		assertEquals("Checking size of List after add", 16,
				enrolmentManagement1.getNumberEnrollments());
	}

	@Test
	public void testUpdate() {
		EnrolmentManagement enrolmentManagement2 = new EnrolmentManagement("default.csv");
		// check delete in Update
		ByteArrayInputStream in =
				new ByteArrayInputStream("S102192\n1\nPHYS1230".getBytes());
		System.setIn(in);
		enrolmentManagement2.update();
		assertEquals("Checking size of List after update", 14,
				enrolmentManagement2.getNumberEnrollments());



	}

	@Test
	public void testDelete() {
		EnrolmentManagement enrolmentManagement1 = new EnrolmentManagement("default.csv");
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		enrolmentManagement1.delete();
		assertEquals("Checking size of List after deleting", 14,
				enrolmentManagement1.getNumberEnrollments());
	}

	@Test
	public void testGetOne() {
		ByteArrayInputStream in = new ByteArrayInputStream("S103192\nBUS2232\n2020B".getBytes());
		System.setIn(in);
		StudentEnrolment se =enrolmentManagement.getOne();
		assertEquals("Student: S103192 Ngan Thu Vo 3/09/1998. Course: BUS2232 Business Law 3. " +
				"Semester: 2020B",se.toString());

	}

	@Test
	public void testGetAll(){
		assertEquals("Checking size of List", 15, enrolmentManagement.getNumberEnrollments());

	}



	@Test
	public void testPrintAllCourseInOneSem() {
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		enrolmentManagement.printAllCourseInOneSem();
	}
	@Test
	public void testPrintAllStudentOfOneCourseInOneSem() {
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		enrolmentManagement.printAllStudentOfOneCourseInOneSem();
	}

	@Test
	public void testPrintAllCourseOneStudentOneSem() {
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		enrolmentManagement.printAllCourseOneStudentOneSem();
	}

	@Test
	public void testPrintAllEnrollment() {
		enrolmentManagement.printAllEnrollment();
	}

	@Test
	public void	testGetSID(){
		assertEquals("S102732",student.getsID());
	}

	@Test
	public void	testSetSID(){
		student.setsID("S102733");
		assertEquals("S102733",student.getsID());
	}

	@Test
	public void	testGetsName(){
		assertEquals("Mark Duong",student.getsName());
	}

	@Test
	public void	testSetsName(){
		student.setsName("Nguyen Van A");
		assertEquals("Nguyen Van A",student.getsName());
	}

	@Test
	public void	testGetsDOB(){
		assertEquals("8/28/2001",student.getsDOB());
	}

	@Test
	public void	testSetsDOB(){
		student.setsDOB("01/01/1998");
		assertEquals("01/01/1998",student.getsDOB());
	}

	@Test
	public void testToStringStudent(){
		assertEquals("S102732 Mark Duong 01/01/1998",student.toString());
	}
	/**
	 * Test class COURSE
	 */
	// test getter setter of cID
	@Test
	public void	testGetcID(){
		assertEquals("COSC4030",course.getcID());
	}

	@Test
	public void	testSetcID(){
		course.setcID("AAA10101");
		assertEquals("AAA10101",course.getcID());
	}

	// test getter, setter of cName
	@Test
	public void	testGetcName(){
		assertEquals("Theory of Computation",course.getcName());
	}

	@Test
	public void	testSetcName(){
		course.setcName("Intro to IT");
		assertEquals("Intro to IT",course.getcName());
	}

	// test getter, setter of numOfCredits
	@Test
	public void	testGetNumOfCredits(){
		assertEquals(5,course.getNumOfCredits());
	}

	@Test
	public void	testSetNumOfCredits(){
		course.setNumOfCredits(5);
		assertEquals(5,course.getNumOfCredits());
	}

	@Test
	public void testToStringCourse(){
		assertEquals("AAA10101 Theory of Computation 5", course.toString());
	}

	/**
	 * Test Student Enrollment
	 */
	private static Student student1 = new Student("S102732", "Mark Duong", "8/28/2001");
	private static Course course1 = new Course("COSC4030", "Theory of Computation", 12);
	private static StudentEnrolment enrolment1 = new StudentEnrolment(student1,course1,"2020C");

	// test getter and setter of students property
	@Test
	public void	testGetSudent(){
		assertEquals(student1.toString(),enrolment1.getStudent().toString());
	}
	@Test
	public void	testSetStudent(){
		Student student2 = new Student("S102732", "Mark Duong", "8/28/2001");
		StudentEnrolment se = new StudentEnrolment();
		se.setStudent(student2);
		assertEquals(student2.toString(),se.getStudent().toString());
	}

	// test getter and setter of course property in Student Enrollment
	@Test
	public void	testGetCourse(){
		assertEquals(course1.toString(),enrolment1.getCourse().toString());
	}
	@Test
	public void	testSetCourse(){
		Course course2 = new Course("ABC123", "Intro to Game", 12);
		StudentEnrolment se = new StudentEnrolment();
		se.setCourse(course2);
		assertEquals(course2.toString(),se.getCourse().toString());
	}

	// test getter and setter of course property in Student Enrollment
	@Test
	public void	testGetSem(){
		assertEquals("2020C",enrolment1.getSemester());
	}
	@Test
	public void	testSetSem(){
		StudentEnrolment se = new StudentEnrolment();
		se.setSemester("2021B");
		assertEquals("2021B",se.getSemester());
	}

	// test toString of StudentEnrollment
	@Test
	public void testToStringStudentEnrollment(){
		assertEquals("Student: S102732 Mark Duong 8/28/2001. Course: COSC4030 Theory of Computation 12. Semester: 2020C",enrolment1.toString());
	}
}
