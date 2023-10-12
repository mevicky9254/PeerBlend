package com.PEERBLEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PEERBLEND.model.Interest;

@Repository
public interface InterestRepository extends JpaRepository<Interest,Long> {

}
