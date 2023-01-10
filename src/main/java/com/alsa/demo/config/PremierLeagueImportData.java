package com.alsa.demo.config;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.Team;
import com.alsa.demo.repositories.LeagueDao;
import com.alsa.demo.repositories.LeaguePositionDao;
import com.alsa.demo.repositories.TeamDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PremierLeagueImportData {

    public PremierLeagueImportData() {}

    public void importData(TeamDao teamDao, LeagueDao leagueDao, LeaguePositionDao leaguePositionDao) {
        importLeague(leagueDao);
        importTeams(teamDao);
        URL file = this.getClass().getResource("/positioninformation.txt");
        Stream<String> fileLines = readFile(file);
        List<String> fileLineList = fileLines.toList();
        for(String f : fileLineList) {
            String[] splitInput = f.split(" ");
            int teamId = Integer.parseInt(splitInput[0]);
            int leagueId = Integer.parseInt(splitInput[1]);
            int position = Integer.parseInt(splitInput[2]);
            int matchesPlayed = Integer.parseInt(splitInput[3]);
            int points = Integer.parseInt(splitInput[4]);
            int goalsFor = Integer.parseInt(splitInput[5]);
            int goalsAgainst = Integer.parseInt(splitInput[6]);
            leaguePositionDao.save(new LeaguePosition(teamDao.getReferenceById(teamId), leagueDao.getReferenceById(leagueId), position, matchesPlayed, points, goalsFor, goalsAgainst));
        }
    }

    private void importLeague(LeagueDao dao) {
        dao.save(new League("Premier League"));
    }

    private void importTeams(TeamDao dao) {
        URL file = this.getClass().getResource("/teamnames.txt");
        Stream<String> fileLines = readFile(file);
        fileLines.forEach(e -> dao.save(new Team(e)));
    }

    private static Stream<String> readFile(URL url) {
        File file = new File(url.getFile());
        try {
            BufferedReader br = new BufferedReader( new FileReader(file) );
            return br.lines();
        }
        catch(FileNotFoundException e) {
            System.out.println("file not found");
        }
        catch(Exception e) {
            System.out.println("Another error");
        }
        return null;
    }

}
