package com.sudokuu.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sudokuu.constants.Constants;
import com.sudokuu.utils.Config;

public class PlayDAO {
	public String saveGame(String username,int level,String savedgame) throws Exception{
		int score=0;
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
			String queryIfPresent = "select * from playedgames where username=? and level=?";
			pstmt = con.prepareStatement(queryIfPresent);
			pstmt.setString(1, username);
			pstmt.setInt(2, level);
			rs = pstmt.executeQuery();
			String query;
			if(rs.next())
			{
				query="update playedgames set data = '"+savedgame+"' where username='"+username+"' and level="+level;
			}
			else{
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String date = sdf.format(today);
				query="insert into playedgames values ((select max(gameid) from playedgames)+1,'"+username+"','"+savedgame+"',"+score+",'"+date+"',"+level+")";
			}
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

		return "Saved successfully";
	}
	
	public String saveSolvedGame(String username,int level,String savedgame) throws Exception{
		int score=level;
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
			String queryIfPresent = "select * from playedgames where username=? and level=?";
			pstmt = con.prepareStatement(queryIfPresent);
			pstmt.setString(1, username);
			pstmt.setInt(2, level);
			rs = pstmt.executeQuery();
			String query;
			if(rs.next())
			{
				query="update playedgames set data = '"+savedgame+"' where username='"+username+"' and level="+level;
			}
			else{
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String date = sdf.format(today);
				query="insert into playedgames values ((select max(gameid) from playedgames)+1,'"+username+"','"+savedgame+"',"+score+",'"+date+"',"+level+")";
			}
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

		return "Saved successfully";
	}
	
	public int getMaxLevelAdded() throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int maxlevel=0;
		try{
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			stmt = con.createStatement();
			pstmt = con.prepareStatement("select max(level) from gametemplates");
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				maxlevel = rs.getInt(1);
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
		return maxlevel;
	}

	
	public int getUserLevel(String username) throws Exception{
		int level=1;
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
			pstmt = con.prepareStatement("select max(level) from playedgames where username=?");
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				level = rs.getInt(1);
			}
			if(level==0)
			{
				level=1;
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
		return level;
	}
	
	public String[] loadGame(String username,int level) throws Exception{
		String[] result = new String[2];
		Connection con=null;
		Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("org.postgresql.Driver");
			Driver driver = new org.postgresql.Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(Config.getConfigProperty().getProperty(Constants.DATABASEURL),Config.getConfigProperty().getProperty(Constants.DATABASEUSERNAME),Config.getConfigProperty().getProperty(Constants.DATABASEPASSWORD));
			stmt = con.createStatement();
			if(level!=0)
			{
				pstmt = con.prepareStatement("select data from playedgames where username=? and level=?");
				pstmt.setString(1, username);
				pstmt.setInt(2, level);
				String savedgame=null;
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					savedgame = rs.getString(1);
				}
				result[1]=savedgame;
				pstmt = con.prepareStatement("select template from gametemplates where level=?");
				pstmt.setInt(1, level);
				rs=pstmt.executeQuery();
				String template=null;
				while(rs.next())
				{
					template = rs.getString(1);
				}
				result[0]=template;
			}
			else{
				pstmt = con.prepareStatement("select template from gametemplates where level=1");
				rs = pstmt.executeQuery();
				String template=null;
				while(rs.next())
				{
					template = rs.getString(1);
				}
				result[0]=template;
				result[1]=template;
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


}
