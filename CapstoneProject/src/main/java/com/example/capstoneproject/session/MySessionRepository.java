package com.example.capstoneproject.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySessionRepository extends JpaRepository<MySession, Integer> {
}
