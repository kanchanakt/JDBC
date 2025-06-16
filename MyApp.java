package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MyApp {
	
	private static Connection con;
	private static Statement stmt;
	private static ResultSet res;
	static int n = 0;
	static int choice = 0;
	private static PreparedStatement pstmt;
	

	public static void main(String[] args) {
		
		while(true) {
			try {
				
				con = MyConnection.connect();
//				SELECT ALL RECORDS FROM DB
				Scanner scan = new Scanner(System.in);
				System.out.println("Enter your choice");
				System.out.println("-----------------------------------------");
				System.out.println("1.SELECT SPECIFIC RECORDS ");
				System.out.println("2.SELECT SPECIFIC RECORDS");
				System.out.println("3.ADD MULTIPLE RECORDS");
				System.out.println("4.UPDATE RECORDS");
				System.out.println("5.DELETE RECORDS");
				System.out.println("------------------------------------------");
				int choice = scan.nextInt();
				switch(choice) {
				case 1:
					System.out.println("HERE THE ALL RECORDS");
					String query1 = "select *from emp";
					stmt = con.createStatement();
					res = stmt.executeQuery(query1);
					while(res.next()) {
						System.out.println(res.getInt("id")+" "+res.getString("name")+" "+res.getString("dsg")+" "+res.getInt("salary"));
					}
					break;
					
				case 2:
					System.out.println("HERE IS THE SPECIFIC RECORDS");
					String query2 = "select *from emp where id =?";
					System.out.println("enter employee id");
					int id = scan.nextInt();
					pstmt = con.prepareStatement(query2);
					pstmt.setInt(1, id);
					
					res = pstmt.executeQuery();
					if(res.next()) {
						System.out.println(res.getInt("id")+" " +res.getString("name")+" "+res.getString("dsg")+" "+res.getInt("salary"));
					}else {
						System.out.println("no data found");
					}
					break;
					
				case 3:
					String query3 = "insert into emp values(?, ?, ?, ?)";
					pstmt = con.prepareStatement(query3);
					System.out.println("enter how many recors you want to enter");
					int number = scan.nextInt();
					con.setAutoCommit(false);
					for(int i=0 ; i<number ; i++) {
						System.out.println("enetr employeee id ");
						pstmt.setInt(1, scan.nextInt());
						scan.nextLine();
						System.out.println("enter employee name");
						pstmt.setString(2, scan.nextLine());
						System.out.println("enter the designation");
						pstmt.setString(3, scan.nextLine());
						System.out.println("enter the employee salary");
						pstmt.setInt(4, scan.nextInt());
						
						n = n + pstmt.executeUpdate();
					}
					con.commit();
					System.out.println("number of rows affected " + n);
					break;
					
				case 4:
					String query4 = "update emp set dsg =? where id =?";
					pstmt = con.prepareStatement(query4);
					System.out.println("enter the employee id to be updated");
					pstmt.setInt(2, scan.nextInt());
					scan.nextLine();
					System.out.println("enter the dsg to be updated");
					pstmt.setString(1, scan.nextLine());
					int n = pstmt.executeUpdate();
					System.out.println("rows affected " + n);
					break;
					
				case 5:
					String query5 ="delete from emp where id =?";
					pstmt= con.prepareStatement(query5);
					System.out.println("enter employee id to be deleted");
					pstmt.setInt(1, scan.nextInt());
					
					n =pstmt.executeUpdate();
					System.out.println("rows deleted " + n);
					break;
					
				default:
					System.out.println("invalid choice, please try again!");
				}
					
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
