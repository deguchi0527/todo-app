package com.example.todoapp.service;

import java.util.List;

import com.example.todoapp.entity.Task;
import com.example.todoapp.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  TaskRepository taskRepository;

  // タスクの全件取得
  public List<Task> findAllTask() {
    return taskRepository.findAll();
  }

  // タスク追加
  public void saveTask(Task task) {
    taskRepository.save(task);
  }

  // タスク削除
  public void deleteTask(Integer id) {
    taskRepository.deleteById(id);
  }

  // タスクを1件取得
  public Task editTask(Integer id) {
    Task task = (Task) taskRepository.findById(id).orElse(null);

    return task;
  }
}
