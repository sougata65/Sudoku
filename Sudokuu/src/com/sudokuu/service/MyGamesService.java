package com.sudokuu.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.sudokuu.dao.MyGamesDAO;

public class MyGamesService {
	public ArrayList<ArrayList<String>> getScores(String username) throws Exception{
		ArrayList<ArrayList<String>> mygames = new MyGamesDAO().getGames(username);
		return mygames;
	}
	
	public int getUserLevel(String username) throws Exception{
		return new MyGamesDAO().getUserLevel(username);
	}
	
	public int getUserTotalScore(String username) throws Exception{
		return new MyGamesDAO().totalScore(username);
	}
}
