package com.karthik.course_planner;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ModuleRepository extends JpaRepository<Module, Long>
{
    List<Module> findByUserId(Long userId);
}