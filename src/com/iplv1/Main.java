package com.iplv1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
            Utility u=new Utility();
        List<Match> matchRow=u.getmatchReader();
        List<Deliveries> deliRow = u.getDeliveryReader();

        numOfMatchesPlayiedPerYear(matchRow);
        numOfMatchesWonOfAllTeamsPerYear(matchRow);
        extraRunsConcededPerTeamIn2016(matchRow,deliRow);
        topEconomicalBowlersIn2015(matchRow,deliRow);
    }
    public static void numOfMatchesPlayiedPerYear(List<Match> matchRow)
    {
        HashMap<Integer,Integer> yearMap=new HashMap<>();
        for(int i=0;i<matchRow.size();i++)
        {
            if(yearMap.containsKey(matchRow.get(i).getSeason()))
            {
                yearMap.put(matchRow.get(i).getSeason(),yearMap.get(matchRow.get(i).getSeason())+1);
            }
            else
            {
                yearMap.put(matchRow.get(i).getSeason(),1);
            }
        }
        System.out.println(yearMap);
    }

    public static void numOfMatchesWonOfAllTeamsPerYear(List<Match> matchRow)
    {
        HashMap<String,Integer> winnerMap=new HashMap<>();
        for(int i=0;i<matchRow.size();i++)
        {
            if(winnerMap.containsKey(matchRow.get(i).getWinner()))
            {
                winnerMap.put(matchRow.get(i).getWinner(),winnerMap.get(matchRow.get(i).getWinner())+1);
            }
            else
            {
                winnerMap.put(matchRow.get(i).getWinner(),1);
            }
        }
        System.out.println(winnerMap);
    }

    public static void extraRunsConcededPerTeamIn2016(List<Match> matchRow, List<Deliveries> deliRow)
    {
        List<Integer> matchId=new ArrayList<>();
        int year=2016;
        for(int i=0;i<matchRow.size();i++)
        {
            if(matchRow.get(i).getSeason()==year)
            {
                matchId.add(matchRow.get(i).getId());
            }
        }

        int total=0;
        LinkedHashMap<String,Integer> extraRunMap=new LinkedHashMap<>();
        for (int j=0;j<deliRow.size();j++)
        {
            int deliRowId=deliRow.get(j).getMatchId();
            int ExtraRun=deliRow.get(j).getExtraRuns();
            if(matchId.contains(deliRowId))
            {
                if(extraRunMap.containsKey(deliRow.get(j).getBattingTeam()))
                {
                    total=(extraRunMap.get(deliRow.get(j).getBattingTeam()))+deliRow.get(j).getExtraRuns();
                    extraRunMap.put(deliRow.get(j).getBattingTeam(),total);
                }
                else
                {
                    extraRunMap.put(deliRow.get(j).getBattingTeam(),ExtraRun);
                }
            }
        }
        System.out.println(extraRunMap);
    }
    public static void  topEconomicalBowlersIn2015(List<Match> matchRow, List<Deliveries> deliRow)
    {
        List<Integer> matchId=new ArrayList<>();
        int year=2015;
        for(int i=0;i<matchRow.size();i++)
        {
            if(matchRow.get(i).getSeason()==year)
            {
                matchId.add(matchRow.get(i).getId());
            }
        }

        LinkedHashMap<String,Integer> overMap=new LinkedHashMap<>();
        String previousBowler="";
        for(int j=0;j<deliRow.size();j++)
        {
            int deliRowId=deliRow.get(j).getMatchId();
            if(matchId.contains(deliRowId))
            {
                String bowler=deliRow.get(j).getBowler();

                if(!bowler.equals(previousBowler))
                {
                    previousBowler=bowler;

                    if(overMap.containsKey(bowler))
                    {
                        overMap.put(bowler,overMap.get(bowler)+1);
                    }
                    else
                    {
                        overMap.put(bowler,1);
                    }
                }
            }
        }


        LinkedHashMap<String,Integer> runMap=new LinkedHashMap<>();
        for (int k=0;k<deliRow.size();k++)
        {
            int deliRowId=deliRow.get(k).getMatchId();
            if(matchId.contains(deliRowId))
            {
                String bowler=deliRow.get(k).getBowler();

                if(runMap.containsKey(bowler))
                {
                    Integer count=deliRow.get(k).getTotalRuns();
                    runMap.put(bowler,runMap.get(bowler)+count);
                }
                else
                {
                    runMap.put(bowler,deliRow.get(k).getTotalRuns());
                }
            }
        }
//        System.out.println(runMap);

        LinkedHashMap<String,Double> average=new LinkedHashMap<String, Double>();
        double division=0d;
        for(String bowler:runMap.keySet() )
        {
            Integer valrun=runMap.get(bowler);
            Integer valover=overMap.get(bowler);

            division=(double)valrun/valover;

            average.put(bowler,division);
        }

        double min=Double.MAX_VALUE;
        String ballerName="";
        for (String name:average.keySet())
        {
            double val=average.get(name);
//            System.out.println(val);
            if(val<min)
            {
                min=val;
                ballerName=name;
            }
        }
        System.out.println(ballerName+" "+min);
     }
}
