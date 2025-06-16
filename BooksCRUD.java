package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BooksCRUD {
	
	private Connection con;
	private PreparedStatement pstmt;
	static int n = 0;
	Scanner scan = new Scanner(System.in);
	private ResultSet res;
	
	
	
	public void selectBook() {
		try {
			con = MyConnection.connect();
			String query = "select *from books where id=?";
			pstmt =con.prepareStatement(query);
			pstmt.setInt(1, scan.nextInt());
			res = pstmt.executeQuery();
			if(res.next()) {
				System.out.println(res.getInt("id")+" "+res.getString("title")+" "+res.getString("author")+ " "+res.getInt("year"));
			}else {
				System.out.println("no data found");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addBook() {
		try {
			con = MyConnection.connect();
			String query1 = "insert into books values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(query1);
			
			System.out.println("how many times you want to add books");
			int number = scan.nextInt();
			con.setAutoCommit(false);
			for(int i=0; i<number; i++) {
				System.out.println("Enter the Book ID");
				pstmt.setInt(1, scan.nextInt());
				scan.nextLine();
				System.out.println("Enter the Book title");
				pstmt.setString(2, scan.nextLine());
				System.out.println("Enter the Book author");
				pstmt.setString(3, scan.nextLine());
				System.out.println("Enter the Year of Book");
				pstmt.setInt(4, scan.nextInt());
				n = n+ pstmt.executeUpdate();
			}
			con.commit();
			System.out.println("number of rows affected " + n);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook() {
		try {
			con = MyConnection.connect();
			
			String query2 = "update books set year =? where id=?";
			pstmt=con.prepareStatement(query2);
			System.out.println("enter the year to be updated");
			pstmt.setInt(1, scan.nextInt());
			System.out.println("enter the book ID");
			pstmt.setInt(2, scan.nextInt());
			
			n = pstmt.executeUpdate();
			System.out.println("row updated " +n );
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook() {
		try {
			con = MyConnection.connect();
			String query3 = "delete from books where id =?";
			pstmt = con.prepareStatement(query3);
			System.out.println("enter the book Id to be deleted");
			pstmt.setInt(1, scan.nextInt());
			
			n = pstmt.executeUpdate();
			System.out.println("numbe of rows deleted " + n);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		MyConnection.close(res, pstmt, con);
	}
	
}