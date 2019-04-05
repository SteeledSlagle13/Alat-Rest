package com.apexlegendsat.springmvc.backend.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apexlegendsat.springmvc.backend.entity.UserEntity;
import com.apexlegendsat.springmvc.backend.service.UserService;

@RestController
@RequestMapping(value = "user", headers = { "Accept=application/json,application/xml" }, produces = {
		"application/json", "application/xml" })
public class ApiUserRestController {

	static Logger logger = LogManager.getLogger(ApiUserRestController.class.getName());

	@Autowired
	private UserService userService;

	/**
	 * This method is used to grab user information from the database. User objects
	 * are converted inorder to hide data that does not need to be seen
	 * 
	 * @return list of users in database in JSON format
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> listAllUsers() {
		List<UserEntity> userEntities = userService.findAllUsers();

		logger.debug("should have users.");
		if (userEntities.isEmpty()) {
			return new ResponseEntity<List<UserEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<UserEntity>>(userEntities, HttpStatus.OK);
	}

	/**
	 * Grabs user data from the database then converts the entity to a viewable user
	 * object
	 * 
	 * @param id - user id
	 * @return user object without sensitive data or NO_CONTENT when user is not
	 *         found
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<UserEntity> getUser(@PathVariable("id") int id) {

		logger.debug("Fetching user with id : " + id);
		UserEntity userEntity = userService.findById(id);

		if (userEntity == null) {
			logger.error("user with id : " + id + " not found");
			return new ResponseEntity<UserEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
	}

	/**
	 * sends new user data to database user data is not converted to a UserView
	 * object because an Entity object is not returned
	 * 
	 * @param user - user to create
	 * @return CREATED on Success | CONFLICT if username is in the Database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Boolean> createUser(@RequestBody UserEntity user) {
		logger.debug("creating user " + user);

		if (userService.doesUserExist(user)) {
			logger.error("A User with name " + user.getUsername() + " already exist");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		userService.saveUser(user);

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	/**
	 * update the username, email, and address in the database
	 * 
	 * @param id   - user id
	 * @param user - user viewable object
	 * @return NO_CONTENT if user cannot be found | OK if update was successful
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
		logger.debug("updating user " + user.getId());

		UserEntity currentUser = userService.findById(user.getId());

		if (currentUser == null) {
			logger.debug("user with id " + user.getId() + " not found");
			return new ResponseEntity<UserEntity>(currentUser, HttpStatus.INTERNAL_SERVER_ERROR);// 500
		}

		userService.updateUser(user);

		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}

	/**
	 * delete the user from the database and local
	 * 
	 * @param id - user id
	 * @return NO_CONTENT
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id) {
		logger.debug("Fetching & Deleting User with id " + id);

		UserEntity user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}

}