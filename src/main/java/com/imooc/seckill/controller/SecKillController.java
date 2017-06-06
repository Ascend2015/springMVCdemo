package com.imooc.seckill.controller;

import com.imooc.seckill.dto.Exposer;
import com.imooc.seckill.dto.SecKillExcution;
import com.imooc.seckill.dto.SecKillResult;
import com.imooc.seckill.entity.SecKill;
import com.imooc.seckill.enums.SecKillStatEnum;
import com.imooc.seckill.exception.RepeatKillException;
import com.imooc.seckill.exception.SecKillColseException;
import com.imooc.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by ASCEND on 2017/5/7.
 */
@Controller
@RequestMapping("/seckill")//url:/模块/资源/{id}/细分
public class SecKillController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SecKillService secKillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        //list.jsp+model=ModelAndView
        List<SecKill> list=secKillService.getSecKillList();
        System.out.println(list);
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail" ,method=RequestMethod.GET)
    public String detail(@PathVariable("seckillId")Long seckillId, Model model){
        if (seckillId==null){
            return "redirect:/seckill/list";
        }
        SecKill secKill=secKillService.getById(seckillId);
        if (secKill==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",secKill);
        return "detail";
    }

    //ajax接口
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SecKillResult<Exposer> result;
        try {
            Exposer exposer=secKillService.exportSecKillUrl(seckillId);
            result=new SecKillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage());
            result=new SecKillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/}seckillId}/{md5}/excution",
                            method = RequestMethod.POST,
                            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<SecKillExcution> excute(@PathVariable("seckillId") Long seckillId,
                                                 @PathVariable("md5") String md5,
                                                 @CookieValue(value = "killPhone",required = false)Long userPhone){
        if (userPhone==null){
            return new SecKillResult<SecKillExcution>(false,"该用户不存在");
        }
        SecKillResult<SecKillExcution> result;
        try {
            SecKillExcution excution=secKillService.excuteSecKill(seckillId,userPhone,md5);
            result =new SecKillResult<SecKillExcution>(true,excution);
        }catch (SecKillColseException e1){
            SecKillExcution excution=new SecKillExcution(seckillId, SecKillStatEnum.END);
            return new SecKillResult<SecKillExcution>(false,excution);
        }catch (RepeatKillException e2){
            SecKillExcution excution=new SecKillExcution(seckillId, SecKillStatEnum.REPEAT_KILL);
            return new SecKillResult<SecKillExcution>(false,excution);
        }catch (Exception e){
            logger.error(e.getMessage());
            SecKillExcution excution=new SecKillExcution(seckillId, SecKillStatEnum.INNER_ERROR);
            result=new SecKillResult<SecKillExcution>(false,excution);
        }
        return result;
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SecKillResult<Long> time(){
        Date now=new Date();
        return new SecKillResult<Long>(true,now.getTime());
    }
}
