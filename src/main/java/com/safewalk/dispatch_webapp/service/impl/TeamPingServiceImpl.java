package com.safewalk.dispatch_webapp.service.impl;

import org.springframework.stereotype.Service;

import com.safewalk.dispatch_webapp.dto.TeamPingDto;
import com.safewalk.dispatch_webapp.entity.TeamPing;
import com.safewalk.dispatch_webapp.mapper.TeamPingMapper;
import com.safewalk.dispatch_webapp.repository.TeamPingRepository;
import com.safewalk.dispatch_webapp.service.TeamPingService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamPingServiceImpl implements TeamPingService {
    private TeamPingRepository teamPingRepository;

    @Override
    public TeamPingDto createOrUpdateTeamPing(TeamPingDto teamPingDto) {
        TeamPing teamPing = TeamPingMapper.mapToTeamPing(teamPingDto);
        if (teamPing == null) {
            throw new RuntimeException("Mapping unsuccessful");
        }
        TeamPing existingTeamPing = teamPingRepository.findByTeam(teamPing.getTeam());
        if (existingTeamPing != null) {
            existingTeamPing.setLatitude(teamPing.getLatitude());
            existingTeamPing.setLongitude(teamPing.getLongitude());
            existingTeamPing.setLastPing(teamPing.getLastPing());
            existingTeamPing.setActive(teamPing.getActive());
            existingTeamPing.setSos(teamPing.getSos());
            teamPing = existingTeamPing;
        }

        TeamPing savedTeamPing = teamPingRepository.save(teamPing);
        return TeamPingMapper.mapToTeamPingDto(savedTeamPing);
    }
}
