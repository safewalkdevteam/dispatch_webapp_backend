package com.safewalk.dispatch_webapp.service;

import java.util.List;

import com.safewalk.dispatch_webapp.dto.TeamDto;

public interface TeamService {
    List<TeamDto> getTeams();
    TeamDto getTeam(String teamColour);
    TeamDto setTeamActive(String teamColour, boolean active);
}
