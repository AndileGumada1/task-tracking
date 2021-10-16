package com.andile.task.tracking.app.controller

import com.andile.task.tracking.app.model.Task
import com.andile.task.tracking.app.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RequestMapping("v1/api/tasks")
@RestController
class TaskController(private val taskService: TaskService) {

    /**
     * This end-point is used to return a List of tasks
     **/
    @GetMapping
    fun getAllTasks(): List<Task> =
        taskService.getTasks()

    /**
     * This end-point is used to return a task using Id
     **/
    @GetMapping("/{taskId}")
    fun getTaskById(@PathVariable taskId: Long) =
        taskService.getTaskById(taskId)

    /**
     * This end-point is used to delete a task using the id
     * @param task represents the new Task that will be created
     * @return ResponseEntity
     **/
    @PostMapping
    fun addNewTask(@Valid @RequestBody task: Task): ResponseEntity<Task> =
        taskService.addTask(task)

    /**
     * This end-point is used to update a task using the id
     * @param taskId represents the Task Id
     * @param newTask represents new task that is updated
     * @return ResponseEntity
     **/
    @PutMapping("/{id}")
    fun updateTaskUsingId(
        @PathVariable(value = "id") taskId: Long,
        @Valid @RequestBody newTask: Task
    ): ResponseEntity<Task> =
        taskService.updateTask(taskId, newTask)

    /**
     * This end-point is used to delete a task using the id
     * @param taskId represents the Task Id
     **/
    @DeleteMapping("/{id}")
    fun deleteTaskById(@PathVariable(value = "id") taskId: Long): ResponseEntity<Void> =
        taskService.deleteTask(taskId)
}