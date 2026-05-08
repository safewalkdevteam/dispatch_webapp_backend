package com.safewalk.dispatch_webapp.mapper;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.entity.Team;

public class TeamMapper {

    public static TeamDto mapToTeamDto(Team team) {
        return TeamDto.builder()
            .id(team.getId())
            .teamColour(team.getTeamColour())
            .isActive(team.isActive())
            .build();
    }

    public static Team mapToTeam(TeamDto teamDto) {
        return Team.builder()
            .id(teamDto.getId())
            .teamColour(teamDto.getTeamColour())
            .isActive(teamDto.isActive())
            .build();
    }
}
