package com.dragon.mkh.service.impl;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.dragon.mkh.common.constant.ChannelConstant;
import com.dragon.mkh.entity.vo.response.ChannelResponse;
import com.dragon.mkh.service.IChannelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program MKH
 * @description: 渠道管理的service
 * @author: Gaofei
 * @create: 2018/11/26 10:09
 */

@Service(value = "channelService")
public class ChannelServiceImpl implements IChannelService {

    @Override
    public List<ChannelResponse> batchGenerateChannel(Integer startChannelID, Integer endChannelID, String pageID) {

        List<ChannelResponse> channelResponseList = new ArrayList<>();

        String cql = "insert into Channel(name, source,url,code,price,totalCount,payCount,isDel,creator) values " ;

        StringBuilder valuesStr = new StringBuilder();
        ChannelResponse  channelResponse = null;
        for (int i=startChannelID; i<=endChannelID; i++){
            //检查渠道号是否已经被使用，被使用跳过
            channelResponse = this.queryChannelByChannelID(i);
            if(null!=channelResponse && null!=channelResponse.getID()){
                continue;
            }else{
                channelResponse = new ChannelResponse();
                String name = "预备外链"+i;
                String source ="微信公众号";
                String url= ChannelConstant.CHANNEL_URL_PREFIX+pageID+ChannelConstant.CHANNEL_URL_SUFFIX+i;
                Integer code = i;
                Double price = 128.0;
                Integer totalCount=0;
                Integer payCount =0;
                valuesStr = valuesStr.append("('").append(name).append("',").append("'").append(source).append("',");
                valuesStr.append("'").append(url).append("','").append(code).append("',").append(price).append(",");
                valuesStr.append(totalCount).append(",").append(payCount).append(",").append(0)
                        .append(",pointer('_User','56cd6f947db2a2622918608c')").append(")");
                if(i!=endChannelID){
                    valuesStr.append(",") ;
                }

                //组装返回数据
                channelResponse.setName(name);
                channelResponse.setUrl(url);
                channelResponse.setPrice(price);
                channelResponse.setChannelCode(code);
                channelResponseList.add(channelResponse);
            }

        }
        cql = cql + valuesStr;
        try{
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql);
        }catch (Exception e){
            e.printStackTrace();
        }

        return channelResponseList;
    }


    private ChannelResponse queryChannelByChannelID(int channelID) {


        ChannelResponse channelResponse = new ChannelResponse();
        try{

            //查询渠道信息
            String cql = "select * from Channel where code ='" + channelID+"'";

            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery(cql);
           if(avCloudQueryResult.getResults().size()==0){
               return new ChannelResponse();
           }
            AVObject channel = avCloudQueryResult.getResults().get(0);

            if(null!=channel){
                channelResponse.setID(channel.getObjectId());
                channelResponse.setName(channel.get("name").toString());
                channelResponse.setChannelCode(channel.getInt("code"));
                channelResponse.setPrice(channel.getDouble("price"));
                channelResponse.setUrl(channel.getString("url"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return channelResponse;
    }
}
