package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTests {

    @InjectMocks
    private DbService service;

    @Mock
    TaskRepository repository;

    @Test
    void shouldGetAllTasks() throws Exception {

        // Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "task", "content"));

        when(repository.findAll()).thenReturn(taskList);
        List<Task> taskList2 = service.getAllTasks();

        //When & Then
        assertEquals(1, taskList2.size());
    }

    @Test
    void shouldGetTask() throws Exception {

        // Given
        Task task = new Task(1L, "task", "content");
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> thisTask = service.getTask(1L);

        //When & Then
        assertEquals("task", thisTask.get().getTitle());
    }

    @Test
    void shoudSaveTasks() throws Exception {

        // Given
        Task task = new Task(1L, "task", "content");
        when(repository.save(task)).thenReturn(task);
        Task thisTask = service.saveTask(task);

        //When & Then
        assertEquals("task", thisTask.getTitle());
    }

    @Test
    void shouldDeleteTask() throws Exception {

        // Given
        TaskRepository taskRepository = mock(TaskRepository.class);
        DbService dbService = mock(DbService.class);

        //When
        taskRepository.deleteById(1L);
        dbService.deleteTask(1L);

        // Then
        verify(dbService,times(1)).deleteTask(1L);
        verify(taskRepository,times(1)).deleteById(1L);
    }
}
