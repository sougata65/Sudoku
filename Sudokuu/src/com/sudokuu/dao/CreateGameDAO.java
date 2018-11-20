package com.sudokuu.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sudokuu.constants.Constants;
import com.sudokuu.utils.Config;
import com.sudokuu.utils.MatrixOperations;

public class CreateGameDAO {
	public boolean isLevelAlreadyPresent(int level) throws Exception{
		Connection con =null;
		Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result=false;
		try{
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			stmt = con.createStatement();
			String query = "select * from gametemplates where level="+level;
			rs=stmt.executeQuery(query);
			if(rs.next())
			{
				 result=true;
			}
		}catch(Exception e)
		{
			throw new Exception(e);
		}finally
		{
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
		return result;

	}
	public int getNextLevelToBeAdded() throws Exception{
		Connection con =null;
		Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int level=0;
		try{
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			stmt = con.createStatement();
			String query = "select max(level) from gametemplates";
			rs=stmt.executeQuery(query);
			if(rs.next())
			{
				 level = rs.getInt(1);
			}
		}catch(Exception e)
		{
			throw new Exception(e);
		}finally
		{
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
		return level+1;

	}
	public String addTemplate(int level,int[][] matrix) throws Exception{
		if(isLevelAlreadyPresent(level))
		{
			return "Template for level already present";
		}
		MatrixOperations operations = new MatrixOperations();
		String template = operations.matrixToTemplate(matrix);
		Connection con =null;
		Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			stmt = con.createStatement();
			String query = "insert into gametemplates values ("+level+",'"+template+"')";
			stmt.execute(query);
		}catch(Exception e)
		{
			throw new Exception(e);
		}finally
		{
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
		return "Successfully Added";
	}
	
	

}
