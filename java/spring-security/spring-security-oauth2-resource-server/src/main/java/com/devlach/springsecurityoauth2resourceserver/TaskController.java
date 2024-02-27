package com.devlach.springsecurityoauth2resourceserver;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<Task> createTask(@RequestBody Task task, UriComponentsBuilder ucb) {
        Task savedTask = taskRepository.save(task);
        var urlLocation = ucb.path("/tasks/{id}").buildAndExpand(savedTask.id()).toUri();
        return ResponseEntity.created(
                urlLocation
        ).body(savedTask);
    }

    @GetMapping("/{taskIdRequested}")
    public ResponseEntity<Task> findById(@PathVariable Long taskIdRequested) {
        return taskRepository.findById(taskIdRequested)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@GetMapping
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }*/

    /* Method injection */
   /* @GetMapping
    public Iterable<Task> findAll(Authentication authentication) {
        var owner = authentication.getName();
        return taskRepository.findAllByOwner(owner);
    }*/

    /*Principal Type Conversion*/
   /* @GetMapping
    public ResponseEntity<Iterable<Task>> findAll(@CurrentSecurityContext(expression = "authentication.principal")Jwt principal) {
        var owner = principal.getSubject();
        return ResponseEntity.ok(taskRepository.findAllByOwner(owner));
    }*/

    /* @AuthenticationPrincipal */
    /*@GetMapping
    public ResponseEntity<Iterable<Task>> findAll(@AuthenticationPrincipal Jwt principal) {
        var owner = principal.getSubject();
        return ResponseEntity.ok(taskRepository.findAllByOwner(owner));
    }*/

    /* Meta-annotation */
    /*@GetMapping
    public ResponseEntity<Iterable<Task>> findAll(@CurrentUsername String owner) {
        return ResponseEntity.ok(taskRepository.findAllByOwner(owner));
    }*/

    /* SecurityContextHolder */
/*    @GetMapping
    public ResponseEntity<Iterable<Task>> findAll() {
        var context = SecurityContextHolder.getContext();
        var authentication = context.getAuthentication();
        var owner = authentication.getName();
        return ResponseEntity.ok(taskRepository.findAllByOwner(owner));
    }*/

    /* Method-Level Authorization */
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_tasks:read')")
    public ResponseEntity<Iterable<Task>> findAll() {
        var context = SecurityContextHolder.getContext();
        var authentication = context.getAuthentication();
        var owner = authentication.getName();
        return ResponseEntity.ok(taskRepository.findAllByOwner(owner));
    }
}
