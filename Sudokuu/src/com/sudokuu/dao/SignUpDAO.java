package com.sudokuu.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sudokuu.beans.User;
import com.sudokuu.constants.Constants;
import com.sudokuu.utils.Config;

public class SignUpDAO {
	
	public String addUser(User newUser) throws Exception{
		String name = newUser.getName();
		String username = newUser.getUsername();
		String password = newUser.getPassword();
		String email = newUser.getEmail();
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			stmt = con.createStatement();
			pstmt = con.prepareStatement("select * from userregistry where username="+"'"+username+"'");
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				return "User already present";
			}
			else{
				String query = "insert into userregistry values ((select max(id) from userregistry)+1,'"+name+"','"+username+"','"+email+"','"+password+"',0)";
				stmt.execute(query);
			}
		}catch(Exception e)
		{
			throw new Exception(e);
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException logOrIgnore) {

				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException logOrIgnore) {

				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException logOrIgnore) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException logOrIgnore) {

				}
			}
			
		}
		return "User successfully added";
	}

}
