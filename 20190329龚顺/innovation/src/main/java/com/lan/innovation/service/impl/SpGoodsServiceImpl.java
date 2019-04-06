package com.lan.innovation.service.impl;

import com.lan.common.utils.DtfpUtils;
import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.GoodsVo;
import com.lan.innovation.dao.SpGoodsMapper;
import com.lan.innovation.pojo.SpGoods;
import com.lan.innovation.pojo.SpGoodsExample;
import com.lan.innovation.service.SpGoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SpGoodsServiceImpl implements SpGoodsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SpGoodsMapper spGoodsMapper;
    

    @Override
    public GoodsVo queryGoods(GoodsVo goodsVo) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlList = new StringBuffer();
        sqlList.append(" SELECT sg.*, su.username  ");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append(" select count(1) as aaa ");


        sql.append(" FROM sp_goods sg LEFT JOIN sys_user su ON su.id = sg.sys_user_id where 1 = 1  ");
        if(StringUtils.isNotBlank(goodsVo.getName())) {
            sql.append(" and sg.name like '%" + goodsVo.getName() + "%'");
        }
        if(StringUtils.isNotBlank(goodsVo.getGoodsClassify())) {
            sql.append(" and sg.goods_classify in ('" + goodsVo.getGoodsClassify() + "') ");
        }
        sqlList.append(sql.toString());
        sqlList.append(" ORDER BY sg.update_time DESC ");
        sqlList.append(" LIMIT " + (goodsVo.getCurrentPage() - 1) * goodsVo.getPageSize() + "," + goodsVo.getPageSize() );
        goodsVo.setList(DtfpUtils.changeMapKeys(jdbcTemplate.queryForList(sqlList.toString())));

        sqlCount.append(sql.toString());
        goodsVo.setTotalCount(Integer.parseInt(jdbcTemplate.queryForList(sqlCount.toString()).get(0).get("aaa").toString()));
        return goodsVo;
    }


    @Override
    public GoodsVo queryGoodsByUserId(GoodsVo goodsVo, String userId) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlList = new StringBuffer();
        sqlList.append(" SELECT sg.*, su.username  ");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append(" select count(1) as aaa ");


        sql.append(" FROM sp_goods sg LEFT JOIN sys_user su ON su.id = sg.sys_user_id where 1 = 1  ");
        if(StringUtils.isNotBlank(goodsVo.getName())) {
            sql.append(" and sg.name like '%" + goodsVo.getName() + "%'");
        }
        if(StringUtils.isNotBlank(goodsVo.getGoodsClassify())) {
            sql.append(" and sg.goods_classify in ('" + goodsVo.getGoodsClassify() + "') ");
        }
        if(StringUtils.isNotBlank(userId)) {
            sql.append(" and sg.sys_user_id in ('" + userId + "') ");
        }
        sqlList.append(sql.toString());
        sqlList.append(" ORDER BY sg.update_time DESC ");
        sqlList.append(" LIMIT " + (goodsVo.getCurrentPage() - 1) * goodsVo.getPageSize() + "," + goodsVo.getPageSize() );
        goodsVo.setList(DtfpUtils.changeMapKeys(jdbcTemplate.queryForList(sqlList.toString())));

        sqlCount.append(sql.toString());
        goodsVo.setTotalCount(Integer.parseInt(jdbcTemplate.queryForList(sqlCount.toString()).get(0).get("aaa").toString()));
        return goodsVo;
    }

    @Transactional
    @Override
    public BaseResultVo deleteGoods(String id) {
        BaseResultVo brv = new BaseResultVo();
        int result = spGoodsMapper.deleteByPrimaryKey(id);
        if(result > 0) {
            brv.setResultMsg("0000", "删除成功");
        } else {
            brv.setResultMsg("9999", "删除失败");
        }
        return brv;
    }

    @Transactional
    @Override
    public BaseResultVo deleteBatchGoods(String ids) {
        BaseResultVo brv = new BaseResultVo();

        SpGoodsExample spGoodsExample = new SpGoodsExample();
        SpGoodsExample.Criteria criteria = spGoodsExample.createCriteria();
        criteria.andIdIn(DtfpUtils.jsonArrToOrclInStr(ids));
        int result = spGoodsMapper.deleteByExample(spGoodsExample);
        if(result > 0) {
            brv.setResultMsg("0000", "批量删除成功");
        } else {
            brv.setResultMsg("9999", "批量删除失败");
        }
        return brv;
    }

    @Override
    public BaseResultVo updateGoods(SpGoods spGoods, String userId) {
        BaseResultVo brv = new BaseResultVo();
        spGoods.setUpdateTime(new Date());  //可更新时间
        int result =  spGoodsMapper.updateByPrimaryKeySelective(spGoods);
        if(result > 0) {
            brv.setResultMsg("0000", "更新成功");
        } else {
            brv.setResultMsg("9999", "更新失败");
        }
        return brv;
    }

    @Override
    public BaseResultVo addGoods(SpGoods spGoods, String userId) {
        BaseResultVo brv = new BaseResultVo();
        spGoods.setId(DtfpUtils.getUUID());
        spGoods.setCreareTime(new Date());
        spGoods.setUpdateTime(new Date());  //可更新时间
        spGoods.setSysUserId(userId);
        int result =  spGoodsMapper.insert(spGoods);
        if(result > 0) {
            brv.setResultMsg("0000", "添加成功");
        } else {
            brv.setResultMsg("9999", "添加失败");
        }
        return brv;
    }
}
