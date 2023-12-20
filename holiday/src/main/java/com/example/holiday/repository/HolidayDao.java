package com.example.holiday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.holiday.entity.Holiday;

//ニュースDBのレスポストリ
@Repository
public interface HolidayDao extends JpaRepository<Holiday, Integer> {



}
