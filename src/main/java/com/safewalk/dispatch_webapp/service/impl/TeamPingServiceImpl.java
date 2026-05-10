package com.safewalk.dispatch_webapp.service.impl;

import org.springframework.stereotype.Service;

import com.safewalk.dispatch_webapp.dto.TeamPingDto;
import com.safewalk.dispatch_webapp.entity.Team;
import com.safewalk.dispatch_webapp.entity.TeamPing;
import com.safewalk.dispatch_webapp.exception.ConflictException;
import com.safewalk.dispatch_webapp.exception.ResourceNotFoundException;
import com.safewalk.dispatch_webapp.mapper.TeamPingMapper;
import com.safewalk.dispatch_webapp.repository.TeamPingRepository;
import com.safewalk.dispatch_webapp.repository.TeamRepository;
import com.safewalk.dispatch_webapp.service.TeamPingService;
import com.safewalk.dispatch_webapp.websocket.LocationWebSocketHandler;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamPingServiceImpl implements TeamPingService {
    private LocationWebSocketHandler locationWebSocketHandler;
    private TeamPingRepository teamPingRepository;
    private TeamRepository teamRepository;

    @Override
    @Transactional
    public TeamPingDto createOrUpdateTeamPing(TeamPingDto teamPingDto) {
        TeamPing teamPing = TeamPingMapper.mapToTeamPing(teamPingDto);
        if (teamPing == null) {
            throw new RuntimeException("Mapping unsuccessful");
        }

        Team team = teamRepository.findByTeamColour(teamPing.getTeamColour())
            .orElseThrow(() -> new ResourceNotFoundException("Team not found with colour: " + teamPing.getTeamColour()));
        
        if (!team.isActive()) {
            throw new ConflictException("Cannot ping for an inactive team: " + teamPing.getTeamColour());
        }

        TeamPing saveTeamPing = teamPingRepository.findByTeamColour(teamPing.getTeamColour())
            .orElse(new TeamPing());

        saveTeamPing.setTeamColour(teamPing.getTeamColour());
        saveTeamPing.setLatitude(teamPing.getLatitude());
        saveTeamPing.setLongitude(teamPing.getLongitude());
        saveTeamPing.setLastPing(teamPing.getLastPing());
        saveTeamPing.setSos(teamPing.getSos());
        saveTeamPing.setTeam(team);
        
        TeamPing savedTeamPing = teamPingRepository.save(saveTeamPing);

        locationWebSocketHandler.broadcast(TeamPingMapper.mapToTeamPingDto(savedTeamPing));
        return TeamPingMapper.mapToTeamPingDto(savedTeamPing);
    }
}
