package com.mwc.langchain4j.tools;

import com.mwc.langchain4j.pojo.Reservation;
import com.mwc.langchain4j.service.ReservationService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationTool {
    @Autowired
    private ReservationService reservationService;

    //添加预约信息
    @Tool("预约志愿填报服务")
    public void addReservation(
            @P("考生姓名") String name,
            @P("考生性别") String gender,
            @P("考生手机号码") String phone,
            @P("预约时间,格式为:yyyy-MM-dd'T'HH:mm") String communicationTime,
            @P("考生所在省份") String province,
            @P("预估分数") Integer estimatedScore
    ){
        Reservation reservation = new Reservation(null, name, gender, phone, LocalDateTime.parse(communicationTime), province, estimatedScore);
        reservationService.addReservation(reservation);

    }
    //查询预约信息
    @Tool("根据考生手机号查询预约")
    public Reservation queryReservation(
            @P("考生手机号码") String phone
    ){
        return reservationService.queryReservation(phone);
    }
}
