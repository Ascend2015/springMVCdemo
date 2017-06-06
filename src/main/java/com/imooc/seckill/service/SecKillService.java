package com.imooc.seckill.service;

import com.imooc.seckill.dto.Exposer;
import com.imooc.seckill.dto.SecKillExcution;
import com.imooc.seckill.entity.SecKill;
import com.imooc.seckill.exception.RepeatKillException;
import com.imooc.seckill.exception.SecKillColseException;
import com.imooc.seckill.exception.SecKillException;

import java.util.List;

/**
 * 业务接口：站在“使用者”的角度来设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型要友好/异常）
 *
 * Created by ASCEND on 2017/5/1.
 */
public interface SecKillService {
    /**
     * 查询所有的秒杀记录
     * @return
     */
    List<SecKill> getSecKillList();

    /**
     * 查询单个秒杀记录
     * @return
     */
    SecKill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口，否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSecKillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SecKillExcution excuteSecKill(long seckillId, long userPhone, String md5)throws SecKillException,RepeatKillException,SecKillColseException;
}
