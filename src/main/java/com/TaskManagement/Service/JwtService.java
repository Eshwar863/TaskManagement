package com.TaskManagement.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService   {
     String generateJwtToken(UserDetails userDetails);
     String extractUserName(String token);}
