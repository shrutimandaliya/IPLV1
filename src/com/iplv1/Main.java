package com.iplv1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import static com.iplv1.Constant.*;


public class Main {

    public static void main(String[] args) throws IOException {
        Utility utility = new Utility();
        List<Match> matchRow = utility.getmatchReader();
        List<Deliveries> deliRow = utility.getDeliveryReader();

        numOfMatchesPlayedPerYear(matchRow);
        numOfMatchesWonByTeams(matchRow);
        extraRunsConcededPerTeamIn2016(matchRow, deliRow);
        topEconomicalBowlersIn2015(matchRow, deliRow);
    }

    public static void numOfMatchesPlayedPerYear(List<Match> matchRow) {
        HashMap<Integer, Integer> matchCount = new HashMap<>();
        for (int i = 0; i < matchRow.size(); i++) {
            if (matchCount.containsKey(matchRow.get(i).getSeason())) {
                matchCount.put(matchRow.get(i).getSeason(), matchCount.get(matchRow.get(i).getSeason()) + 1);
            } else {
                matchCount.put(matchRow.get(i).getSeason(), 1);
            }
        }
        System.out.println(matchCount);
    }

    public static void numOfMatchesWonByTeams(List<Match> matchRow) {
        HashMap<String, Integer> winnerMap = new HashMap<>();
        for (int i = 0; i < matchRow.size(); i++) {
            if (winnerMap.containsKey(matchRow.get(i).getWinner())) {
                winnerMap.put(matchRow.get(i).getWinner(), winnerMap.get(matchRow.get(i).getWinner()) + 1);
            } else {
                winnerMap.put(matchRow.get(i).getWinner(), 1);
            }
        }
        System.out.println(winnerMap);
    }

    public static void extraRunsConcededPerTeamIn2016(List<Match> matchRow, List<Deliveries> deliRow) {
        List<Integer> matchId = new ArrayList<>();

        for (int i = 0; i < matchRow.size(); i++) {
            if (matchRow.get(i).getSeason() == YEAR2016) {
                matchId.add(matchRow.get(i).getId());
            }
        }

        int total = 0;
        LinkedHashMap<String, Integer> extraRunMap = new LinkedHashMap<>();
        for (int j = 0; j < deliRow.size(); j++) {
            int deliRowId = deliRow.get(j).getMatchId();
            int ExtraRun = deliRow.get(j).getExtraRuns();
            if (matchId.contains(deliRowId)) {
                if (extraRunMap.containsKey(deliRow.get(j).getBattingTeam())) {
                    total = (extraRunMap.get(deliRow.get(j).getBattingTeam())) + deliRow.get(j).getExtraRuns();
                    extraRunMap.put(deliRow.get(j).getBattingTeam(), total);
                } else {
                    extraRunMap.put(deliRow.get(j).getBattingTeam(), ExtraRun);
                }
            }
        }
        System.out.println(extraRunMap);
    }

    public static void topEconomicalBowlersIn2015(List<Match> matchRow, List<Deliveries> deliRow) {
        List<Integer> matchId = new ArrayList<>();
        for (int i = 0; i < matchRow.size(); i++) {
            if (matchRow.get(i).getSeason() == YEAR2015) {
                matchId.add(matchRow.get(i).getId());
            }
        }

        LinkedHashMap<String, Integer> overMap = new LinkedHashMap<>();
        String previousBowler = "";
        for (int j = 0; j < deliRow.size(); j++) {
            int deliRowId = deliRow.get(j).getMatchId();
            if (matchId.contains(deliRowId)) {
                String bowler = deliRow.get(j).getBowler();
                if (!bowler.equals(previousBowler)) {
                    previousBowler = bowler;

                    if (overMap.containsKey(bowler)) {
                        overMap.put(bowler, overMap.get(bowler) + 1);
                    } else {
                        overMap.put(bowler, 1);
                    }
                }
            }
        }

        LinkedHashMap<String, Integer> runMap = new LinkedHashMap<>();
        for (int k = 0; k < deliRow.size(); k++) {
            int deliRowId = deliRow.get(k).getMatchId();
            if (matchId.contains(deliRowId)) {
                String bowler = deliRow.get(k).getBowler();

                if (runMap.containsKey(bowler)) {
                    Integer count = deliRow.get(k).getTotalRuns();
                    runMap.put(bowler, runMap.get(bowler) + count);
                } else {
                    runMap.put(bowler, deliRow.get(k).getTotalRuns());
                }
            }
        }
        LinkedHashMap<String, Double> average = new LinkedHashMap<String, Double>();
        double division = 0d;
        for (String bowler : runMap.keySet()) {
            Integer valrun = runMap.get(bowler);
            Integer valover = overMap.get(bowler);

            division = (double) valrun / valover;

            average.put(bowler, division);
        }

        double min = Double.MAX_VALUE;
        String ballerName = "";
        for (String name : average.keySet()) {
            double val = average.get(name);
            if (val < min) {
                min = val;
                ballerName = name;
            }
        }
        System.out.println(ballerName + " " + min);
    }
}
