package com.sudokuu.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import com.sudokuu.constants.Constants;
import com.sudokuu.utils.Config;

public class LeaderboardsDAO {
	public LinkedHashMap<String, Integer> getLeaderboards() throws Exception{
		LinkedHashMap<String,Integer> userScores = new LinkedHashMap<>();
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
			pstmt = con.prepareStatement("select username,sum(score) as totalScore from playedgames group by username order by totalScore desc");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				userScores.put(rs.getString(1),rs.getInt(2));
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
		
		return userScores;
	}

}
