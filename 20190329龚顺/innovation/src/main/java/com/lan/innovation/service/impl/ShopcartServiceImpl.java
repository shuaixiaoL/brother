package com.lan.innovation.service.impl;

import com.lan.common.utils.DtfpUtils;
import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.GoodsVo;
import com.lan.common.vo.ShopcartVo;
import com.lan.innovation.dao.SpGoodsMapper;
import com.lan.innovation.dao.SpShopcartMapper;
import com.lan.innovation.dao.SysUserMapper;
import com.lan.innovation.pojo.*;
import com.lan.innovation.service.ShopcartService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopcartServiceImpl implements ShopcartService {

    @Autowired
    private SpShopcartMapper spShopcartMapper;

    @Autowired
    private SpGoodsMapper spGoodsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BaseResultVo addShopcart(GoodsVo goodsVo, String userId) {
        BaseResultVo brv = new BaseResultVo();
        //查询，如果存在，就更新，不存在，就添加
        // 1.先查询，就更新
        SpShopcartExample spShopcartExample = new SpShopcartExample();
        SpShopcartExample.Criteria criteria = spShopcartExample.createCriteria();
        criteria.andSpGoodIdEqualTo(goodsVo.getId());
        criteria.andSysUserIdEqualTo(userId);
        List<SpShopcart> spShopcarts = spShopcartMapper.selectByExample(spShopcartExample);
        if(spShopcarts.size() > 0) {
            SpShopcart result = spShopcarts.get(0);
            SpShopcart con = new SpShopcart();
            con.setId(result.getId());
            con.setNumber(result.getNumber() + goodsVo.getNumber());
            spShopcartMapper.updateByPrimaryKeySelective(con);
        } else {
            // 2.添加
            SpShopcart con = new SpShopcart();
            con.setId(DtfpUtils.getUUID());
            con.setNumber(goodsVo.getNumber());
            con.setSpGoodId(goodsVo.getId());
            con.setSysUserId(userId);
            spShopcartMapper.insert(con);
        }
        brv.setResultMsg("0000", "已加入购物车，请去购物车页面结算");
        return brv;
    }

    @Override
    public ShopcartVo queryShopcartByUserId(ShopcartVo shopcartVo, String userId) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlList = new StringBuffer();
        sqlList.append(" SELECT ss.*, sg.name, sg.price, sg.num  ");
        StringBuffer sqlCount = new StringBuffer();
        sqlCount.append(" select count(1) as aaa ");


        sql.append(" FROM sp_shopcart ss LEFT JOIN sp_goods sg ON sg.id = ss.sp_good_id  where 1 = 1  ");
        if(StringUtils.isNotBlank(shopcartVo.getName())) {
            sql.append(" and sg.name like '%" + shopcartVo.getName() + "%'");
        }
        if(StringUtils.isNotBlank(shopcartVo.getGoodsClassify())) {
            sql.append(" and sg.goods_classify in ('" + shopcartVo.getGoodsClassify() + "') ");
        }
        if(StringUtils.isNotBlank(userId)) {
            sql.append(" and ss.sys_user_id in ('" + userId + "') ");
        }
        sqlList.append(sql.toString());
        sqlList.append(" ORDER BY sg.update_time DESC ");
        sqlList.append(" LIMIT " + (shopcartVo.getCurrentPage() - 1) * shopcartVo.getPageSize() + "," + shopcartVo.getPageSize() );
        shopcartVo.setList(DtfpUtils.changeMapKeys(jdbcTemplate.queryForList(sqlList.toString())));

        sqlCount.append(sql.toString());
        shopcartVo.setTotalCount(Integer.parseInt(jdbcTemplate.queryForList(sqlCount.toString()).get(0).get("aaa").toString()));
        return shopcartVo;
    }

    @Override
    public BaseResultVo deleteShopcart(String id) {
        BaseResultVo brv = new BaseResultVo();
        int result = spShopcartMapper.deleteByPrimaryKey(id);
        if(result > 0) {
            brv.setResultMsg("0000", "删除成功");
        } else {
            brv.setResultMsg("9999", "删除失败");
        }
        return brv;
    }

    @Transactional
    @Override
    public BaseResultVo deleteBatchShopcart(String ids) {
        BaseResultVo brv = new BaseResultVo();

        SpShopcartExample spShopcartExample = new SpShopcartExample();
        SpShopcartExample.Criteria criteria = spShopcartExample.createCriteria();
        criteria.andIdIn(DtfpUtils.jsonArrToOrclInStr(ids));
        int result = spShopcartMapper.deleteByExample(spShopcartExample);
        if(result > 0) {
            brv.setResultMsg("0000", "批量删除成功");
        } else {
            brv.setResultMsg("9999", "批量删除失败");
        }
        return brv;
    }

    @Transactional
    @Override
    public BaseResultVo pay(ShopcartVo shopcartVo, String id) throws Exception {
        BaseResultVo brv = new BaseResultVo();

        //1.删除购物车
        int result = spShopcartMapper.deleteByPrimaryKey(shopcartVo.getId());
        if(result > 0) {
        } else {
            brv.setResultMsg("9999", "清空购物车失败");
            throw new Exception();
        }
        //2.减少库存
        SpGoods con = spGoodsMapper.selectByPrimaryKey(shopcartVo.getSpGoodId());
        con.setNum(con.getNum() - shopcartVo.getNumber());
        int result2 = spGoodsMapper.updateByPrimaryKeySelective(con);
        if(result2 > 0) {
        } else {
            brv.setResultMsg("9999", "减少库存失败");
            throw new Exception();
        }
        //3.添加到我的已购商品中（暂无）
        //4.修改我的小金库
        SysUser con2 = sysUserMapper.selectByPrimaryKey(id);
        con2.setMoney(con2.getMoney() - shopcartVo.getNumber() * shopcartVo.getPrice());
        int result3 = sysUserMapper.updateByPrimaryKeySelective(con2);
        if(result3 > 0) {
        } else {
            brv.setResultMsg("9999", "减少小金库失败");
            throw new Exception();
        }
        brv.setResultMsg("0000", "支付完成");
        return brv;
    }
}
