package com.crm.repository;

import com.crm.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Company Repository
 *
 * @author Dima Zelenyuk
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
