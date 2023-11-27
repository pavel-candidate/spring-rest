package com.dsv.work.around.rest.repositories;

import com.dsv.work.around.rest.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long> {


}
