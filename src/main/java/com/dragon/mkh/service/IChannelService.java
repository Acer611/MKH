package com.dragon.mkh.service;


import com.dragon.mkh.entity.vo.response.ChannelResponse;

import java.util.List;

/**
 * 渠道接口
 */
public interface IChannelService {
    /**
     *
     * @param startChannelID
     * @param endChannelID
     * @return
     */
    List<ChannelResponse> batchGenerateChannel(Integer startChannelID, Integer endChannelID,String pageID);
}
