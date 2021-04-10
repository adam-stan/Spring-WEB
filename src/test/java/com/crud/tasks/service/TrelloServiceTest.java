package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;


    @Test
    void fetchTrelloBoardsTest() {

        // Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, trelloBoardDtoList.size());
    }

    @Test
    void createTrelloCardTest() {

        //Given
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto();
        TrelloCardDto trelloCardDto = new TrelloCardDto();

        //When
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCard);
        trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNotNull(newCard);
    }
}