package com.dsv.work.around.rest.controllers;

import com.dsv.work.around.rest.entities.Company;
import com.dsv.work.around.rest.entities.User;
import com.dsv.work.around.rest.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "api-user", description = "API related with company")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/users")
public class UserController {
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private final UserRepository userRepository;

  @Operation(summary = "returns a list of the users", tags = {"users", "get", "filter"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")}),
      @ApiResponse(responseCode = "404", description = "There are no company", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  @GetMapping("/")
  public ResponseEntity<List<User>> findAllUsers() {
    try {
      List<User> companies = userRepository.findAll();

      if (companies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(companies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Retrieve all users by companyId", tags = {"users", "get", "filter"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
      @ApiResponse(responseCode = "404", description = "There are no Users", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  @GetMapping()
  //public ResponseEntity<List<User>> findUsersByCompanyId(@RequestParam(value="company_id", required = true) Long companyId) {
  public ResponseEntity<List<User>> findUsersByCompanyId(@RequestParam("company_id") Long companyId) {
    try {
      List<User> users = userRepository.findByCompanyId(companyId);

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Retrieve a user by id", tags = {"users", "get", "filter"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
      @ApiResponse(responseCode = "404", description = "There are no user with given id", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  @GetMapping("/{id}")
  public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) {
    try {
      Optional<User> optionalUser = userRepository.findById(id);
      if (optionalUser.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Create a new User", tags = {"users", "post"})
  @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
          @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  @PostMapping("/")
  public ResponseEntity<User> createTutorial(@RequestBody User user) {
    try {
      User savedEntity = userRepository.save(user);
      return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
