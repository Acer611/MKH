package com.dragon.mkh.entity.vo.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @program slack
 * @description:
 * @author: Gaofei
 * @create: 2018/11/19 14:40
 */

public class OrderInfoResponse implements Serializable {

    //渠道号
    @ApiModelProperty(value = "渠道号")
    private String channelId;
    @ApiModelProperty(value = "渠道名称")
    private String channelTitle;
    @ApiModelProperty(value = "订单总量")
    private Integer totalCount;
    @ApiModelProperty(value = "已支付订单量")
    private  Integer payCount;
    @ApiModelProperty(value = "未支付订单量")
    private Integer notPayCount;
    @ApiModelProperty(value = "objectId")
    private String objectId;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public Integer getNotPayCount() {
        return notPayCount;
    }

    public void setNotPayCount(Integer notPayCount) {
        this.notPayCount = notPayCount;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
