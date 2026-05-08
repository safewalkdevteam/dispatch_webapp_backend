package com.safewalk.dispatch_webapp.mapper;

import com.safewalk.dispatch_webapp.dto.TeamPingDto;
import com.safewalk.dispatch_webapp.entity.TeamPing;

public class TeamPingMapper {
    public static TeamPingDto mapToTeamPingDto(TeamPing teamPing) {
        return TeamPingDto.builder()
            .id(teamPing.getId())
            .teamColour(teamPing.getTeamColour())
            .latitude(teamPing.getLatitude())
            .longitude(teamPing.getLongitude())
            .lastPing(teamPing.getLastPing())
            .sos(teamPing.getSos())
            .build();
    }

    public static TeamPing mapToTeamPing(TeamPingDto teamPingDto) {
        return TeamPing.builder()
            .id(teamPingDto.getId())
            .teamColour(teamPingDto.getTeamColour())
            .latitude(teamPingDto.getLatitude())
            .longitude(teamPingDto.getLongitude())
            .lastPing(teamPingDto.getLastPing())
            .sos(teamPingDto.getSos())
            .build();
    }
}
