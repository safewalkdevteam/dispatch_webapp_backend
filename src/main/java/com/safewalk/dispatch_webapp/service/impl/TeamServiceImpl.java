package com.safewalk.dispatch_webapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.entity.Team;
import com.safewalk.dispatch_webapp.mapper.TeamMapper;
import com.safewalk.dispatch_webapp.repository.TeamRepository;
import com.safewalk.dispatch_webapp.service.TeamService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Override
    public List<TeamDto> getTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(TeamMapper::mapToTeamDto).collect(Collectors.toList());
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        Team team = TeamMapper.mapToTeam(teamDto);
        if (team == null) {
            throw new RuntimeException("Mapping unsuccessful");
        }
        Team savedTeam = teamRepository.save(team);
        return TeamMapper.mapToTeamDto(savedTeam);
    }

    @Override
    public void deleteTeam(String team) {
        Team teamToDelete = teamRepository.findByTeamColour(team)
            .orElseThrow(() -> new RuntimeException("Team not found"));
        teamToDelete.setActive(false);
        teamRepository.save(teamToDelete);
    }
}
