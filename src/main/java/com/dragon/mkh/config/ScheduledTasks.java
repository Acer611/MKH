package com.dragon.mkh.config;

import com.dragon.mkh.entity.vo.response.OrderInfoResponse;
import com.dragon.mkh.service.IStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  定时任务操作类
 * @author  Gaofei
 * create by 2018-07-05
 *
 */
@Component
@Order(5)
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private IStatisticsService statisticsService;

    //@Scheduled(fixedRate = 1800000) //上一次开始执行时间点之后5秒再执行
    //@Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
    //@Scheduled(initialDelay=1000, fixedRate=5000) //第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
   // @Scheduled(cron="*/5 * * * * *")//通过cron表达式定义规则
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    /**
     * 同步更新渠道的总订单数和已支付的订单数（渠道号2000-2100）
     */
    //@Scheduled(cron="0 0 2 1/1 * ? ") //每天凌晨两点执行
    @Scheduled(fixedDelay = 720000)//上一次执行完毕时间点之后两小时后再执行
    public void synchronizeOrderInfo(){
        //循环获取渠道号对应的订单总数和已支付总数
        for (int channelId= 2001;channelId<=2030; channelId++){
            logger.info("...........频道号为: "+channelId+" 开始同步数据.......");
            System.out.println("...........频道号为: "+channelId+" 开始同步数据.......");
            //获取当前渠道的订单总量 和已支付订单量
            OrderInfoResponse orderInfoResponse = statisticsService.queryOrderInfoByChannel(channelId+"");

            //更新渠道表的中的订单总量和已支付订单量字段
            statisticsService.updateChannel(orderInfoResponse);
        }

        for (int channelId= 1015;channelId<=1020; channelId++){
            logger.info("...........频道号为: "+channelId+" 开始同步数据.......");
            System.out.println("...........频道号为: "+channelId+" 开始同步数据.......");
            //获取当前渠道的订单总量 和已支付订单量
            OrderInfoResponse orderInfoResponse = statisticsService.queryOrderInfoByChannel(channelId+"");

            //更新渠道表的中的订单总量和已支付订单量字段
            statisticsService.updateChannel(orderInfoResponse);
        }


    }

}
