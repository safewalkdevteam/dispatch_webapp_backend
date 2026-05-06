package com.safewalk.dispatch_webapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamPingDto {
    private Long id;

    @JsonProperty("Team")
    private String team;

    @JsonProperty("Latitude")
    private Float latitude;

    @JsonProperty("Longitude")
    private Float longitude;

    @JsonProperty("LastPing")
    private Long lastPing;

    @JsonProperty("SOS")
    private Boolean sos;
}
