package com.puzzle.solutionapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Solution {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String steps;

  private double result;

  @JsonProperty("isCorrect")
  private boolean correct;

  @JsonProperty("isUserSolution")
  private boolean userSolution;

  private long durationMs;
  private String createdAt;

  // GETTERS
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
    return correct;
  }

  public boolean isUserSolution() {
    return userSolution;
  }

  public long getDurationMs() {
    return durationMs;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  // SETTERS
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
    this.correct = correct;
  }

  public void setUserSolution(boolean userSolution) {
    this.userSolution = userSolution;
  }

  public void setDurationMs(long durationMs) {
    this.durationMs = durationMs;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
