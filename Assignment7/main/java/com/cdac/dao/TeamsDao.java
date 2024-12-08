package com.cdac.dao;

import java.util.List;

import com.cdac.entitites.Teams;

public interface TeamsDao {
 String signUpTeams(Teams team);

public List<Teams> getAllTeams();
public List <Teams> getAllTeamsAgeAvg(int age, double avg);
public List<Teams> displayOwnerNameAndAbbreviations(int age, double avg);

public String updateTeamMaxAge(String name, int age);
public String deleteTeam(Long teamid);



}
