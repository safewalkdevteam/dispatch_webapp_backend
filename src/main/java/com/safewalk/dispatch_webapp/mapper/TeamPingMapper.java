package com.safewalk.dispatch_webapp.mapper;

import com.safewalk.dispatch_webapp.dto.TeamPingDto;
import com.safewalk.dispatch_webapp.entity.TeamPing;

public class TeamPingMapper {
    public static TeamPingDto mapToTeamPingDto(TeamPing teamPing) {
        return new TeamPingDto(
            teamPing.getId(),
            teamPing.getTeam(),
            teamPing.getLatitude(),
            teamPing.getLongitude(),
            teamPing.getLastPing(),
            teamPing.getSos()
        );
    }

    public static TeamPing mapToTeamPing(TeamPingDto teamPingDto) {
        return new TeamPing(
            teamPingDto.getId(),
            teamPingDto.getTeam(),
            teamPingDto.getLatitude(),
            teamPingDto.getLongitude(),
            teamPingDto.getLastPing(),
            teamPingDto.getSos()
        );
    }
}
