package com.PEERBLEND.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserDetailsDTO {
	
		private Long id;
		
	    private String firstName;
	    
	    private String lastName;

	    private String email;
	    
	    private String role;
	    
	    private String mobile;
	    
	    private String profileImage;
	    
	    private List<User> users = new ArrayList<>();
}
