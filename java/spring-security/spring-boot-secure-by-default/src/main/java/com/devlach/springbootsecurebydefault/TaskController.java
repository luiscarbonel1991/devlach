package com.devlach.springbootsecurebydefault;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, UriComponentsBuilder ubc) {
        var taskSaved = taskRepository.save(task);
        var urlLocationOfNewTask = ubc.path("/tasks/{id}").buildAndExpand(taskSaved.id()).toUri();
        return ResponseEntity.created(urlLocationOfNewTask).body(taskSaved);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> findById(@PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Task>> findAll() {
        return ResponseEntity.ok(taskRepository.findAll());
    }
}
