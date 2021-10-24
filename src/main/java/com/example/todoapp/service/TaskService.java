package com.example.todoapp.service;

import java.sql.Timestamp;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.todoapp.entity.Task;
import com.example.todoapp.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  // タスクの絞込
  public List<Task> searchTask(String start, String end, String content, String status) {
    // 現在の時刻を取得
    Date date = new Date();
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String defaultEnd = dateformat.format(date);

    if (StringUtils.hasText(start)) {
      start = start + " 00:00:00";
    } else {
      start = "2020-08-01 00:00:00";
    }

    if (StringUtils.hasText(end)) {
      end = end + " 23:59:59";
    } else {
      end = defaultEnd;
    }

    Timestamp startDate = Timestamp.valueOf(start);
    Timestamp endDate = Timestamp.valueOf(end);

    Integer statusInt = Integer.parseInt(status);

    List<Task> tasks;
    if (statusInt == 0) {
      tasks = taskRepository.findByTime(startDate, endDate, content);
    } else {
      tasks = taskRepository.findByAllTime(startDate, endDate, content, statusInt);
    }

    return tasks;
  }
}
