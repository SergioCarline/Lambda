package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.next();
		
		List<Employee> list = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0],fields[1],Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than 2000.00: ");
			emails.forEach(System.out::println);
			
			System.out.print("Enter name's initial you want to filter: ");
			char initial = sc.next().charAt(0);
			
			Double salarySum = list.stream()
					.filter(e -> e.getName().charAt(0) == initial)
					.map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with '" + initial + "': " + String.format("%.2f", salarySum));		
			
			
			br.close();
		}
		catch(IOException e) {
			System.out.print("Error: " + e.getMessage());
		}
		
		
		sc.close();
	}

}
