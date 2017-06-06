package com.imooc.seckill.service.serviceImpl;

import com.imooc.seckill.dao.SecKillDao;
import com.imooc.seckill.dao.SuccessKillDao;
import com.imooc.seckill.dto.Exposer;
import com.imooc.seckill.dto.SecKillExcution;
import com.imooc.seckill.entity.SecKill;
import com.imooc.seckill.entity.SuccessKilled;
import com.imooc.seckill.enums.SecKillStatEnum;
import com.imooc.seckill.exception.RepeatKillException;
import com.imooc.seckill.exception.SecKillColseException;
import com.imooc.seckill.exception.SecKillException;
import com.imooc.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


/**
 * Created by ASCEND on 2017/5/1.
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillDao secKillDao;
    @Autowired
    private SuccessKillDao successKillDao;

    //MD5盐值字符串，用于混淆MD5
    private final String slat="fhoq'jASJIOKQ932K9340(*pQWO";

    public List<SecKill> getSecKillList() {
        return secKillDao.queryAll(0,4);
    }

    public SecKill getById(long seckillId) {
        return secKillDao.queryById(seckillId);
    }

    public Exposer exportSecKillUrl(long seckillId) {
        SecKill secKill=secKillDao.queryById(seckillId);
        if (secKill==null){
            return new Exposer(false,seckillId);
        }
        Date startTime=secKill.getStartTime();
        Date endTime=secKill.getEndTime();
        Date nowTime=new Date();
        if (nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转化特定字符串的过程，不可逆
        String md5=getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    /**
     * 使用注解控制事务方法的优点
     * 1.开发团队形成一致约定，明确标注事务方法的变成风格；
     * 2.保证事务方法执行的时间尽可能短，不要穿插其他的网络操作（比如RPC/HTTP请求），或者剥离到事务方法外部；
     * 3.不是所有的方法都需要事务，如只有一条修改操作、或者只读操作无需事务操作。
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SecKillException
     * @throws RepeatKillException
     * @throws SecKillColseException
     */
    @Transactional
    public SecKillExcution excuteSecKill(long seckillId, long userPhone, String md5)
            throws SecKillException, RepeatKillException, SecKillColseException {
        try {
            if (md5==null||!md5.equals(getMd5(seckillId))){
                throw new SecKillException("seckill data rewrite");
            }
            //执行秒杀的业务逻辑：减库存，记录购买行为
            Date nowTime=new Date();
            int updateCount=secKillDao.reduceNumber(seckillId,nowTime);
            if (updateCount<=0){
                //没有更新到记录
                throw new SecKillColseException("SecKill is closed");
            }else {
                //减库存成功,记录购买行为
                int insertUpdateCount=successKillDao.insertSuccessKilled(seckillId,userPhone);
                //唯一认证：seckillId，userPhone
                if (insertUpdateCount<=0){
                    throw new RepeatKillException("seckill repeat");
                }else {
                    SuccessKilled successKilled=successKillDao.queryBySecKill(seckillId,userPhone);
                    return new SecKillExcution(seckillId, SecKillStatEnum.SUCCESS,successKilled);
                }
            }
        }catch (SecKillColseException e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有的编译异常转化为运行期异常
            throw new SecKillException("seckill inner error:"+e.getMessage());
        }
    }

    private String getMd5(long seckillId){
        String base=seckillId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
