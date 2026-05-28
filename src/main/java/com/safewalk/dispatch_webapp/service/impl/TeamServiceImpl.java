package com.safewalk.dispatch_webapp.service.impl;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.entity.Team;
import com.safewalk.dispatch_webapp.entity.TeamPing;
import com.safewalk.dispatch_webapp.enums.TeamStatus;
import com.safewalk.dispatch_webapp.exception.ResourceNotFoundException;
import com.safewalk.dispatch_webapp.mapper.TeamMapper;
import com.safewalk.dispatch_webapp.repository.TeamPingRepository;
import com.safewalk.dispatch_webapp.repository.TeamRepository;
import com.safewalk.dispatch_webapp.service.TeamService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private TeamPingRepository teamPingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> getTeams() {
        return teamRepository.findAll()
            .stream()
            .map(TeamMapper::mapToTeamDto)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDto getTeam(String teamColour) {
        Team team = teamRepository.findByTeamColour(teamColour)
            .orElseThrow(() -> new ResourceNotFoundException("Team not found with colour: " + teamColour));
        return TeamMapper.mapToTeamDto(team);
    }

    @Override
    @Transactional
    public TeamDto setTeamStatus(String teamColour, TeamStatus status, Boolean sos) {
        Team team = teamRepository.findByTeamColour(teamColour)
            .orElseThrow(() -> new ResourceNotFoundException("Team not found with colour: " + teamColour));
        
        if (status != null) {
            team.setStatus(status);
        }
        if (sos != null) {
            team.setSos(sos);
        }

        if (team.getStatus() == TeamStatus.INACTIVE) {
            teamPingRepository.findByTeamColour(teamColour)
                .ifPresent((@NonNull TeamPing teamPing) -> {
                    team.setTeamPing(null);
                    teamPingRepository.delete(teamPing);
                });
        }

        Team updatedTeam = teamRepository.save(team);
        return TeamMapper.mapToTeamDto(updatedTeam);
    }
}
