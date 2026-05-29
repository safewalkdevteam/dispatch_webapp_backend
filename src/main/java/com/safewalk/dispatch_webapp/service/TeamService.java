package com.safewalk.dispatch_webapp.service;

import java.util.List;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.enums.TeamStatus;

public interface TeamService {
    List<TeamDto> getTeams();
    TeamDto getTeam(String teamColour);
    TeamDto setTeamStatus(String teamColour, TeamStatus status);
}
