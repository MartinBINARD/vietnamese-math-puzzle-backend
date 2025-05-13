package com.puzzle.solutionapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzzle.solutionapi.entity.Solution;
import com.puzzle.solutionapi.service.SolutionService;

@RestController
@RequestMapping("/api/solutions")
@CrossOrigin
public class SolutionController {

  private final SolutionService service;

  public SolutionController(SolutionService service) {
    this.service = service;
  }

  // GET all
  @GetMapping
  public List<Solution> getAllSolutions() {
    return service.getAllSolutions();
  }

  // GET by ID
  @GetMapping("/{id}")
  public ResponseEntity<Solution> getSolutionById(@PathVariable Long id) {
    Solution solution = service.getSolutionById(id);

    return (solution != null) ? ResponseEntity.ok(solution) : ResponseEntity.notFound().build();
  }

  // POST one user solution
  @PostMapping("/user")
  public ResponseEntity<?> createUserSolution(@RequestBody Solution solution) {
    if (!solution.isUserSolution() || !solution.isCorrect()) {
      return ResponseEntity.badRequest().body("User solution must be correct and marked as user-generated.");
    }

    return ResponseEntity.ok(service.createSolution(solution));
  }

  // POST batch (algorithm-generated)
  @PostMapping("/generated")
  public ResponseEntity<?> createGeneratedSolutions(@RequestBody List<Solution> solutions) {
    boolean valid = solutions.stream().allMatch(s -> s.getId() == null && !s.isUserSolution() && s.isCorrect());

    if (!valid) {
      return ResponseEntity.badRequest()
          .body("Each generated solution must have no ID, be correct, and not user-generated.");
    }
    service.deleteAllSolutions();

    return ResponseEntity.ok(service.createSolutions(solutions));
  }

  // PUT
  @PutMapping("/{id}")
  public ResponseEntity<?> updateSolution(@PathVariable Long id, @RequestBody Solution solution) {

    if (!solution.isCorrect()) {
      return ResponseEntity.badRequest().body("Updated solution must be correct.");
    }
    Solution updated = service.updateSolution(id, solution);

    return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
  }

  // DELETE all
  @DeleteMapping
  public Map<String, Boolean> deleteAll() {
    return Map.of("isSuccess", service.deleteAllSolutions());
  }

  // DELETE by ID
  @DeleteMapping("/{id}")
  public Map<String, Boolean> deleteById(@PathVariable Long id) {
    return Map.of("isSuccess", service.deleteSolutionById(id));
  }
}
