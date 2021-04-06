package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldGetAllTasks() throws Exception {
        // Given
        when(service.getAllTasks()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetTask() throws Exception {

        // Given
        Task task = new Task(1L, "task", "content");
        TaskDto taskdto = new TaskDto(1L, "task", "content");


        when(service.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskdto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTask")
                        .param("taskId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void shouldUpdateTask() throws Exception {
        // Given
        Task task = new Task(1L, "task", "content");
        TaskDto taskdto = new TaskDto(1L, "task", "content");
        TaskDto taskdto2 = new TaskDto(2L, "updatedTask", "updatedContent");


        when(service.saveTask(task)).thenReturn(new Task(2L, "updatedTask", "updatedContent"));
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskdto2);
        when(taskMapper.mapToTask(any())).thenReturn(task);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskdto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("updatedTask")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("updatedContent")))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void shouldCreateTask() throws Exception {
        // Given
        Task task = new Task(1L, "task", "content");
        TaskDto taskDto = new TaskDto(1L, "task", "content");

        when(service.saveTask(any(Task.class))).thenReturn(new Task(1L, "task", "content"));
        when(taskMapper.mapToTask(any())).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteTask() throws Exception {

        // Given
        Task task = new Task(1L, "task", "content");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders

                .delete("/v1/task/deleteTasks")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}