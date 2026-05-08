package com.safewalk.dispatch_webapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safewalk.dispatch_webapp.dto.TeamDto;
import com.safewalk.dispatch_webapp.entity.Team;
import com.safewalk.dispatch_webapp.service.TeamService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@AllArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {
        return new ResponseEntity<>(teamService.createTeam(teamDto), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<TeamDto>> getTeams() {
        return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    }

    @DeleteMapping("/{team}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("team") String team) {
        teamService.deleteTeam(team);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
