package com.springMVC.demo.repository;

import com.springMVC.demo.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long>{

}