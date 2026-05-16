package com.safewalk.dispatch_webapp.entity;

import com.safewalk.dispatch_webapp.enums.TeamStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String teamColour;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)
    private TeamPing teamPing;

    private TeamStatus status;

    private Boolean sos;
}
