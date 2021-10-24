package com.example.todoapp.repository;

import java.sql.Timestamp;
import java.util.List;

import com.example.todoapp.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
  @Query("select t from Task t where t.created_date between ?1 and ?2 and t.content like  %?3%")
  List<Task> findByTime(Timestamp start, Timestamp end, String content);

  @Query("select t from Task t where t.created_date between ?1 and ?2 and t.content like %?3% and t.status = ?4")
  List<Task> findByAllTime(Timestamp start, Timestamp end, String content, Integer status);
}
