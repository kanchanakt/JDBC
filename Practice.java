package com.skyllx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Practice {
	
	private static Connection con;
	private static ResultSet res;
	private static Statement stmt;

	public static void main(String[] args) throws SQLException {
		
		try {
			
			con = MyConnection.connect();
//			CRAETE STATEMENT
			String query = "select *from emp";
			stmt = con.createStatement();
			
//			EXECUTE STATEMENT
			res = stmt.executeQuery(query);
			
//			PROCESS RESULT
			if(res.next()) {
				while(res.next()) {
					//System.out.println(res.getInt(1)+" "+res.getString(2)+ " "+res.getString(3)+" "+res.getInt(4));
					
					System.out.println(res.getInt("id")+" "+res.getString("name")+ " "+res.getString("dsg")+" "+res.getInt("salary"));
				}
			}else {
				System.out.println("data not found");
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		MyConnection.close(res, stmt, con);	
	}

}
