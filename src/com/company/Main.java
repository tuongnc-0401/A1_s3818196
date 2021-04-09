package com.company;

import java.util.Scanner;

public class Main {
    private static EnrolmentManagement enrolmentManagement = new EnrolmentManagement();
    public static void main(String[] args) {
        mainMenu();
    }


    /**
     * Main menu. Ask user what to do
     */
    public static void mainMenu() {

        System.out.println("------- Main Menu----------");
        System.out.println("1. CRUD student enrollment");
        System.out.println("2. Reports");
        System.out.println("3. Exit.");
        System.out.println("---------------------------");
        System.out.print("Choose number (1-3): ");
        Scanner input = new Scanner(System.in);
        // ask user to choose option
        String number = input.nextLine().trim();
        while (!number.equals("1") && !number.equals("2") && !number.equals(3)){
            System.out.println("Your input is incorrect! Please choose a number from 1 to 4!");
            System.out.print("Choose number (1-3): ");
            number = input.nextLine().trim();
        }

        // if user chooses 1, run enrollment menu
        if (number.equals("1")){
            enrollmentMenu();
        }
        // if user chooses 2, run report menu
        if (number.equals("2")){
            reportMenu();
        }

        // if user chooses 3, exit the program
        if (number.equals("3")){
            return;
        }


    }
    public static void enrollmentMenu(){
        System.out.println("------- Student Enrollment Menu----------");
        System.out.println("1. View a list of enrollment.");
        System.out.println("2. Add a new enrollment.");
        System.out.println("3. Delete an enrollment.");
        System.out.println("4. Update an enrollment");
        System.out.println("5. Back to main menu");
        System.out.println("6. Exit");
        System.out.println("----------------------------------------");
        // Ask user, choose number to do
        System.out.print("Choose number (1-6): ");
        Scanner input = new Scanner(System.in);
        String number = input.nextLine().trim();
        while (!number.equals("1") && !number.equals("2") && !number.equals(3) && !number.equals(
                "4") && !number.equals("5") && !number.equals("6")){
            System.out.println("Your input is incorrect! Please choose a number from 1 to 6!");
            System.out.print("Choose number (1-6): ");
            number = input.nextLine().trim();
        }

        if (number.equals("1")){
            enrolmentManagement.printAllEnrollment();
            backToMenu(2);
        }
        if (number.equals("2")){
            enrolmentManagement.add();
            backToMenu(2);
        }
        if (number.equals("3")){
            enrolmentManagement.delete();
            backToMenu(2);
        }
        if (number.equals("4")){
            enrolmentManagement.update();
            backToMenu(2);
        }
        if (number.equals("5")){
            mainMenu();
        }
        if (number.equals("6")){
            return;
        }
    }
    public static void reportMenu(){
        System.out.println("------------ Report Menu ---------------");
        System.out.println("1. Print all courses for 1 student in 1 semester.");
        System.out.println("2. Print all students of 1 course in 1 semester.");
        System.out.println("3. Prints all courses offered in 1 semester.");
        System.out.println("4. Back to main menu");
        System.out.println("5. Exit");
        System.out.println("----------------------------------------");
        // Ask user, choose number to do
        System.out.print("Choose number (1-5): ");
        Scanner input = new Scanner(System.in);
        String number = input.nextLine();
        while (!number.equals("1") && !number.equals("2") && !number.equals(3) && !number.equals(
                "4") && !number.equals("5")){
            System.out.println("Your input is incorrect! Please choose a number from 1 to 5!");
            System.out.print("Choose number (1-5): ");
            number = input.nextLine();
        }

        if (number.equals("1")){
            enrolmentManagement.printAllCourseOneStudentOneSem();
            backToMenu(3);
        }
        if (number.equals("2")){
            enrolmentManagement.printAllStudentOfOneCourseInOneSem();
            backToMenu(3);
        }
        if (number.equals("3")){
            enrolmentManagement.printAllCourseInOneSem();
            backToMenu(3);
        }
        if (number.equals("4")){
            mainMenu();
        }
        if (number.equals("5")){
            return;
        }
    }
    public static void backToMenu(int option){
        Scanner sc = new Scanner(System.in);
        if (option == 1){
            System.out.println("Back to Main Menu? (Y/N)");
            String choice = sc.nextLine();
            if (choice.equals("Y") || choice.equals("y") ){
                mainMenu();
            } else {
                return;
            }
        }

        if (option == 2){
            System.out.println("Back to Enrollment Menu? (Y/N)");
            String choice = sc.nextLine();
            if (choice.equals("Y") || choice.equals("y") ){
                enrollmentMenu();
            } else {
                return;
            }
        }

        if (option == 3){
            System.out.println("Back to Report Menu? (Y/N)");
            String choice = sc.nextLine();
            if (choice.equals("Y") || choice.equals("y") ){
                reportMenu();
            } else {
                return;
            }
        }
    }
}
