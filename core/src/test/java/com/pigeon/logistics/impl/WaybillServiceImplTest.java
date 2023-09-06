package com.pigeon.logistics.impl;

import com.pigeon.logistics.entity.LogisticsLogEntity;
import com.pigeon.logistics.entity.WaybillEntity;
import com.pigeon.logistics.service.WaybillService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WaybillServiceImplTest {


    @Autowired
    WaybillService waybillService;

    @Test
    void getOneByWaybillCode() {
        WaybillEntity oneByWaybillCode = waybillService.getOneByWaybillCode("JL1675184462560");
        System.out.println("获取物流运单信息：" + oneByWaybillCode);
    }

    @Test
    void createOneLog() {
        var log = LogisticsLogEntity.builder()
                .dateTime(LocalDateTime.now())
                .status("运输中")
                .operator("武汉小蜜蜂公司")
                .operaDetail("已打包")
                .phone("15017806272")
                .build();
        WaybillEntity oneByWaybillCode = waybillService.getOneByWaybillCode("JL1675184462560");
        oneByWaybillCode.getLogs().add(log);
        waybillService.updateById(oneByWaybillCode);
    }

    @Test
    void createOne() {

    }

    @Test
    void pay() {
    }

    @Test
    void deliver() {
    }

    @Test
    void receive() {
    }

    @Test
    void refulse() {
    }
}