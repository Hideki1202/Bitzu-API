package com.bitzu.demo.repositories;

import com.bitzu.demo.models.Tag;
import com.bitzu.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
