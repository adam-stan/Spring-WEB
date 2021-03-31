import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariousTests {

    @Test
    void validateTrelloBoardsTest() {

        //Given
        TrelloValidator trelloValidator = new TrelloValidator();

        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoardList =
                List.of(new TrelloBoard("1", "test", trelloLists));

        //When
        List<TrelloBoard> trelloBoardList2 = trelloValidator.validateTrelloBoards(trelloBoardList);

        //Then
        assertEquals(0, trelloBoardList2.size());
    }

    @Test
    void taskMapperTest() {

        //Given
        Task task = new Task(Long.valueOf(1), "title1", "content1");
        TaskDto taskDto = new TaskDto(Long.valueOf(2), "title2", "content2");
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = List.of(new Task(Long.valueOf(3), "title3", "content3"));

        //When
        TaskDto newTaskDto = taskMapper.mapToTaskDto(task);
        Task newTask = taskMapper.mapToTask(taskDto);
        List<TaskDto> newTaskDto2 = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals("title1", newTaskDto.getTitle());
        assertEquals("title2", newTask.getTitle());
        assertEquals(1, newTaskDto2.size());
    }
}