package com.iplv1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {


    private static final int ID_INDEX = 0;
    private static final int SEASON_INDEX = 1;
    private static final int CITY_INDEX = 2;
    private static final int DATE_INDEX = 3;
    private static final int TEAM1_INDEX = 4;
    private static final int TEAM2_INDEX = 5;
    private static final int TOSS_WINNER = 6 ;
    private static final int TOSS_DECISION_INDEX = 7;
    private static final int RESULT_INDEX = 8;
    private static final int DL_APPLIED_INDEX = 9;
    private static final int WINNER_INDEX = 10;
    private static final int WIN_BY_RUNS_INDEX = 11;
    private static final int WIN_BY_WICKETS_INDEX = 12;
    private static final int PLAYER_OF_MATCH_INDEX = 13;
    private static final int VENUE_INDEX = 14;
    private static final int UMPIRE1_INDEX = 15;
    private static final int UMPIRE2_INDEX = 16;


    private static final int MATCH_ID_INDEX = 0;
    private static final int INNING_INDEX = 1;
    private static final int BATTING_TEAM_INDEX = 2;
    private static final int BOWLING_TEAM_INDEX = 3;
    private static final int OVER_INDEX = 4;
    private static final int BALL_INDEX = 5;
    private static final int BATSMAN_INDEX = 6;
    private static final int NON_STRIKER_INDEX = 7;
    private static final int BOWLER_INDEX = 8;
    private static final int IS_SUPER_OVER_INDEX = 9;
    private static final int WIDE_RUNS_INDEX = 10;
    private static final int BYE_RUNS_INDEX = 11;
    private static final int LEGBYE_RUNS_INDEX = 12;
    private static final int NOBALL_RUNS_INDEX = 13;
    private static final int PENALTY_RUNS_INDEX = 14;
    private static final int BATSMAN_RUNS_INDEX = 15;
    private static final int EXTRA_RUNS_INDEX = 16;
    private static final int TOTAL_RUNS_INDEX = 17;
    private static final int PLAYER_DISMISSED_INDEX = 18;
    private static final int DISMISSAL_KIND_INDEX = 19;
    private static final int FIELDER_INDEX = 20;

    public BufferedReader fileReader(String filePath) throws IOException {
        String path=filePath;
        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        String firstLine = fileReader.readLine();
        return fileReader;
    }
    public List<Match> getmatchReader() throws IOException {

        String filePath="matches.csv";
        BufferedReader matchReader=fileReader(filePath);
        String line = "";
        List<Match> match=new ArrayList<Match>();
        while ((line = matchReader.readLine()) != null)  //returns a boolean value
        {
            String[] row = line.replaceAll(","," ,").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            Match matchList=new Match();

            matchList.setId(Integer.parseInt(row[ID_INDEX].trim()));
            matchList.setSeason(Integer.parseInt(row[SEASON_INDEX].trim()));
            matchList.setCity(row[CITY_INDEX]);
            matchList.getDate(row[DATE_INDEX]);
            matchList.setTeam1(row[TEAM1_INDEX]);
            matchList.setTeam2(row[TEAM2_INDEX]);
            matchList.setTossWinner(row[TOSS_WINNER]);
            matchList.setTossDecision(row[TOSS_DECISION_INDEX]);
            matchList.setResult(row[RESULT_INDEX]);
            matchList.setDlApplied(Integer.parseInt(row[DL_APPLIED_INDEX].trim()));
            matchList.setWinner(row[WINNER_INDEX]);
            matchList.setWinByRuns(Integer.parseInt(row[WIN_BY_RUNS_INDEX].trim()));
            matchList.setWinByWickets(Integer.parseInt(row[WIN_BY_WICKETS_INDEX].trim()));
            matchList.setPlayerOfMatch(row[PLAYER_OF_MATCH_INDEX]);
            matchList.setVenue(row[VENUE_INDEX]);
            matchList.setUmpire1(row[UMPIRE1_INDEX]);
            matchList.setUmpire2(row[UMPIRE2_INDEX]);

            match.add(matchList);
        }
//        System.out.println(match);
        return match;
    }
    public List<Deliveries> getDeliveryReader() throws IOException {
        String filePath="delivery.csv";
        BufferedReader deliReader=fileReader(filePath);
        String line = "";

        List<Deliveries> deliveries=new ArrayList<Deliveries>();
        while ((line = deliReader.readLine()) != null)  //returns a boolean value
        {
            String[] row = line.replaceAll(",",", ").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

//            System.out.print(row+" ");
            Deliveries deliList = new Deliveries();

            deliList.setMatchId(Integer.parseInt(row[MATCH_ID_INDEX].trim()));
            deliList.setInning(Integer.parseInt(row[INNING_INDEX].trim()));
            deliList.setBattingTeam(row[BATTING_TEAM_INDEX]);
            deliList.setBowlingTeam(row[BOWLING_TEAM_INDEX]);
            deliList.setOver(Integer.parseInt(row[OVER_INDEX].trim()));
            deliList.setBall(Integer.parseInt(row[BALL_INDEX].trim()));
            deliList.setBatsman(row[BATSMAN_INDEX]);
            deliList.setNonStriker(row[NON_STRIKER_INDEX]);
            deliList.setBowler(row[BOWLER_INDEX]);
            deliList.setIsSuperOver(Integer.parseInt(row[IS_SUPER_OVER_INDEX].trim()));
            deliList.setWideRuns(Integer.parseInt(row[WIDE_RUNS_INDEX].trim()));
            deliList.setByeRuns(Integer.parseInt(row[BYE_RUNS_INDEX].trim()));
            deliList.setLegbyeRuns(Integer.parseInt(row[LEGBYE_RUNS_INDEX].trim()));
            deliList.setNoballRuns(Integer.parseInt(row[NOBALL_RUNS_INDEX].trim()));
            deliList.setPenaltyRuns(Integer.parseInt(row[PENALTY_RUNS_INDEX].trim()));
            deliList.setBatsmanRuns(Integer.parseInt(row[BATSMAN_RUNS_INDEX].trim()));
            deliList.setExtraRuns(Integer.parseInt(row[EXTRA_RUNS_INDEX].trim()));
            deliList.setTotalRuns(Integer.parseInt(row[TOTAL_RUNS_INDEX].trim()));
            deliList.setPlayerDismissed(row[PLAYER_DISMISSED_INDEX]);
            deliList.setDismissalKind(row[DISMISSAL_KIND_INDEX]);
            deliList.setFielder(row[FIELDER_INDEX]);

            deliveries.add(deliList);
        }
        return deliveries;
//        System.out.println(deliveries.get(11).getFielder());
    }
}
