package com.imooc.seckill.dao;

import com.imooc.seckill.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by ASCEND on 2017/4/9.
 */
public interface SecKillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 返回值>1，表示表中插入的行数
     */
    int reduceNumber( @Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    SecKill queryById(long seckillId);

    /**
     * 查列表，偏移量表示要多少
     * @param offset
     * @param limit
     * @return
     */
    List<SecKill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
