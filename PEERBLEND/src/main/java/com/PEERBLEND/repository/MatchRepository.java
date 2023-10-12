package com.PEERBLEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PEERBLEND.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {

}
