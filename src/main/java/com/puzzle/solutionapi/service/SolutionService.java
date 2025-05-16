package com.puzzle.solutionapi.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.puzzle.solutionapi.entity.Solution;
import com.puzzle.solutionapi.repository.SolutionRepository;

@Service
public class SolutionService {

  private final SolutionRepository repository;

  public SolutionService(SolutionRepository repository) {
    this.repository = repository;
  }

  public List<Solution> getAllSolutions() {
    return repository.findAll();
  }

  public Solution getSolutionById(Long id) {
    return repository.findById(id).orElse(null);
  }

  // POST one user solution
  public Solution createSolution(Solution solution) {
    if (!solution.isUserSolution()) {
      throw new IllegalArgumentException("Posted solution must be user creation");
    }

    return repository.save(solution);
  }

  // POST batch (algorithm-generated)
  public List<Solution> createSolutions(List<Solution> solutions) {
    boolean allCorrect = solutions.stream().allMatch(Solution::isCorrect);
    if (allCorrect) {
      return repository.saveAll(solutions);
    }
    throw new IllegalArgumentException("All solutions must be correct");
  }

  // PUT one user solution
  public Solution updateSolution(Long id, Solution updated) {
    if (!updated.isUserSolution()) {
      throw new IllegalArgumentException("Updated solution must be user creation");
    }

    return repository.findById(id).map(existing -> {
      updated.setId(existing.getId());

      return repository.save(updated);
    }).orElse(null);
  }

  public boolean deleteSolutionById(Long id) {
    try {
      repository.deleteById(id);

      return true;
    } catch (EmptyResultDataAccessException ex) {
      System.err.println("Failed to delete : id not found: " + id);

      return false;
    }
  }

  public boolean deleteAllSolutions() {
    try {
      repository.deleteAll();

      return true;
    } catch (Exception ex) {
      System.err.println("Failed to delete all solutions: " + ex.getMessage());

      return false;
    }
  }
}
