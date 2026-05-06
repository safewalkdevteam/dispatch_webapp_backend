package com.safewalk.dispatch_webapp.service;

import com.safewalk.dispatch_webapp.dto.TeamPingDto;

public interface TeamPingService {
    TeamPingDto createOrUpdateTeamPing(TeamPingDto teamPingDto);
    void deleteTeamPing(String teamPingDto);    
}
