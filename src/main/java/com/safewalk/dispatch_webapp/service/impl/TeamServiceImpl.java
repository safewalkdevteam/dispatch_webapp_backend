package com.safewalk.dispatch_webapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.entity.Team;
import com.safewalk.dispatch_webapp.entity.TeamPing;
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
    public TeamDto setTeamActive(String teamColour, boolean active) {
        Team team = teamRepository.findByTeamColour(teamColour)
            .orElseThrow(() -> new ResourceNotFoundException("Team not found with colour: " + teamColour));
        team.setActive(active);

        if (!active) {
            teamPingRepository.findByTeamColour(teamColour)
                .ifPresent(teamPing -> {
                    team.setTeamPing(null);
                    teamPingRepository.delete(teamPing);
                });
        }

        Team updatedTeam = teamRepository.save(team);
        return TeamMapper.mapToTeamDto(updatedTeam);
    }
}
