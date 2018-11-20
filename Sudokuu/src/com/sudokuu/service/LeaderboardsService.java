package com.sudokuu.service;

import java.util.LinkedHashMap;

import com.sudokuu.dao.LeaderboardsDAO;

public class LeaderboardsService {
	public LinkedHashMap<String,Integer> getLeaderboards() throws Exception{
		LeaderboardsDAO leaderboardsGetter = new LeaderboardsDAO();
		LinkedHashMap<String,Integer> leaderboards = leaderboardsGetter.getLeaderboards();
		return leaderboards;
	}

}
