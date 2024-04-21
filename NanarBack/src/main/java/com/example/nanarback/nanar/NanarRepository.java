package com.example.nanarback.nanar;


import com.example.nanarback.nanar.Nanar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NanarRepository extends JpaRepository<Nanar, Long> {
}
