import static org.junit.Assert.assertEquals;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

public class LeagueTableTest {

    @Test
    public void testLeagueTableCreation() {
        HashMap<String, Integer> leagueMap = new LinkedHashMap<>();
        leagueMap.put("Team A", 30);
        leagueMap.put("Team B", 25);
        leagueMap.put("Team C", 20);

        LeagueTable leagueTable = new LeagueTable(leagueMap);

        ArrayList<Team> teams = leagueTable.copyLeague();
        assertEquals("Team A", teams.get(0).getName());
        assertEquals(30, teams.get(0).getPoints());
        assertEquals("Team B", teams.get(1).getName());
        assertEquals(25, teams.get(1).getPoints());
        assertEquals("Team C", teams.get(2).getName());
        assertEquals(20, teams.get(2).getPoints());
    }

    @Test
    public void testMergeSort() {
        HashMap<String, Integer> leagueMap = new LinkedHashMap<>();
        leagueMap.put("Team A", 15);
        leagueMap.put("Team B", 25);
        leagueMap.put("Team C", 20);
        leagueMap.put("Team D", 30);

        LeagueTable leagueTable = new LeagueTable(leagueMap);
        ArrayList<Team> sortedTeams = leagueTable.mergeSort(leagueTable.copyLeague());

        assertEquals("Team D", sortedTeams.get(0).getName());
        assertEquals(30, sortedTeams.get(0).getPoints());
        assertEquals("Team B", sortedTeams.get(1).getName());
        assertEquals(25, sortedTeams.get(1).getPoints());
        assertEquals("Team C", sortedTeams.get(2).getName());
        assertEquals(20, sortedTeams.get(2).getPoints());
        assertEquals("Team A", sortedTeams.get(3).getName());
        assertEquals(15, sortedTeams.get(3).getPoints());
    }

    @Test
    public void testAnalyseTeamsSmall() {
		List<String> capturedPrints = new ArrayList<>();

		 // Save the original System.out
        PrintStream originalOut = System.out;

        // Create a custom OutputStream to capture prints
        OutputStream captureStream = new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                char c = (char) b;
                String value = Character.toString(c);
                if (value.equals("\n")) {
                    capturedPrints.add(buffer.toString());
                    buffer.delete(0, buffer.length());
                } else {
                    buffer.append(c);
                }
            }
        };

        PrintStream customPrintStream = new PrintStream(captureStream);
        // Redirect System.out to our custom PrintStream
        System.setOut(customPrintStream);

		// This is the test input to the program 
		HashMap<String, Integer> leagueInfo = new HashMap<String, Integer>(); 
		leagueInfo.put("Scotland", 15);
		leagueInfo.put("Bulgaria", 2);
		leagueInfo.put("England", 16);
		leagueInfo.put("Andorra", 2);
		leagueInfo.put("Wales", 10);

		LeagueTable league = new LeagueTable(leagueInfo); 
			
		league.analyseTeams(league.copyLeague());

        // Restore the original System.out
        System.setOut(originalOut);
        assertEquals(capturedPrints.size(), 12);

    }

    @Test
    public void testAnalyseTeamsMeduim() {
		List<String> capturedPrints = new ArrayList<>();

		 // Save the original System.out
        PrintStream originalOut = System.out;

        // Create a custom OutputStream to capture prints
        OutputStream captureStream = new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                char c = (char) b;
                String value = Character.toString(c);
                if (value.equals("\n")) {
                    capturedPrints.add(buffer.toString());
                    buffer.delete(0, buffer.length());
                } else {
                    buffer.append(c);
                }
            }
        };

        PrintStream customPrintStream = new PrintStream(captureStream);
        // Redirect System.out to our custom PrintStream
        System.setOut(customPrintStream);

		// This is the test input to the program 
		HashMap<String, Integer> leagueInfo = new HashMap<String, Integer>(); 
		leagueInfo.put("Scotland", 15);
		leagueInfo.put("Bulgaria", 2);
		leagueInfo.put("England", 16);
		leagueInfo.put("Andorra", 2);
		leagueInfo.put("Wales", 10);
		leagueInfo.put("Malta", 0);
		leagueInfo.put("N.Ireland", 6);
		leagueInfo.put("Finland", 12);

		LeagueTable league = new LeagueTable(leagueInfo); 
			
		league.analyseTeams(league.copyLeague());

        // Restore the original System.out
        System.setOut(originalOut);

        System.out.println(capturedPrints.size());

        assertEquals(capturedPrints.size(), 276);
    }

    @Test
    public void testAnalyseTeamsLarge() {
		List<String> capturedPrints = new ArrayList<>();

		 // Save the original System.out
        PrintStream originalOut = System.out;

        // Create a custom OutputStream to capture prints
        OutputStream captureStream = new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                char c = (char) b;
                String value = Character.toString(c);
                if (value.equals("\n")) {
                    capturedPrints.add(buffer.toString());
                    buffer.delete(0, buffer.length());
                } else {
                    buffer.append(c);
                }
            }
        };

        PrintStream customPrintStream = new PrintStream(captureStream);
        // Redirect System.out to our custom PrintStream
        System.setOut(customPrintStream);

		// This is the test input to the program 
		HashMap<String, Integer> leagueInfo = new HashMap<String, Integer>(); 
		leagueInfo.put("Scotland", 15);
		leagueInfo.put("Bulgaria", 2);
		leagueInfo.put("England", 16);
		leagueInfo.put("Andorra", 2);
		leagueInfo.put("Wales", 10);
		leagueInfo.put("Malta", 0);
		leagueInfo.put("N.Ireland", 6);
		leagueInfo.put("Finland", 12);
        leagueInfo.put("India", 5);
		leagueInfo.put("Japan", 8);

		LeagueTable league = new LeagueTable(leagueInfo); 
			
		league.analyseTeams(league.copyLeague());

        // Restore the original System.out
        System.setOut(originalOut);

        System.out.println(capturedPrints.size());

        assertEquals(capturedPrints.size(), 936);
    }

}
