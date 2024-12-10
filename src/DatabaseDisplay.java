/*
 * @author Archie Tan
 */


import java.util.Map.Entry;
import java.util.Scanner;

public class DatabaseDisplay {
	
	Scanner scan;
	String input;
	Controller controller;
	
	public DatabaseDisplay() {
		this.scan = new Scanner(System.in);
		this.controller  = new Controller(this);
		this.input = "";
	}


	// Star the program
	public void start() {
		
		System.out.println("Hello! What do you like to know about moives today? \n");
		while(!this.input.equals("Q")) {
			printQueries();
			this.input = getUserInput();
			printResult(this.input);
		}
	}
	
	
	private void printQueries() {
		for (Entry<String, String> entry : controller.getDisplayQueries().entrySet()) {
		    System.out.print(entry.getKey()+" : "+entry.getValue());
		}
	}
	
	
	private String getUserInput() {

		this.input = this.scan.nextLine();
		while (controller.getDisplayQueries().get(this.input) == null && !this.input.equals("Q")) {
			System.out.println("Input is not valid, Enter again!");
			this.input = this.scan.nextLine();
		}
		return this.input;
	}
	
	private void printResult(String input) {
		controller.getOutput(input);
	}

}
