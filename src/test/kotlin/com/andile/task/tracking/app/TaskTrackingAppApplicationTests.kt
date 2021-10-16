package com.andile.task.tracking.app

import com.andile.task.tracking.app.controller.TaskController
import com.andile.task.tracking.app.model.Task
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@WebMvcTest(TaskController::class)
@AutoConfigureMockMvc(addFilters = false)
class TaskTrackingAppApplicationTests(
									  ) {
	@Autowired
	private lateinit var  mockMvc: MockMvc



	@Test
	fun check_if_task_is_created() {
		val objectMapper= ObjectMapper()
		mockMvc.post("http://localhost:8080/v1/api/tasks"){
			contentType = MediaType.APPLICATION_JSON
			val taskJosn = objectMapper.writeValueAsString(createNewTask())
		}.andDo { print() }
			.andExpect {
				status { isCreated() }
				content {
					contentType(MediaType.APPLICATION_JSON)
					json(objectMapper.writeValueAsString(createNewTask()))
				}
			}
	}

	private fun createNewTask(): Task {
		val task = Task(1L,"Kotlin","",null,null,"1","",LocalDateTime.now())
		return task;
	}

}
