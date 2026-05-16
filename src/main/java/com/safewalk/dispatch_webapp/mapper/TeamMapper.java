package com.safewalk.dispatch_webapp.mapper;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.entity.Team;

public class TeamMapper {

    public static TeamDto mapToTeamDto(Team team) {
        return TeamDto.builder()
            .teamColour(team.getTeamColour())
            .status(team.getStatus())
            .build();
    }

    public static Team mapToTeam(TeamDto teamDto) {
        return Team.builder()
            .teamColour(teamDto.getTeamColour())
            .status(teamDto.getStatus())
            .build();
    }
}
