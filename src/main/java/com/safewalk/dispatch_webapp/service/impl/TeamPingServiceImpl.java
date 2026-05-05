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

        TeamPing saveTeamPing = teamPingRepository.findByTeam(teamPing.getTeam()).orElse(new TeamPing());

        saveTeamPing.setTeam(teamPing.getTeam());
        saveTeamPing.setLatitude(teamPing.getLatitude());
        saveTeamPing.setLongitude(teamPing.getLongitude());
        saveTeamPing.setLastPing(teamPing.getLastPing());
        saveTeamPing.setActive(teamPing.getActive());
        saveTeamPing.setSos(teamPing.getSos());
        
        TeamPing savedTeamPing = teamPingRepository.save(saveTeamPing);
        return TeamPingMapper.mapToTeamPingDto(savedTeamPing);
    }

    @Override
    public void deleteTeamPing(TeamPingDto teamPingDto) {
        TeamPing teamPing = teamPingRepository.findByTeam(teamPingDto.getTeam()).orElseThrow(
            () -> new RuntimeException("Team ping not found for team: " + teamPingDto.getTeam()));
        if (teamPing != null) {
            teamPingRepository.delete(teamPing);
        }
    }
}
