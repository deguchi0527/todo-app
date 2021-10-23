package com.example.todoapp.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.todoapp.entity.Task;
import com.example.todoapp.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

  @Autowired
  TaskService taskService;

  // タスク表示画面
  @GetMapping
  public ModelAndView top() {
    ModelAndView mav = new ModelAndView();
    List<Task> tasks = taskService.findAllTask();
    List<String> statusArray = new ArrayList<>();
    statusArray.add("選択なし");
    statusArray.add("完了");
    statusArray.add("ステイ中");
    statusArray.add("実行中");
    mav.setViewName("/top");
    mav.addObject("tasks", tasks);
    mav.addObject("statusArray", statusArray);

    return mav;
  }

  // タスクの追加画面
  @GetMapping("/new")
  public ModelAndView newContent() {
    ModelAndView mav = new ModelAndView();
    // form用の空のentityを準備
    Task task = new Task();
    List<String> statusArray = new ArrayList<>();
    statusArray.add("完了");
    statusArray.add("ステイ中");
    statusArray.add("実行中");
    mav.setViewName("/new");
    mav.addObject("formModel", task);
    mav.addObject("statusArray", statusArray);

    return mav;
  }

  // タスクの追加処理
  @PostMapping("/add")
  public ModelAndView addContent(@ModelAttribute("formModel") Task task, @RequestParam String limit) {
    // パラメータから送られてくる値をTimestamp型に変換するための処理
    String limitDateFormat = limit + ":00";
    String limitDateReplace = limitDateFormat.replace("T", " ");
    Timestamp limitDate = Timestamp.valueOf(limitDateReplace);

    // entityにlimit_dateをセット
    task.setLimit_date(limitDate);

    // タスクをテーブルに格納
    taskService.saveTask(task);

    return new ModelAndView("redirect:/");
  }
}
