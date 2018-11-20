package com.sudokuu.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.sudokuu.constants.Constants;
import com.sudokuu.utils.Config;

public class MyGamesDAO {
	public ArrayList<ArrayList<String>> getGames(String username) throws Exception{
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		ArrayList<String> levels = new ArrayList<>();
		ArrayList<String> dates = new ArrayList<>();
		ArrayList<String> scores = new ArrayList<>();
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
			pstmt = con.prepareStatement("select level,score,date from playedgames where username=? order by level");
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				levels.add(""+rs.getInt(1));
				scores.add(""+rs.getInt(2));
				dates.add(rs.getDate(3).toString());
			}
			result.add(levels);
			result.add(scores);
			result.add(dates);
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
	
	public int getUserLevel(String username) throws Exception{
		PlayDAO playDAO = new PlayDAO();
		return playDAO.getUserLevel(username);
	}
	public int totalScore(String username) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totalScore=0;
		try{
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			stmt = con.createStatement();
			pstmt = con.prepareStatement("select sum(score) from playedgames where username = ? group by username;");
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				totalScore = rs.getInt(1);
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
		return totalScore;
	}

}
