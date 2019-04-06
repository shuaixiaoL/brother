package com.lan.innovation.service;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.MessageVo;
import com.lan.innovation.pojo.SpGoods;
import com.lan.innovation.pojo.SpMessage;

public interface MessageService {

    MessageVo queryMessage(MessageVo messageVo);

    BaseResultVo deleteMessage(String id);

    BaseResultVo deleteBatchMessage(String ids);

    BaseResultVo updateMessage(SpMessage spMessage);

    BaseResultVo addMessage(SpMessage spMessage, String userId);
}
