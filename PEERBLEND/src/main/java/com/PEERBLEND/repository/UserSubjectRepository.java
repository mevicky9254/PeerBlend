package com.PEERBLEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PEERBLEND.model.UserSubject;

public interface UserSubjectRepository extends JpaRepository <UserSubject, Long> {

}
