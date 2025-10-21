package com.mwc.langchain4j.service.impl;

import com.mwc.langchain4j.mapper.ReservationMapper;
import com.mwc.langchain4j.pojo.Reservation;
import com.mwc.langchain4j.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    @Override
    public void addReservation(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    @Override
    public Reservation queryReservation(String phone) {
         return reservationMapper.findByPhone(phone);
    }
}
