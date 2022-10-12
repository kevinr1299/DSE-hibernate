package com.dse.hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Console {

	public static void get_all(StudentManager sm) {
		System.out.println("\n\tSTUDENTS");
		List<Student> students = sm.get_all();
		for (Student student : students) {
			System.out.print(student);
		}
	}

	public static void get_by_id(StudentManager sm, Scanner scanner) {
		System.out.println("\n\tSEARCH STUDENT BY ID");
		System.out.print("Insert id to search: ");
		Integer id = scanner.nextInt();
		Student student = sm.get_by_id(id);
		if(student == null) {
			System.out.println("Student not found");
			return;
		}
		System.out.println(student);
	}
	
	public static void delete(StudentManager sm, Scanner scanner, BufferedReader buffer) throws IOException {
		System.out.println("\n\tDELETE A STUDENT");
		System.out.print("Id of the student to delete: ");
		Integer id = scanner.nextInt();
		System.out.print("Are you sure that you want to delete the student with id "+ id.toString() + "? (Y/n) ");
		String answerd = buffer.readLine();
		if(answerd.equalsIgnoreCase("y")) {
			boolean deleted = sm.delete(id);
			if(deleted) {
				System.out.println("Student deleted");
			} else {
				System.out.println("An error occures trying to delete the student");
			}
		}
	}

	public static void create(StudentManager sm, BufferedReader buffer) throws IOException {
		System.out.println("\n\tCREATE A STUDENT");
		System.out.print("Name: ");
		String name = buffer.readLine();
		System.out.print("Surname: ");
		String surname = buffer.readLine();
		System.out.print("Code: ");
		String code = buffer.readLine();
		System.out.print("Email: ");
		String mail = buffer.readLine();
		System.out.print("Phone: ");
		String phone = buffer.readLine();
		Student student = new Student(name, surname, mail, phone, code);
		boolean created = sm.create(student);
		if(created) {
			System.out.println("Student created");
		} else {
			System.out.println("An error occurs trying to create a student");
		}
	}
	
	public static void update(StudentManager sm, Scanner scanner, BufferedReader buffer) throws IOException {
		System.out.println("\n\tUPDATE A STUDENT");
		System.out.print("Insert id to update: ");
		Integer id = scanner.nextInt();
		Student student = sm.get_by_id(id);
		if(student == null) {
			System.out.println("Student not found");
			return;
		}
		System.out.println("If you want to skip the change of any attribute press enter");
		System.out.print("New name(" + student.getName() + "): ");
		String name = buffer.readLine();
		if(name != "") {
			student.setName(name);
		}
		System.out.print("New surname(" + student.getSurname() + "): ");
		String surname = buffer.readLine();
		if(surname != "") {
			student.setSurname(surname);
		}
		System.out.print("New email(" + student.getEmail() + "): ");
		String mail = buffer.readLine();
		if(mail != "") {
			student.setEmail(mail);
		}
		System.out.print("New phone(" + student.getPhone() + "): ");
		String phone = buffer.readLine();
		if(phone != "") {
			student.setPhone(phone);
		}
		boolean updated = sm.update(student);
		if(updated) {
			System.out.println("Student updated");
		} else {
			System.out.println("An error occurs trying to update the student");
		}
	}
	
	public static void main(String[] args) throws IOException {
		StudentManager sm = new StudentManager();
		Scanner scanner = new Scanner(System.in);
		int opt = 0;
		while(opt != 6) {
			System.out.println("\n\t******MENU******");
			System.out.println("1. Get all the students");
			System.out.println("2. Get a student by id");
			System.out.println("3. Create a new student");
			System.out.println("4. Update a student");
			System.out.println("5. Delete a student");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");
			opt = scanner.nextInt();
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader buffer = new BufferedReader(in);
			switch(opt) {
				case 1:
					Console.get_all(sm);
					break;
				case 2:
					Console.get_by_id(sm, scanner);
					break;
				case 3:
					Console.create(sm, buffer);
					break;
				case 4:
					Console.update(sm, scanner, buffer);
					break;
				case 5:
					Console.delete(sm, scanner, buffer);
					break;
			}
			System.in.read();
		}
		scanner.close();
	}
}
