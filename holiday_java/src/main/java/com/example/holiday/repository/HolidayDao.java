package com.example.holiday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.holiday.entity.Holiday;

//ニュースDBのレスポストリ
@Repository
public interface HolidayDao extends JpaRepository<Holiday, Integer> {

	@Query(value = "select * from holidays "
			+ "where year = :inputYear AND holiday_name = :inputName", nativeQuery = true)
	public Holiday findByYearAndName(@Param("inputYear") String year,@Param("inputName")String name);


}
