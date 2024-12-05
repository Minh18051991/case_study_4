package com.example.demo.repository.classes;

import com.example.demo.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {
    List<Classes> findByEmployeeId(Integer employeeId);
    // Các truy vấn tùy chỉnh ở đây (nếu cần)
}
