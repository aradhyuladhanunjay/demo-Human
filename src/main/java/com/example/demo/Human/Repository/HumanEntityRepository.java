package com.example.demo.Human.Repository;

import com.example.demo.Human.Entity.HumanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanEntityRepository extends JpaRepository<HumanEntity, Long> {
}