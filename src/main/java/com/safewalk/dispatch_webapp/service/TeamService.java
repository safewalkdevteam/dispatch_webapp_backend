package com.safewalk.dispatch_webapp.service;

import java.util.List;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.entity.Team;

public interface TeamService {
    List<TeamDto> getTeams();
    TeamDto createTeam(TeamDto teamDto);
    void deleteTeam(String team);
}
