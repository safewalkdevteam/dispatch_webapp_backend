package com.safewalk.dispatch_webapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safewalk.dispatch_webapp.entity.TeamPing;

public interface TeamPingRepository extends JpaRepository<TeamPing, Long> {
    Optional<TeamPing> findByTeam(String team);
}
