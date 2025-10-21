package com.mwc.langchain4j;

import com.mwc.langchain4j.pojo.Reservation;
import com.mwc.langchain4j.service.ReservationService;
import com.mwc.langchain4j.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;
    //测试添加
    @Test
    void testAddReservation() {
        Reservation reservation = new Reservation(null, "张三", "男", "12345678901", LocalDateTime.now(), "北京", 580);
        reservationService.addReservation(reservation);
    }


    //测试查询
    @Test
    void testQueryReservation() {
        Reservation reservation = reservationService.queryReservation("12345678901");
        System.out.println(reservation);
    }
}
