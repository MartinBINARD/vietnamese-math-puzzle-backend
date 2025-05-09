package com.puzzle.solutionapi.entity;

import jakarta.persistence.*;

@Entity
public class Solution {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String steps; // JSON string (frontend-encoded steps)

  private double result;
  private boolean isCorrect;
  private boolean isUserSolution;
  private long durationMs;
  private String createdAt;

  // Getters
  public Long getId() {
    return id;
  }

  public String getSteps() {
    return steps;
  }

  public double getResult() {
    return result;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public boolean isUserSolution() {
    return isUserSolution;
  }

  public long getDurationMs() {
    return durationMs;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setSteps(String steps) {
    this.steps = steps;
  }

  public void setResult(double result) {
    this.result = result;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }

  public void setUserSolution(boolean userSolution) {
    isUserSolution = userSolution;
  }

  public void setDurationMs(long durationMs) {
    this.durationMs = durationMs;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
