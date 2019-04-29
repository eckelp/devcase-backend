package com.vsm.devcase.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
@CrossOrigin(
	origins = "http://localhost:4200"
)
@Api(
	value = "Resource para autenticação", 
	description = "Resource para autenticação de usuário")
public class AuthResource {
	
	
	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Retorna o usuário autenticado", response = User.class)
	public ResponseEntity<User> auth(){
		
		UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails principal = (UserDetails ) authentication.getPrincipal();
		
		User user = new User();
		user.setUsername(principal.getUsername());
				
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	

}
