package com.puzzle.solutionapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puzzle.solutionapi.entity.Solution;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
}
