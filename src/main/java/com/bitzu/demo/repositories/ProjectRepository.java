package com.bitzu.demo.repositories;

import com.bitzu.demo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository  extends JpaRepository<Project, Long> {

}