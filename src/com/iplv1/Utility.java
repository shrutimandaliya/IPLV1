package com.iplv1;

import static com.iplv1.Constant.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    public BufferedReader fileReader(String filePath) throws IOException {
        String path=filePath;
        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        String firstLine = fileReader.readLine();
        return fileReader;
    }

    public List<Match> getmatchReader() throws IOException {

        BufferedReader matchReader=fileReader(MATCHPATH);
        String line = "";
        List<Match> match=new ArrayList<Match>();
        while ((line = matchReader.readLine()) != null)
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
        return match;
    }

    public List<Deliveries> getDeliveryReader() throws IOException {

        BufferedReader deliReader=fileReader(DELIVERYPath);
        String line = "";

        List<Deliveries> deliveries=new ArrayList<Deliveries>();
        while ((line = deliReader.readLine()) != null)  //returns a boolean value
        {
            String[] row = line.replaceAll(",",", ").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

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
    }
}
