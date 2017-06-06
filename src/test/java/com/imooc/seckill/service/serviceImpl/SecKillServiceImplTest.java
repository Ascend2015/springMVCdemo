package com.imooc.seckill.service.serviceImpl;

import com.imooc.seckill.dto.Exposer;
import com.imooc.seckill.dto.SecKillExcution;
import com.imooc.seckill.entity.SecKill;
import com.imooc.seckill.exception.SecKillException;
import com.imooc.seckill.service.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ASCEND on 2017/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/springDao.xml","classpath:spring/spring-service.xml"})
public class SecKillServiceImplTest {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @Test
    public void testGetSecKillList() throws Exception {
        List<SecKill> list=secKillService.getSecKillList();
        logger.info("list={}",list);
    }

    @Test
    public void testGetById() throws Exception {
        long id=1000L;
        SecKill secKill=secKillService.getById(id);
        logger.info("seckill={}",secKill);
    }

    @Test
    public void testExportSecKillUrl() throws Exception {
        long id=1000;
        Exposer exposer=secKillService.exportSecKillUrl(id);
        logger.info("exposer={}",exposer);
    }

    @Test
    public void testExcuteSecKill() throws Exception {
        try {
            long id = 1000;
            long userPhone = 13814567892L;
            String md5 = "b58c98d39671a4b30ff4b0ea2b6e0718";
            SecKillExcution excution = secKillService.excuteSecKill(id, userPhone, md5);
            logger.info("excution={}", excution);
        }catch (SecKillException e){
            logger.error(e.getMessage());
        }
    }

}