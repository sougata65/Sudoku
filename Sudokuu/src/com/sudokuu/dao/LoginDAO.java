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

public class LoginDAO {

	public User authenticate(String username, String password) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			String query = "";
			stmt = con.prepareStatement("select * from userregistry");
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(username)) {
					if (rs.getString("password").equals(password)) {
						User user = new User();
						user.setName(rs.getString("fullname"));
						user.setUsername(username);
						int usertype = rs.getInt("usertype");
						String usertypeStr = "";
						if (usertype == 0) {
							usertypeStr = Constants.USERROLEUSER;
						} else {
							usertypeStr = Constants.USERROLEADMIN;
						}
						user.setUserrole(usertypeStr);
						return user;
					}
					break;
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException logOrIgnore) {

				}
			}
		}
		return null;

	}

}
