package com.dragon.mkh.service.impl;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.dragon.mkh.common.utils.DateUtils;
import com.dragon.mkh.entity.vo.response.OrderInfoResponse;
import com.dragon.mkh.entity.vo.response.UserReadHistoryResponse;
import com.dragon.mkh.service.IStatisticsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 统计业务逻辑层接口
 * @author: Gaofei
 * @create: 2018/10/18 13:12
 */

@Service(value = "statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {

    /**
     * 统计用户浏览文章记录，默认取前50条数据
     * @param userName
     * @return
     */
    @Override
    public List<UserReadHistoryResponse> statisticsUserReadHistory(String userName) {
        List<UserReadHistoryResponse> resultList = new ArrayList<>();

        //根据用户名获取用户的信息
        String cql = "select * from _User where username ='" + userName+"'";

        try{
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql);
            AVObject user = avCloudQueryResult.getResults().get(0);
            String userId = user.getObjectId();

            //文章访问记录表获取用户访问记录并且按照时间倒序排列
            AVQuery<AVObject> articleQuery = new AVQuery<>("ArticleVisit");

            articleQuery.whereEqualTo("user", AVObject.createWithoutData("_User", userId));
            articleQuery.orderByDescending("updatedAt");
            articleQuery.limit(50);
            articleQuery.include("article");
            List<AVObject> list = articleQuery.find();

            //组装数据  用户名 、 那本杂志、 哪一年、哪一期、文章标题、读了几次 、 最后一次读的时间
            for (AVObject object:list) {
                UserReadHistoryResponse userReadHistoryResponse = new UserReadHistoryResponse();
                //获取文章对象信息
                AVObject articleObject = object.getAVObject("article");

                //组装返回数据对象
                userReadHistoryResponse.setUserName(userName);
                userReadHistoryResponse.setMagazineName(null!=articleObject.get("resourceName")?articleObject.get("resourceName").toString():"");
                userReadHistoryResponse.setArticleName(null!=articleObject.get("title")?articleObject.get("title").toString():"");
                userReadHistoryResponse.setReadCount(Long.parseLong(object.get("visitCount").toString()));
                userReadHistoryResponse.setDate(DateUtils.CST2Date(object.getDate("updatedAt").toString()));
                userReadHistoryResponse.setYear(null!=articleObject.get("year")?articleObject.get("year").toString():"");
                userReadHistoryResponse.setIssue(null!=articleObject.get("issue")?articleObject.get("issue").toString():"");

               /* map.put("用户名",userName);
                map.put("杂志名",articleObject.get("resourceName"));
                map.put("文章标题",articleObject.get("title"));
                map.put("阅读次数",object.getInt("visitCount"));
                map.put("阅读时间",DateUtils.CST2Date(object.getDate("updatedAt").toString()) );*/
                resultList.add(userReadHistoryResponse);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return resultList;
    }

    /**
     *
     * @param channelId
     * @return
     */
    @Override
    public OrderInfoResponse queryOrderInfoByChannel(String channelId) {

        OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
        try{
            Thread.sleep(1000L);
            //查询渠道信息
            String cql = "select * from Channel where code ='" + channelId+"'";

            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql);
            AVObject channel = avCloudQueryResult.getResults().get(0);
            String channelName = channel.getString("name");

            orderInfoResponse.setChannelId(channelId);
            orderInfoResponse.setChannelTitle(channelName);

            orderInfoResponse.setObjectId(channel.getObjectId());

            //扣费成功订单量
            //已支付订单状态
            final AVQuery<AVObject> statusQuery0 = new AVQuery<>("UserOrderList");
            statusQuery0.whereEqualTo("status", 0);
            //渠道号
            final AVQuery<AVObject> channelQuery = new AVQuery<>("UserOrderList");
            channelQuery.whereEqualTo("channel", channelId);

            AVQuery<AVObject> payQuery = AVQuery.and(Arrays.asList(statusQuery0, channelQuery));

            int payCount = payQuery.count();

            orderInfoResponse.setPayCount(payCount);

            //未支付订单状态
            final AVQuery<AVObject> statusQuery1 = new AVQuery<>("UserOrderList");
            statusQuery1.whereEqualTo("status", 1);

            AVQuery<AVObject> notPayQuery = AVQuery.and(Arrays.asList(statusQuery1, channelQuery));
            int notPayCount = notPayQuery.count();
            orderInfoResponse.setNotPayCount(notPayCount);

            orderInfoResponse.setTotalCount(payCount+notPayCount);


        }catch (Exception e){
            e.printStackTrace();
        }

        return orderInfoResponse;
    }

    /**
     * 修改Channel表中的订单总数量和支付数量
     * @param orderInfoResponse
     */
    @Override
    public void updateChannel(OrderInfoResponse orderInfoResponse) {
        if(null!=orderInfoResponse.getChannelId()){
            try{
                int totalCount = null==orderInfoResponse.getTotalCount()?0 :orderInfoResponse.getTotalCount();
                int payCount = null==orderInfoResponse.getPayCount()?0 :orderInfoResponse.getPayCount();

                //修改
                String updateSQL = "update Channel set totalCount="+totalCount+", payCount="+payCount+" where objectId='"+orderInfoResponse.getObjectId()+"'";
                AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(updateSQL);
            }catch (Exception e){
                e.printStackTrace();
            }

         }
    }

    @Override
    public List<OrderInfoResponse> listOrderInfoByChannel(Integer startChannelID, Integer endChannelID) {

        List<OrderInfoResponse>  orderInfoResponseList = new ArrayList<>();
        for (int i = startChannelID; i <=endChannelID; i++) {
            OrderInfoResponse orderInfoResponse = queryOrderInfoByChannel(i+"");
            orderInfoResponseList.add(orderInfoResponse);
        }
        return orderInfoResponseList;
    }
}
