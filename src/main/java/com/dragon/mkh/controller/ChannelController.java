package com.dragon.mkh.controller;

import com.dragon.mkh.entity.vo.response.ChannelResponse;
import com.dragon.mkh.service.IChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program MKH
 * @description: 生成渠道号
 * @author: Gaofei
 * @create: 2018/11/26 09:43
 */
@Api(tags = "渠道接口")
@RestController
@RequestMapping("/channel")
public class ChannelController {


    @Autowired
    private IChannelService channelService;


    /**
     * 批量生成渠道的接口
     * @param startChannelID
     * @param endChannelID
     * @param pageID
     * @return
     */
    @ApiOperation(value = "根据渠道号生成渠道号外链")
    @ResponseBody
    @GetMapping(value = "/batchGenerateChannel")
    public List<ChannelResponse> batchGenerateChannel(@ApiParam(value = "开始渠道号", required = true)
                                                      @RequestParam(name = "startChannelID") Integer  startChannelID,
                                                      @ApiParam(value = "结束渠道号", required = true)
                                                      @RequestParam(name = "endChannelID") Integer  endChannelID,
                                                      @ApiParam(value = "外链要跳转页面的ID,可到老系统新建或查询需要的页面，" +
                                                              "目前大多使用的为：5a3a0ff275657100436719b8 ", required = true)
                                                      @RequestParam(name = "pageID") String  pageID ){
        List<ChannelResponse> channelResponseList = new ArrayList<>();
        channelResponseList = channelService.batchGenerateChannel(startChannelID,endChannelID,pageID);

        return channelResponseList;
    }
}
