// This is the only Class you need to edit
// DO NOT EDIT SOME METHODS FROM THE CLASS - THESE ARE CLEARLY STATED
// DO NOT USE ADDITIONAL LIBRARIES TO IMPLEMENT YOUR SOLUTION

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class LeagueTable {
	private ArrayList<Team> teams;

	// Part 1 of A2: Pre-processing of input from HashMap to ArrayList
	public LeagueTable(HashMap<String, Integer> league) {

		teams = new ArrayList<Team>();

		for (String name : league.keySet()) {
			Team team = new Team();
			team.setName(name);
			team.setPoints(league.get(name));
			teams.add(team);
		}
	}
	
	// DO NOT EDIT THIS METHOD
	// You can use this method to create copies of an ArrayList, e.g. for dividing the list into two for Mergesort
	private ArrayList<Team> copyPartOfLeague(ArrayList<Team> league, int startIndex, int endIndex) {

		ArrayList<Team> copy = new ArrayList<Team>();

		for (int i = startIndex; i <= endIndex; i++) {
			Team team = new Team();
			team.setName(league.get(i).getName());
			team.setPoints(league.get(i).getPoints());
			copy.add(team);
		}
		return copy;
	}
	
	// DO NOT EDIT THIS METHOD
	// You do not need to use this method
	public ArrayList<Team> copyLeague() {
		return copyPartOfLeague(teams, 0, teams.size()-1);
	}
	
	// DO NOT EDIT THIS METHOD
	public void printTable() {
		// Print the initial (unsorted) list of teams first
		print(copyLeague());
		
		// Sort the list of teams - it should also print the recursion tree
		System.out.println("===========================");
		System.out.println("PRINTING THE RECURSION TREE");
		System.out.println("===========================");
		teams = mergeSort(copyLeague());
		
		// Print the sorted list of teams
		System.out.println("=========================");
		System.out.println("PRINTING THE LEAGUE TABLE");
		System.out.println("=========================");
		print(copyLeague());
	}
	
	// Part 1 of A2: Implement the Mergesort algorithm to sort the league
	// Part 2 of A2: It should also print the recursion tree, somewhere in mergeSort(...) or merge(...)
	// Takes as input a list and returns the sorted list
	private Stack<String> stack = new Stack<String>();
	
	public ArrayList<Team> mergeSort(ArrayList<Team> league) {
		
		stack.push("    ");
	
		for (Team team : league) {
			System.out.println(String.join("", stack) + team.getName());
		}
	
		if (league.size() <= 1) {
			stack.pop();
			return league;
		} else {
			ArrayList<Team> half1 = copyPartOfLeague(league, 0, league.size() / 2 - 1);
			ArrayList<Team> half2 = copyPartOfLeague(league, league.size() / 2, league.size() - 1);
	
			half1 = mergeSort(half1);
			half2 = mergeSort(half2);
	
			stack.pop();
	
			return merge(half1, half2);
		}
	
	}
	// Part 1 of A2: Implement the Merge algorithm as part of Mergesort
	// Takes as input two sorted lists and returns the merged sorted list
	private ArrayList<Team> merge(ArrayList<Team> half1, ArrayList<Team> half2) {
		//initialise the merged list and two pointers for the two halves
		ArrayList<Team> merged = new ArrayList<Team>();
		int index1 = 0, index2 = 0;

		//while we have not reached the end of either half, we compare the points of the teams at the current indices
		//and add trhe team with the less points to the merged list.
		while (index1 < half1.size() && index2 < half2.size()) {
	
			if (half1.get(index1).getPoints() > half2.get(index2).getPoints()) {
				merged.add(half1.get(index1));
				index1++;
			} else {
				merged.add(half2.get(index2));
				index2++;
			}
		}
		//if we have reached the end of one half, we add the rest of the other half to the merged list
		while (index1 < half1.size()) {
			merged.add(half1.get(index1));
			index1++;
		}
		while (index2 < half2.size()) {
			merged.add(half2.get(index2));
			index2++;
		}
		return merged;

	}
	
	// Part 1 of A2: Post-processing of output to print the league table
	// ONLY RECURSIVE SOLUTIONS WILL BE ACCEPTED
	private void print(ArrayList<Team> league) {
		if(league.size()==0){
			return;
		}
		else{
			System.out.print(league.get(0).getName() + ": " + league.get(0).getPoints() + "\n");
			league.remove(0);
			print(league);
		}
		
	}
	
	// Part 3 of A2: Find teams with more points than the sum of points of at least 3 teams
	// YOU SHOULD NOT SORT THE TEAMS FIRST
	// HINT: Try reducing this to a similar problem of Subset Sum
	// You need to write one additional recursive method
	// (because the parameter for total points is not known in advance, but it depends on the selected team)
	// ONLY RECURSIVE SOLUTIONS WILL BE ACCEPTED FOR THE ADDITIONAL METHOD
	public void analyseTeams(ArrayList<Team> league) {
		for (Team team : league) {
			analyseHelper(league, team, 0, 0, new ArrayList<>());
		}
	}

	/**
	 * This is my helper method for the analyseTeams method. It checks if there is a subset of at least 3 teams with less points than the target team.
	 * @param league is just a list of all teams in leage
	 * @param targetTeam the team for which we are checking using the subset sum algorithm
	 * @param index pointer for the current team in the league we are checking
	 * @param sum the sum of points of the teams in the current subset
	 * @param currentSub the subset of teams we are currently checking
	 */
	private void analyseHelper(ArrayList<Team> league, Team targetTeam, int index, int sum, List<Team> currentSub) {
		//if we have a subset of at least 3 teams and the sum of their points is less than the target team's points, we print the subset and return
		if (currentSub.size() == 3 && sum < targetTeam.getPoints()) {
			System.out.print(targetTeam.getName() + " (" + targetTeam.getPoints() + ") > ");

			//for loop to print the teams in the subset in the same order as they appear in the league
			for (int i = 0; i < currentSub.size(); i++) {
				Team team = currentSub.get(i);
				if (i > 0) {
					System.out.print(" + ");
				}
				System.out.print(team.getName() + " (" + team.getPoints() + ")");
			}
			System.out.println();
		}

		//if we have checked all teams in the league or our subset has more than 3 teams, we return
		if (index == league.size()) {
			return;
		}

		//get the current team
		Team currentTeam = league.get(index);

		//if the current team's points are not equal to the target team's points, we check two subsets: one including the current team and one excluding it
		if (!currentTeam.equals(targetTeam)) {
			//include the current team in the subset
			currentSub.add(currentTeam);
			analyseHelper(league, targetTeam, index + 1, sum + currentTeam.getPoints(), currentSub);

			//exclude the current team from the subset
			currentSub.remove(currentTeam);
			//check the next subset excluding the current team
			analyseHelper(league, targetTeam, index + 1, sum, currentSub);
		}else{
			//if the current team's points are equal to the target team's points, we check only one subset: one excluding the current team
			analyseHelper(league, targetTeam, index + 1, sum, currentSub);}
	}
}