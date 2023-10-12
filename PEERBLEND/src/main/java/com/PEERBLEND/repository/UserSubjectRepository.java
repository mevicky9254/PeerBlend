package com.PEERBLEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PEERBLEND.model.UserSubject;

@Repository
public interface UserSubjectRepository extends JpaRepository <UserSubject, Long> {

}
