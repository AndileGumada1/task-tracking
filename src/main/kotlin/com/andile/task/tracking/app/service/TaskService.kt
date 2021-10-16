package com.andile.task.tracking.app.service

import com.andile.task.tracking.app.model.Task
import com.andile.task.tracking.app.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TaskService (private val taskRepository: TaskRepository){

    /**
     * This method is used to return a list of tasks
     * @return List of type Task object
     **/
    fun getTasks(): List<Task> =
    taskRepository.findAll()
    /**
     * This method is used to create a new task
     * @param task represents the new Task that will be created in the database
     * @return ResponseEntity object
     **/
    fun addTask(task: Task): ResponseEntity<Task> =
        ResponseEntity.ok(taskRepository.save(task))
    /**
     * This method is used to get a task using the id
     * @param taskId represents the Task Id
     * @return ResponseEntity object
     **/
    fun getTaskById(taskId:Long): ResponseEntity<Task> =
        taskRepository.findById(taskId).map { task ->
            ResponseEntity.ok(task)
        }.orElse(ResponseEntity.notFound().build())
    /**
     * This method is used to update the task using the id
     * @param taskId represents the Task Id
     * @param newTask represents the new Task that will be updated using the Id
     * @return ResponseEntity object
     **/
    fun updateTask(taskId: Long, newTask: Task): ResponseEntity<Task> =
        taskRepository.findById(taskId).map { currentTask ->
            val updatedTask: Task =
                currentTask
                    .copy(
                        title = newTask.title,
                        description = newTask.description,
                        status = newTask.status,
                        startDate = newTask.startDate,
                        priority = newTask.priority,
                        dueDate = newTask.dueDate
                    )
            ResponseEntity.ok(updatedTask)
        }.orElse(ResponseEntity.notFound().build())
    /**
     * This method is used to delete a task using the id
     * @param taskId represents the Task Id
     *
     **/
    fun deleteTask(taskId:Long): ResponseEntity<Void> =
        taskRepository.findById(taskId).map { task ->
            taskRepository.delete(task)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())
}