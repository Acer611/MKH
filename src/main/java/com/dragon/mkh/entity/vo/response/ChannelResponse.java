package com.dragon.mkh.entity.vo.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @program MKH
 * @description:
 * @author: Gaofei
 * @create: 2018/11/26 09:47
 */

public class ChannelResponse  implements Serializable {

    @ApiModelProperty(value = "唯一标识")
    private String ID;
    @ApiModelProperty(value = "渠道号")
    private Integer channelCode;
    @ApiModelProperty(value = "渠道名称")
    private String name;
    @ApiModelProperty(value = "渠道外链")
    private String url;
    @ApiModelProperty(value = "价格")
    private Double price;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(Integer channelCode) {
        this.channelCode = channelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
