package com.eazybytes.EazySchoolApplication.repository;

import com.eazybytes.EazySchoolApplication.model.EazyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository extends JpaRepository<EazyClass,Integer> {
}
