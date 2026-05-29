package com.safewalk.dispatch_webapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.enums.TeamStatus;
import com.safewalk.dispatch_webapp.service.TeamService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;
    
    @GetMapping
    public ResponseEntity<List<TeamDto>> getTeams() {
        return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    }

    @GetMapping("/{team}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable String team) {
        return new ResponseEntity<>(teamService.getTeam(team), HttpStatus.OK);
    }
    

    @PatchMapping("/{team}/status")
    public ResponseEntity<TeamDto> setTeamStatus(
        @PathVariable String team,
        @RequestParam(required = true) TeamStatus status
    ) {
        return new ResponseEntity<>(teamService.setTeamStatus(team, status), HttpStatus.OK);
    }
}
