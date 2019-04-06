package com.lan.innovation.controller.message;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.MessageVo;
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
@RequestMapping("/api/messageCreate")
public class MessageCreateController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/queryMessageByUserId")
    @ResponseBody
    public MessageVo queryMessageByUserId(MessageVo messageVo, HttpSession session){
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        messageVo.setSysUserId(sysUser.getId());
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

    @RequestMapping("/updateMessage")
    @ResponseBody
    public BaseResultVo updateMessage(@RequestBody SpMessage spMessage) {
        spMessage.setMessageStatus("LY1");
        return messageService.updateMessage(spMessage);
    }

    @RequestMapping("/addMessage")
    @ResponseBody
    public BaseResultVo addMessage(@RequestBody SpMessage spMessage, HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return messageService.addMessage(spMessage, sysUser.getId());
    }

}
