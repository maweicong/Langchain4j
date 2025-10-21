package com.mwc.langchain4j.mapper;

import com.mwc.langchain4j.pojo.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReservationMapper {
    @Insert("insert into reservation(name,gender,phone,communication_time,province,estimated_score) values(#{name},#{gender},#{phone},#{communicationTime},#{province},#{estimatedScore})")
    //添加预约信息
    void insert(Reservation reservation);
    @Select("select * from reservation where phone=#{phone}")
    //根据手机号查询预约信息
    Reservation findByPhone(String phone);
}
