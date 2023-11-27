package com.dsv.work.around.rest.controllers;


import com.dsv.work.around.rest.entities.Company;
import com.dsv.work.around.rest.repositories.CompanyRepository;
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

@Tag(name = "api-company", description = "API related with company")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/companies")

public class CompanyController {

  private final CompanyRepository companyRepository;

  public CompanyController(CompanyRepository companyRepository) {this.companyRepository = companyRepository;}

  @Operation(summary = "returns a list of the companies", tags = {"companies", "get", "filter"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")}),
      @ApiResponse(responseCode = "404", description = "There are no company", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  @GetMapping("/")
  public ResponseEntity<List<Company>> findAllCompanies() {
    try {
      List<Company> companies = companyRepository.findAll();

      if (companies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(companies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @Operation(summary = "Retrieve a company by id", tags = {"companies", "get", "filter"})
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")}),
      @ApiResponse(responseCode = "404", description = "There are no company with given id", content = {
          @Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  @GetMapping("/{id}")
  public ResponseEntity<Company> findCompanyById(@PathVariable(value = "id") Long id) {
    try {
      Optional<Company> optionalCompany = companyRepository.findById(id);
      if (optionalCompany.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(optionalCompany.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  @Operation(summary = "Create a new Company", tags = {"companies", "post"})
  @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
          @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
  @PostMapping("/")
  public ResponseEntity<Company> createTutorial(@RequestBody Company company) {
    try {
      Company savedEntity = companyRepository.save(company);
      return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
