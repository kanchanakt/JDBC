//query is complete = create statement
//query is incomplete = preparedstatement
//fetching speciific data from db and when user interacts with application

package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Practice1 {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet res;

	public static void main(String[] args) {
		
		try {
			con = MyConnection.connect();
			
			String query="select *from emp where id = ?";
			pstmt=con.prepareStatement(query);
			
			System.out.println("enter employee id");
			Scanner scan = new Scanner(System.in);
			int id = scan.nextInt();
			pstmt.setInt(1, id);
			
			res = pstmt.executeQuery();
			if(res.next()) {
				System.out.println(res.getInt("id")+" "+res.getString("name")+" "+ res.getString("dsg")+" "+res.getInt("salary"));
			}else {
				System.out.println("no data found");
			}
			
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		MyConnection.close(res, pstmt, con);
		
	}
}
