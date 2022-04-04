package com.example.harjoitustyo;

import java.time.LocalDate;

public interface Turnaus {
    void setEndDatetime(LocalDate d2);
    LocalDate getEndDatetime();
    void setStartDatetime(LocalDate d1);
    LocalDate getStartDatetime();
    void setTurnauksenNimi(String TurnauksenNimi);
    String getTurnauksenNimi();
    void setTurnauksenID(int id);
    int getTurnauksenID();
}
