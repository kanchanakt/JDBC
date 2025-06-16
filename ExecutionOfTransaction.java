package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ExecutionOfTransaction {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	static int n = 0;
	
	public static void main(String[] args) {
		
		try {
			con = MyConnection.connect();
			
			String query = "insert into emp(id, name, dsg, salary) values (?, ?, ?, ?)";
			pstmt=con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			System.out.println("enter how many students you want to enter");
			int number = scan.nextInt();
			
			con.setAutoCommit(false);
			for(int i=0; i<number; i++)	{
				System.out.println("enter employee id");
				pstmt.setInt(1, scan.nextInt());
				scan.nextLine();
				
				System.out.println("enter employee name");
				pstmt.setString(2, scan.nextLine());
				
				System.out.println("enter employee dsg");
				pstmt.setString(3, scan.nextLine());
				
				System.out.println("enter employee salary");
				pstmt.setInt(4, scan.nextInt());
				
				
				n = n + pstmt.executeUpdate();
			}
			con.commit();
			System.out.println("rows affected " + n);
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		MyConnection.close(res, pstmt, con);
		
	}
}
