package com.safewalk.dispatch_webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safewalk.dispatch_webapp.dto.TeamPingDto;
import com.safewalk.dispatch_webapp.service.TeamPingService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pings")
public class TeamPingController {
    private TeamPingService teamPingService;

    @PostMapping
    public ResponseEntity<TeamPingDto> createTeamPing(@RequestBody TeamPingDto teamPingDto) {
        TeamPingDto savedTeamPing = teamPingService.createOrUpdateTeamPing(teamPingDto);
        return new ResponseEntity<>(savedTeamPing, HttpStatus.OK);
    }
}
