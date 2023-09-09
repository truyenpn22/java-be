package com.report.teama.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.report.teama.Interface.CustomResponse;
import com.report.teama.Interface.UserLoginRequest;
import com.report.teama.model.User;
import com.report.teama.repository.UserRepository;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"authorization","content-type"})
@RestController
@RequestMapping("/api/users")

public class UserController {

	@Autowired
    private UserRepository userRepository;
	

    @GetMapping
    public ResponseEntity<CustomResponse> getAllUsers() {
    	try {
    		List<User> users =  userRepository.findAll();
        	CustomResponse customResponse = new CustomResponse(1000,"Find all user success!", users) ;
        	return  new ResponseEntity<>(customResponse, HttpStatus.OK);
    	}catch (Exception e) {
    		CustomResponse customResponse = new CustomResponse(2000,"Find all fail!", null) ;
   		 	return  new ResponseEntity<>(customResponse, HttpStatus.OK);
    	}
    
    }

    @PostMapping
    public ResponseEntity<CustomResponse> createUser(@RequestBody User user) {
    	try {
    		User existUser = userRepository.findById(user.getId()).orElse(null);
    		
    		if(existUser != null) {
    			CustomResponse customResponse = new CustomResponse(2000,"User is existed!", null) ;
        		return  new ResponseEntity<>(customResponse, HttpStatus.OK);
    		}
    		
    		User newUser = userRepository.save(user);
    		CustomResponse customResponse = new CustomResponse(1000,"Register success!", newUser) ;
    		return  new ResponseEntity<>(customResponse, HttpStatus.OK);
    	}catch (Exception e) {
    		CustomResponse customResponse = new CustomResponse(2000,"Register fail!", null) ;
    		 return  new ResponseEntity<>(customResponse, HttpStatus.OK);
    	}
  
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> userLogin(@RequestBody  UserLoginRequest userLoginRequest) {
    	
    	try {
    		 CustomResponse customResponse = new CustomResponse(1000,"Login success!", null) ;
            User user = userRepository.findByIdAndPassword(userLoginRequest.getId(),userLoginRequest.getPassword());

            if (user == null || !user.getPassword().equals(userLoginRequest.getPassword())) {
            	customResponse.setStatusCode(2000);
            	customResponse.setMessage("Invalid ID or password");
            	customResponse.setData(null);
                new ResponseEntity<>(customResponse, HttpStatus.OK);
            }
            customResponse.setData(user);
            return  new ResponseEntity<>(customResponse, HttpStatus.OK);
    	}catch (Exception e) {
    		CustomResponse customResponse = new CustomResponse(2000,"Fail server or querydb!", null) ;
    		 return  new ResponseEntity<>(customResponse, HttpStatus.OK);
    	}
        
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}
