package com.safewalk.dispatch_webapp.dto;

import com.safewalk.dispatch_webapp.enums.TeamStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private String teamColour;
    private TeamStatus status;
    private Boolean sos;
}
