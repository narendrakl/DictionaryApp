package com.naren.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {

	
	public static void close(ResultSet rs){
		try
		{
			if(rs!=null)
			{
				rs.close();
			}
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps1){
		try
		{
			if(ps1!=null)
			{
				ps1.close();
				
			}
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
	}

	public static void close(Connection c){
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
	}
	public static Connection getConnection(){
		
		Connection con = null;
				
		try 
		{
			Class.forName(Constants.DRIVER);
			System.out.println("Class Found");
			String url = Constants.URL;
			String uid = Constants.USER;
			String pwd = Constants.PASSWORD;
			con = DriverManager.getConnection(url, uid, pwd);
			System.out.println("Connection Sightu hey hey....!!"+con);
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	
	
}
