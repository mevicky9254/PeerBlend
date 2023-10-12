package com.PEERBLEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PEERBLEND.model.Match;

public interface MatchRepository extends JpaRepository<Match,Long> {

}
