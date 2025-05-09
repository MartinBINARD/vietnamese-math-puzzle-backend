package com.puzzle.solutionapi.service;

import com.puzzle.solutionapi.entity.Solution;
import com.puzzle.solutionapi.repository.SolutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public Solution createSolution(Solution solution) {
    if (solution.isCorrect()) {
      return repository.save(solution);
    }
    throw new IllegalArgumentException("Solution must be correct to be saved");
  }

  public List<Solution> createSolutions(List<Solution> solutions) {
    boolean allCorrect = solutions.stream().allMatch(Solution::isCorrect);
    if (allCorrect) {
      return repository.saveAll(solutions);
    }
    throw new IllegalArgumentException("All solutions must be correct");
  }

  public Solution updateSolution(Long id, Solution updated) {
    if (!updated.isCorrect()) {
      throw new IllegalArgumentException("Updated solution must be correct");
    }
    return repository.findById(id).map(existing -> {
      updated.setId(existing.getId());
      return repository.save(updated);
    }).orElse(null);
  }

  public boolean deleteSolutionById(Long id) {
    repository.deleteById(id);
    return true;
  }

  public boolean deleteAllSolutions() {
    repository.deleteAll();
    return true;
  }
}
