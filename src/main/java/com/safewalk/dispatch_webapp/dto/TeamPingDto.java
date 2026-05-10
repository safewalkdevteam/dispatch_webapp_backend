package com.safewalk.dispatch_webapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamPingDto {

    @JsonProperty("Team")
    private String teamColour;

    @JsonProperty("Latitude")
    private Float latitude;

    @JsonProperty("Longitude")
    private Float longitude;

    @JsonProperty("LastPing")
    private Long lastPing;

    @JsonProperty("SOS")
    private Boolean sos;
}
