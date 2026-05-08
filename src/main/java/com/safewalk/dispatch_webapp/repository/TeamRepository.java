package com.safewalk.dispatch_webapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safewalk.dispatch_webapp.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamColour(String teamColour);
}
