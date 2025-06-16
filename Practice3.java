//UPDATE QUERY 
//UPDATE EMP SET DSG=? WHERE ID=?
package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Practice3 {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	
	public static void main(String[] args) {
		try {
			con = MyConnection.connect();
			
			String query = "update emp set dsg=? where id=?";
			pstmt=con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			System.out.println("enter employee id");
			pstmt.setInt(2, scan.nextInt());
			scan.nextLine();
			
			System.out.println("enter employee dsg to be updated");
			pstmt.setString(1, scan.nextLine());
			
			int n = pstmt.executeUpdate();
			System.out.println("rows affected " + n);	
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		MyConnection.close(res, pstmt, con);
		
		
	}
}
