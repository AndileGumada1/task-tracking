package com.andile.task.tracking.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskTrackingAppApplication

fun main(args: Array<String>) {
	runApplication<TaskTrackingAppApplication>(*args)
}
