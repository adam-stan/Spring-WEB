package com.crud.tasks;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTests {

    @Test
    void mapToBoardsTest() {

        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloListDto> trelloListsDto =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoardDtoList =
                List.of(new TrelloBoardDto("name", "test_board", trelloListsDto));

        //When
        List<TrelloBoard> trelloBoardList = mapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(1, trelloBoardList.size());

    }

    @Test
    void mapToBoardsDtoTest() {

        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoardList =
                List.of(new TrelloBoard("name", "test_board", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoardDtoList = mapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(1, trelloBoardDtoList.size());

    }

    @Test
    void mapToList() {

        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloListDto> trelloListsDto =
                List.of(new TrelloListDto("1", "test_list", false));

        //When
        List<TrelloList> trelloBoardList = mapper.mapToList(trelloListsDto);

        //Then
        assertEquals(1, trelloBoardList.size());

    }

    @Test
    void mapToListDto() {

        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        //When
        List<TrelloListDto> trelloBoardListDto = mapper.mapToListDto(trelloLists);

        //Then
        assertEquals(1, trelloBoardListDto.size());
    }

    @Test
    void mapToCardDto() {

        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");

        //When
        TrelloCardDto trelloCardDto = mapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("name", trelloCardDto.getName());
    }

    @Test
    void mapToCard() {

        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");

        //When
        TrelloCard trelloCard = mapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("name", trelloCard.getName());
    }
}
