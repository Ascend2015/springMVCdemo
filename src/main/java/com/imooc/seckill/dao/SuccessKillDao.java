package com.imooc.seckill.dao;

import com.imooc.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ASCEND on 2017/4/9.
 */
public interface SuccessKillDao {
    /**
     * 插入购买明细，过滤重复秒杀
     * @param seckillId
     * @param userPhone
     * @return 返回值>1，表示表中插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
     * 根据id来查询SecKilled,并携带秒杀产品对象的属性
     * @param seckillId
     * @return
     */
    SuccessKilled queryBySecKill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
