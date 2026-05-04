package com.safewalk.dispatch_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safewalk.dispatch_webapp.entity.TeamPing;

public interface TeamPingRepository extends JpaRepository<TeamPing, Long> {
    TeamPing findByTeam(String team);
}
