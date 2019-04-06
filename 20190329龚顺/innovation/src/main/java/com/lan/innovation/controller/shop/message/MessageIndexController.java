package com.lan.innovation.controller.shop.message;

import com.lan.common.vo.MessageVo;
import com.lan.innovation.service.SpMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/messageIndex")
public class MessageIndexController {

    @Autowired
    private SpMessageService messageService;

    @RequestMapping("/queryMessageByStatus")
    @ResponseBody
    public MessageVo queryMessageByStatus(@RequestBody MessageVo messageVo) {
        messageVo.setMessageStatus("LY2");
        return messageService.queryMessage(messageVo);
    }
}
