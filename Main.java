package sis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class Main {
	// Student[] list
	static Student[] stdlist = new Student[100];
	static Scanner scanner;

	// This Function finds students
	public static Student findStudent(int number) {

		for (int i = 0; i < stdlist.length; i++) {
			if (stdlist[i] != null && stdlist[i].getStudent_id() == number) {

				return stdlist[i];

			}
		}
		return null; // if it is not found return null,if it is found it already returns
						// stdlist[i]...
	}

	// This function finds student by number and shows their information...
	public static void findStudentAndShow(int number) {
		Date date = new Date();
		String[] list = findStudent(number).getDate_of_birth().split("/");
		int year = Integer.parseInt(list[2]); // student's birth year...
		int age = date.getYear() - year + 1900;
		System.out.println("ID:" + findStudent(number).getStudent_id() + " " + "Name and Surname: "
				+ findStudent(number).getFirst_name() + " " + findStudent(number).getLast_name() + "Gender: "
				+ findStudent(number).getGender() + "Country: " + findStudent(number).getCountry() + "Age: " + age);
	}

	// This function shows all students with their information...
	public static void showStudents() {
		for (int i = 0; i < stdlist.length; i++) {
			if (stdlist[i] != null) {
				System.out.println(String.valueOf(stdlist[i].getStudent_id()) + " " + " " + stdlist[i].getFirst_name()
						+ " " + stdlist[i].getLast_name() + " " + " " + stdlist[i].getGender() + " " + " "
						+ stdlist[i].getCountry() + " " + " " + stdlist[i].getDate_of_birth());
			}
		}
	}

	// This function shows student according to given year...
	public static void showStudentsByYear(int number) { // by year
		for (int i = 0; i < stdlist.length; i++) {
			if (stdlist[i] != null) {
				String[] string = stdlist[i].getDate_of_birth().split("/");// studen's birth year...

				if (Integer.parseInt(string[2]) == number) { // [dd,mm,yyyy]
					System.out.println(String.valueOf(stdlist[i].getStudent_id()) + " " + " " + stdlist[i].getFirst_name()
							+ " " + stdlist[i].getLast_name() + " " + " " + stdlist[i].getGender() + " " + " "
							+ stdlist[i].getCountry() + " " + " " + stdlist[i].getDate_of_birth());
				}
			}
		}
	}

	public static void addStudent(int student_id, String first_name, String last_name, String date_of_birth,
			String gender, String country) {

		for (int i = 0; i < stdlist.length; i++) {
			if (stdlist[i] == null) {
				stdlist[i] = new Student(student_id, first_name, last_name, date_of_birth, gender, country);
				return;
			}
			if (i == 100) {
				System.out.println("It is full\n");
			}
		}
	}

	public static void delStudent(int number) {

		for (int i = 0; i < stdlist.length; i++) {

			if (stdlist[i] != null && stdlist[i].getStudent_id() == number) {
				stdlist[i] = null;
				System.out.println("Student deleted\n");
				return;
			}
		}
		System.out.println("Student not found\n");
	}

	public static void modifyStudent(int stdnumber) {
		if (findStudent(stdnumber) == null) {
			System.out.println("Student not found");
			return;
		}
		Scanner in = new Scanner(System.in);
		System.out.println(
				"Choose field to modify\n\n1-Student Id\n2-First name\n3-Last name\n4-Date of Birth\n5-Gender\n6-Country");

		switch (in.nextInt()) {
		case 1: // student id
			System.out.println("Enter new number");
			findStudent(stdnumber).setStudent_id(in.nextInt());
			break;

		case 2: // FirstName
			System.out.println("Enter new first name");
			in.nextLine();
			findStudent(stdnumber).setFirst_name(in.nextLine());

			break;
		case 3:
			System.out.println("Enter new Last name");
			in.nextLine(); // Scanner Bug
			findStudent(stdnumber).setLast_name(in.nextLine());
			break;
		case 4:
			System.out.println("Enter new date of birth (dd/mm/yyyy)");// I could make control here that controls type
			in.nextInt();
			findStudent(stdnumber).setDate_of_birth(in.nextLine());
			break;
		case 5:
			System.out.println("Enter new Gender");
			in.nextLine();
			findStudent(stdnumber).setGender(in.nextLine());
			break;
		case 6:
			System.out.println("Enter new Country");
			in.nextLine();
			findStudent(stdnumber).setCountry(in.nextLine());
			break;
		default:
			System.out.println("Wrong number [1-6]");
			break;
		}
	}

	// This function writes students information in a file
	public static void writeFile() {
		try {
			FileWriter filewriter = new FileWriter(
					new File("/home/metehan/eclipse-workspace/StudentSystem/src/sis/data.text")); // removed
																									// true,because
																									// every time i
																									// delete the
																									// information in
																									// file because i
																									// use load() every
																									// start

			for (int i = 0; i < stdlist.length; i++) {
				if (stdlist[i] != null) {
					filewriter.write(String.valueOf(stdlist[i].getStudent_id())); 
					filewriter.write("-");
					filewriter.write(stdlist[i].getFirst_name());
					filewriter.write("-");
					filewriter.write(stdlist[i].getLast_name());
					filewriter.write("-");
					filewriter.write(stdlist[i].getDate_of_birth());
					filewriter.write("-");
					filewriter.write(stdlist[i].getGender());
					filewriter.write("-");
					filewriter.write(stdlist[i].getCountry());
					filewriter.write("\n");
				}
			}
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readFile() {

		try {
			scanner = new Scanner(new File("/home/metehan/eclipse-workspace/StudentSystem/src/sis/data.text"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (scanner.hasNext()) {

			String[] string = scanner.nextLine().split("-");
			for (String string2 : string) {
				System.out.print(string2 + " ");
			}
			System.out.println(" ");
		}
	}

	// This function load datas in file to stdlist
	public static void load() {

		// int a = 0;
		try {
			scanner = new Scanner(new File("/home/metehan/eclipse-workspace/StudentSystem/src/sis/data.text"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (scanner.hasNext()) {

			String[] string = scanner.nextLine().split("-");
			addStudent(Integer.parseInt(string[0]), string[1], string[2], string[3], string[4], string[5]);
			/*
			 * stdlist[a].setStudent_id(Integer.parseInt(string[0]));
			 * stdlist[a].setFirst_name(string[1]); stdlist[a].setLast_name(string[2]);
			 * //gives error!!! nullpointer exception
			 * stdlist[a].setDate_of_birth(string[3]); stdlist[a].setGender(string[4]);
			 * stdlist[a].setCountry(string[5]);
			 */

			// a++;

		}
	}

	public static void main(String[] args) {
		load();
		Scanner in = new Scanner(System.in);
		int x = 1;
		while (x != 0) {

			System.out.println("\n1-Write the contents of the student array to a file\n"
					+ "2-Read student data from a file and populate the student array\n" + "3-Add a new student\n"
					+ "4-Find a student by student number, and show all the information about that student\n"
					+ "5-Show all students\n" + "6-Show all students who were born in a given year\n"
					+ "7-Modify a student record: input the student number, ask the field to modify, and get the"
					+ "new value from the user. Modify the record accordingly.\n"
					+ "8-Delete a student with a specific student number\n" + "9-Quit\n");

			switch (in.nextInt()) {

			case 1:

				writeFile();
				System.out.println("done...");
				break;
			case 2:
				readFile();
				System.out.println(" ");
				break;
			case 3:
				System.out.println("Enter Student id");
				int id = in.nextInt();
				in.nextLine();
				if(findStudent(id) != null) {
					System.out.println("There is already a student with this id");
					break;
				}
				System.out.println("Enter Firs name");
				String fname = in.nextLine();

				System.out.println("Enter Last name");
				String lname = in.nextLine();

				System.out.println("Enter Date of Birth (dd/mm/yyyy)");
				String dob = in.nextLine();

				System.out.println("Enter gender");
				String gender = in.nextLine();

				System.out.println("Enter country");
				String country = in.nextLine();

				addStudent(id, fname, lname, dob, gender, country);
				System.out.println("done...");
				break;
			case 4:

				System.out.println("Enter Student number/id");
				findStudentAndShow(in.nextInt());
				break;
			case 5:

				showStudents();
				break;
			case 6:

				System.out.println("Enter Year");
				showStudentsByYear(in.nextInt());
				break;
			case 7:

				System.out.println("Enter Student number/id");
				modifyStudent(in.nextInt());
				break;
			case 8:

				System.out.println("Enter Student number/id ");
				delStudent(in.nextInt());
				break;
			case 9:
				x = 0;
				break;

			default:
				break;
			}
		}
	}
}
