package com.solvd.services;

import static org.testng.Assert.*;

import com.solvd.db.model.Card;
import com.solvd.db.model.User;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CardServiceTest {

    private CardService cs;
    private Card actualCard;
    private long cardNum;

    @BeforeMethod
    public void setUp() {
        cs = new CardService();
        cardNum = 1111111111111111L;
        actualCard = cs.getCardByCardNumber(cardNum);
    }

    @Test
    public void testGetCardByCardNumber() {
        assertEquals(cardNum, actualCard.getCardNumber());
    }

    @Test
    public void testGetInvalidCardIsNull() {
        long invalidNum = 0L;
        Card card = cs.getCardByCardNumber(invalidNum);
        assertNull(card);
    }

    @Test
    public void testGetCardsByUserId() {
        UserService userService = new UserService();
        User user1 = userService.getUserByCardNumber(cardNum);
        Card user1Card = cs.getCardsByUserId(user1.getUserId()).get(0);
        assertEquals(actualCard, user1Card);
    }

    @Test
    public void testGetCardsByCardStatus() {
        List<Card> activeCards = cs.getCardsByCardStatus("active");
        boolean allCardsActive = activeCards.stream()
            .allMatch(card -> card.getStatus().equals("active"));
        assertTrue(allCardsActive);
    }

    @Test
    public void testGetCardsByCardType() {
        List<Card> clientCards = cs.getCardsByCardType("ClientCard");
        boolean allClientCards = clientCards.stream()
            .allMatch(card -> card.getCardType().getName().equals("ClientCard"));
        assertTrue(allClientCards);
    }

}