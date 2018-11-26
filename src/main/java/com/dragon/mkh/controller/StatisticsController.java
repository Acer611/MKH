package com.dragon.mkh.controller;

import com.dragon.mkh.entity.vo.response.OrderInfoResponse;
import com.dragon.mkh.entity.vo.response.UserReadHistoryResponse;
import com.dragon.mkh.service.IStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @description: 数据统计接口
 * @author: Gaofei
 * @create: 2018/10/17 16:57
 */


@Api(tags = "数据统计接口")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;

    /**
     * 统计用户浏览记录，默认区前50条
     * @param userName
     * @return
     */

    @ApiOperation(value = "获取用户浏览文章记录，取最近的50条")
    @ResponseBody
    @GetMapping(value = "/userReadHistory"/*,produces = {"application/json","application/xml"}*/)
    public List<UserReadHistoryResponse> statisticsUserReadHistory(@ApiParam(value = "用户名", required = true)
                                                      @RequestParam(name = "userName") String  userName){

        return  statisticsService.statisticsUserReadHistory(userName);
    }

    /**
     * 根据渠道号查询渠道下的订单情况
     * @param channelID
     * @return
     */

    @ApiOperation(value = "获取渠道号下的订单情况")
    @ResponseBody
    @GetMapping(value = "/queryOrderInfoByChannel"/*,produces = {"application/json","application/xml"}*/)
    public OrderInfoResponse queryOrderInfoByChannel(@ApiParam(value = "渠道号", required = true)
                                                                   @RequestParam(name = "channelID") String  channelID){

        return  statisticsService.queryOrderInfoByChannel(channelID);
    }

    /**
     * 查询某一渠道区间下的的订单信息
     * @param startChannelID
     * @param endChannelID
     * @return
     */

    @ApiOperation(value = "获取某一渠道区间下订单情况")
    @ResponseBody
    @GetMapping(value = "/listOrderInfoByChannel"/*,produces = {"application/json","application/xml"}*/)
    public List<OrderInfoResponse> listOrderInfoByChannel(@ApiParam(value = "开始渠道号", required = true)
                                                          @RequestParam(name = "startChannelID") Integer  startChannelID,
                                                          @ApiParam(value = "结束渠道号", required = true)
                                                          @RequestParam(name = "endChannelID") Integer  endChannelID){

        if(endChannelID<startChannelID){
            return null;
        }
        return statisticsService.listOrderInfoByChannel(startChannelID,endChannelID);
    }
}
