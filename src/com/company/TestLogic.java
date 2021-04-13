package com.company;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLogic {
	private static Student student = new Student("S102732", "Mark Duong", "8/28/2001");
	private static Course course = new Course("COSC4030", "Theory of Computation", 5);
	private static EnrolmentManagement enrolmentManagement = new EnrolmentManagement("default.csv");
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
		ByteArrayInputStream in = new ByteArrayInputStream("S102192\nCOSC3321\n2020B".getBytes());
		System.setIn(in);
		enrolmentManagement2.add();
		assertEquals("Checking size of List after update", 16,
				enrolmentManagement2.getNumberEnrollments());


		ByteArrayInputStream in1 = new ByteArrayInputStream("2".getBytes());
		System.setIn(in1);
		enrolmentManagement2.delete();
		assertEquals("Checking size of List after update", 15,
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
		Student student = new Student("S102732", "Mark Duong", "8/28/2001");
		Course course = new Course("COSC4030", "Theory of Computation", 5);

		StudentEnrolment enrolment = new StudentEnrolment(student,course,"2020C");
		assertEquals(enrolment.toString(),enrolmentManagement.getOne(1).toString());
	}

	@Test
	public void testGetAll(){
		assertEquals("Checking size of List", 15, enrolmentManagement.getNumberEnrollments());

	}

	@Test
	public void testCheckDuplicate(){


		StudentEnrolment enrolment = new StudentEnrolment(student,course,"2020C");
		assertTrue(enrolmentManagement.checkDuplicate(enrolment) == false);
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
		course.setNumOfCredits(12);
		assertEquals(12,course.getNumOfCredits());
	}

}
