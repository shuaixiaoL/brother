package com.lan.innovation.service.impl;

import com.lan.common.utils.DtfpUtils;
import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.MessageVo;
import com.lan.innovation.dao.SpMessageMapper;
import com.lan.innovation.pojo.SpMessage;
import com.lan.innovation.pojo.SpMessageExample;
import com.lan.innovation.service.SpMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SpMessageServiceImpl implements SpMessageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SpMessageMapper spMessageMapper;

    @Override
    public MessageVo queryMessage(MessageVo messageVo) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlList = new StringBuffer();
        sqlList.append(" SELECT sm.*, su.username  ");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append(" select count(1) as aaa ");

        sql.append(" FROM sp_message sm LEFT JOIN sys_user su ON su.id = sm.sys_user_id where 1 = 1  ");
        if(StringUtils.isNotBlank(messageVo.getMessage())) {
            sql.append(" and sm.message LIKE '%" + messageVo.getMessage() + "%' ");
        }
        if(StringUtils.isNotBlank(messageVo.getMessageStatus())) {
            sql.append(" and sm.message_status = '" + messageVo.getMessageStatus() + "' ");
        }
        if(StringUtils.isNotBlank(messageVo.getSysUserId())) {
            sql.append(" and sm.sys_user_id = '" + messageVo.getSysUserId() + "' ");
        }
        sqlList.append(sql.toString());
        sqlList.append(" ORDER BY sm.update_time DESC ");
        sqlList.append(" LIMIT " + (messageVo.getCurrentPage() - 1) * messageVo.getPageSize() + "," + messageVo.getPageSize() );
        messageVo.setList(DtfpUtils.changeMapKeys(jdbcTemplate.queryForList(sqlList.toString())));

        sqlCount.append(sql.toString());
        messageVo.setTotalCount(Integer.parseInt(jdbcTemplate.queryForList(sqlCount.toString()).get(0).get("aaa").toString()));
        return messageVo;
    }

    @Transactional
    @Override
    public BaseResultVo deleteMessage(String id) {
        BaseResultVo brv = new BaseResultVo();
        int result = spMessageMapper.deleteByPrimaryKey(id);
        if(result > 0) {
            brv.setResultMsg("0000", "删除成功");
        } else {
            brv.setResultMsg("9999", "删除失败");
        }
        return brv;
    }

    @Transactional
    @Override
    public BaseResultVo deleteBatchMessage(String ids) {
        BaseResultVo brv = new BaseResultVo();

        SpMessageExample example = new SpMessageExample();
        SpMessageExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(DtfpUtils.jsonArrToOrclInStr(ids));
        int result = spMessageMapper.deleteByExample(example);
        if(result > 0) {
            brv.setResultMsg("0000", "批量删除成功");
        } else {
            brv.setResultMsg("9999", "批量删除失败");
        }
        return brv;
    }

    @Transactional
    @Override
    public BaseResultVo updateMessage(SpMessage spMessage) {
        BaseResultVo brv = new BaseResultVo();
        if(StringUtils.isNotBlank(spMessage.getMessageStatus())) {
            spMessage.setMessageStatus(spMessage.getMessageStatus());
        }
        spMessage.setUpdateTime(new Date());  //可更新时间
        int result =  spMessageMapper.updateByPrimaryKeySelective(spMessage);
        if(result > 0) {
            brv.setResultMsg("0000", "更新成功");
        } else {
            brv.setResultMsg("9999", "更新失败");
        }
        return brv;
    }

    @Transactional
    @Override
    public BaseResultVo addMessage(SpMessage spMessage, String userId) {
        BaseResultVo brv = new BaseResultVo();
        spMessage.setId(DtfpUtils.getUUID());
        spMessage.setMessageStatus("LY1");
        spMessage.setCreateTime(new Date());
        spMessage.setUpdateTime(new Date());  //可更新时间
        spMessage.setSysUserId(userId);
        int result =  spMessageMapper.insert(spMessage);
        if(result > 0) {
            brv.setResultMsg("0000", "添加成功,状态变为未通过");
        } else {
            brv.setResultMsg("9999", "添加失败");
        }
        return brv;
    }

}
