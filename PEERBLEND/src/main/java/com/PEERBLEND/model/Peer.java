package com.PEERBLEND.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Peer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String userName;
	
	@NotNull
    @Column(name = "first_name")
    private String firstName;
    
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    
    @ElementCollection
    private List<String> skills= new ArrayList<>();


}
