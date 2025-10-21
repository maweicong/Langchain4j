package com.mwc.langchain4j.service;

import com.mwc.langchain4j.pojo.Reservation;
import org.springframework.stereotype.Service;


public interface ReservationService {
    //添加预约信息方法
    void addReservation(Reservation reservation);
    //查询预约信息方法
    Reservation queryReservation(String phone);
}
