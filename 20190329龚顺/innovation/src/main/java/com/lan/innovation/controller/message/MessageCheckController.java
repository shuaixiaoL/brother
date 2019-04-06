package com.lan.innovation.controller.message;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.MessageVo;
import com.lan.innovation.pojo.SpMessage;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/messageCheck")
public class MessageCheckController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/queryMessage")
    @ResponseBody
    public MessageVo queryMessage(MessageVo messageVo){
        return messageService.queryMessage(messageVo);
    }

    @RequestMapping("/deleteMessage/{id}")
    @ResponseBody
    public BaseResultVo deleteMessage(@PathVariable String id) {
        return messageService.deleteMessage(id);
    }

    @RequestMapping("/deleteBatchMessage")
    @ResponseBody
    public BaseResultVo deleteBatchMessage(@RequestBody MessageVo messageVo) {
        return messageService.deleteBatchMessage(messageVo.getIds());
    }

    @RequestMapping("/updateMessageStatus")
    @ResponseBody
    public BaseResultVo updateMessageStatus(@RequestBody SpMessage spMessage, HttpSession session) {
        return messageService.updateMessage(spMessage);
    }

}
