package com.example.hooverspec.repo;

import com.example.hooverspec.entity.HooverSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooverRepo extends JpaRepository<HooverSpec, Long> {

}
