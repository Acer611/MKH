package com.dragon.mkh.service;

import com.dragon.mkh.entity.vo.response.OrderInfoResponse;
import com.dragon.mkh.entity.vo.response.UserReadHistoryResponse;

import java.util.List;

/**
 * @description: 统计业务逻辑层接口
 * @author: Gaofei
 * @create: 2018/10/17 17:08
 */

public interface IStatisticsService {

    /**
     * 根据用户名查询用户最近浏览的50条文章的记录
     * @param userName
     * @return
     */
    List<UserReadHistoryResponse> statisticsUserReadHistory(String userName);

    /**
     * 根据渠道号查询渠道的订单信息
     * @param channelId
     * @return
     */
    OrderInfoResponse queryOrderInfoByChannel(String channelId);


    /**
     * 更新渠道表（channel表中的数据）
     * @param orderInfoResponse
     */
    void updateChannel(OrderInfoResponse orderInfoResponse);


    /**
     * 查询某一渠道区间下的订单信息
     * @param startChannelID
     * @param endChannelID
     * @return
     */
    List<OrderInfoResponse> listOrderInfoByChannel(Integer startChannelID, Integer endChannelID);
}
