package com.imooc.seckill.dao;

import com.imooc.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by ASCEND on 2017/5/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springDao.xml")
public class SuccessKillDaoTest {
    @Resource
    private SuccessKillDao successKillDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long seckillId=1000L;
        long userPhone=13812312123L;
        int updateCount=successKillDao.insertSuccessKilled(seckillId,userPhone);
        System.out.println(updateCount);
    }

    @Test
    public void queryBySecKill() throws Exception {
        long seckillId=1000L;
        long userPhone=13812312123L;
        SuccessKilled successKilled=successKillDao.queryBySecKill(seckillId,userPhone);
        System.out.println(successKilled);
    }

}