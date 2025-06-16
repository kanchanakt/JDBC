
//DELETE QUERY

package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Practice4 {
	
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	
	public static void main(String[] args) {
		try {
			con = MyConnection.connect();
			
			String query = "delete from emp where id=?";
			pstmt=con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			System.out.println("enter employee id to be deleted");
			pstmt.setInt(1, scan.nextInt());
			scan.nextLine();
			
			int n = pstmt.executeUpdate();
			System.out.println("rows affected " + n);	
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		MyConnection.close(res, pstmt, con);
		
		
	}
}
