package com.eazybytes.EazySchoolApplication.repository;

import com.eazybytes.EazySchoolApplication.model.Contact;
import com.eazybytes.EazySchoolApplication.model.Holiday;
import com.eazybytes.EazySchoolApplication.rommappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {
    // for spring data JDBC---------------------------------------------------------Start__-------
    //this time Contact Repository is a class
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public HolidaysRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    //BeanPropertyRowMapper create automatic rowmapper we did not need to create of our own it our pojo class properties
//    // same used in database
//    public List<Holiday> findAllHolidays() {
//        String sql = "SELECT * FROM HOLIDAYS";
//        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
//        return jdbcTemplate.query(sql, rowMapper);
//    }
    // for spring data JDBC---------------------------------------------------------END-------

    //Start for JPA
    //this time Contact Repository is a interface

}