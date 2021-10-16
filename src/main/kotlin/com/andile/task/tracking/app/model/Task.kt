package com.andile.task.tracking.app.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
/**
* This class is used to model Ask table to persist to the database
**/
@Entity
@Table(name = "tasks")
data class Task(
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 val id: Long? = null,
 val title:String,
 val description: String? = null,
 val startDate: Date? = null,
 val dueDate: Date? = null,
 val status: String,
 val priority: String,
 val createdAt: LocalDateTime? = LocalDateTime.now(),
 val updatedAt: LocalDateTime? = LocalDateTime.now(),
)