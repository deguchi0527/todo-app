package com.example.todoapp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String content;

  @Column
  private int status;

  @Column
  private Timestamp limit_date;

  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp created_date;

  @UpdateTimestamp
  @Column
  private Timestamp updated_date;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Timestamp getLimit_date() {
    return limit_date;
  }

  public void setLimit_date(Timestamp limit_date) {
    this.limit_date = limit_date;
  }

  public Timestamp getCreated_date() {
    return created_date;
  }

  public void setCreated_date(Timestamp created_date) {
    this.created_date = created_date;
  }

  public Timestamp getUpdated_date() {
    return updated_date;
  }

  public void setUpdated_date(Timestamp updated_date) {
    this.updated_date = updated_date;
  }
}
