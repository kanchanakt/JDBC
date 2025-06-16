package com.skyllx.jdbc;

import java.util.Scanner;

public class BookMain {
	
	public static void main(String[] args) {
		   Scanner scan = new Scanner(System.in);
	        BooksCRUD crud = new BooksCRUD();

	        while (true) {
	            System.out.println("\n====== Library Book Management ======");
	            System.out.println("1. Add Book");
	            System.out.println("2. View Book by ID");
	            System.out.println("3. Update Book Year by ID");
	            System.out.println("4. Delete Book by ID");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();

	            switch (choice) {
	                case 1:
	                    crud.addBook();
	                    break;
	                case 2:
	                    crud.selectBook();
	                    break;
	                case 3:
	                    crud.updateBook();
	                    break;
	                case 4:
	                    crud.deleteBook();
	                    break;
	                case 5:
	                    System.out.println("Exiting... Thank you!");
	                    scan.close();
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	}

