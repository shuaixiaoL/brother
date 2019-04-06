package com.lan.innovation.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescInfoIsNull() {
            addCriterion("desc_info is null");
            return (Criteria) this;
        }

        public Criteria andDescInfoIsNotNull() {
            addCriterion("desc_info is not null");
            return (Criteria) this;
        }

        public Criteria andDescInfoEqualTo(String value) {
            addCriterion("desc_info =", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoNotEqualTo(String value) {
            addCriterion("desc_info <>", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoGreaterThan(String value) {
            addCriterion("desc_info >", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoGreaterThanOrEqualTo(String value) {
            addCriterion("desc_info >=", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoLessThan(String value) {
            addCriterion("desc_info <", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoLessThanOrEqualTo(String value) {
            addCriterion("desc_info <=", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoLike(String value) {
            addCriterion("desc_info like", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoNotLike(String value) {
            addCriterion("desc_info not like", value, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoIn(List<String> values) {
            addCriterion("desc_info in", values, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoNotIn(List<String> values) {
            addCriterion("desc_info not in", values, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoBetween(String value1, String value2) {
            addCriterion("desc_info between", value1, value2, "descInfo");
            return (Criteria) this;
        }

        public Criteria andDescInfoNotBetween(String value1, String value2) {
            addCriterion("desc_info not between", value1, value2, "descInfo");
            return (Criteria) this;
        }

        public Criteria andAttachdocIsNull() {
            addCriterion("attachdoc is null");
            return (Criteria) this;
        }

        public Criteria andAttachdocIsNotNull() {
            addCriterion("attachdoc is not null");
            return (Criteria) this;
        }

        public Criteria andAttachdocEqualTo(String value) {
            addCriterion("attachdoc =", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocNotEqualTo(String value) {
            addCriterion("attachdoc <>", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocGreaterThan(String value) {
            addCriterion("attachdoc >", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocGreaterThanOrEqualTo(String value) {
            addCriterion("attachdoc >=", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocLessThan(String value) {
            addCriterion("attachdoc <", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocLessThanOrEqualTo(String value) {
            addCriterion("attachdoc <=", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocLike(String value) {
            addCriterion("attachdoc like", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocNotLike(String value) {
            addCriterion("attachdoc not like", value, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocIn(List<String> values) {
            addCriterion("attachdoc in", values, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocNotIn(List<String> values) {
            addCriterion("attachdoc not in", values, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocBetween(String value1, String value2) {
            addCriterion("attachdoc between", value1, value2, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andAttachdocNotBetween(String value1, String value2) {
            addCriterion("attachdoc not between", value1, value2, "attachdoc");
            return (Criteria) this;
        }

        public Criteria andSqTimeIsNull() {
            addCriterion("sq_time is null");
            return (Criteria) this;
        }

        public Criteria andSqTimeIsNotNull() {
            addCriterion("sq_time is not null");
            return (Criteria) this;
        }

        public Criteria andSqTimeEqualTo(Date value) {
            addCriterion("sq_time =", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeNotEqualTo(Date value) {
            addCriterion("sq_time <>", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeGreaterThan(Date value) {
            addCriterion("sq_time >", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sq_time >=", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeLessThan(Date value) {
            addCriterion("sq_time <", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeLessThanOrEqualTo(Date value) {
            addCriterion("sq_time <=", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeIn(List<Date> values) {
            addCriterion("sq_time in", values, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeNotIn(List<Date> values) {
            addCriterion("sq_time not in", values, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeBetween(Date value1, Date value2) {
            addCriterion("sq_time between", value1, value2, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeNotBetween(Date value1, Date value2) {
            addCriterion("sq_time not between", value1, value2, "sqTime");
            return (Criteria) this;
        }

        public Criteria andWorkIsNull() {
            addCriterion("work is null");
            return (Criteria) this;
        }

        public Criteria andWorkIsNotNull() {
            addCriterion("work is not null");
            return (Criteria) this;
        }

        public Criteria andWorkEqualTo(Integer value) {
            addCriterion("work =", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotEqualTo(Integer value) {
            addCriterion("work <>", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkGreaterThan(Integer value) {
            addCriterion("work >", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkGreaterThanOrEqualTo(Integer value) {
            addCriterion("work >=", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLessThan(Integer value) {
            addCriterion("work <", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLessThanOrEqualTo(Integer value) {
            addCriterion("work <=", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkIn(List<Integer> values) {
            addCriterion("work in", values, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotIn(List<Integer> values) {
            addCriterion("work not in", values, "work");
            return (Criteria) this;
        }

        public Criteria andWorkBetween(Integer value1, Integer value2) {
            addCriterion("work between", value1, value2, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotBetween(Integer value1, Integer value2) {
            addCriterion("work not between", value1, value2, "work");
            return (Criteria) this;
        }

        public Criteria andFundsIsNull() {
            addCriterion("funds is null");
            return (Criteria) this;
        }

        public Criteria andFundsIsNotNull() {
            addCriterion("funds is not null");
            return (Criteria) this;
        }

        public Criteria andFundsEqualTo(Integer value) {
            addCriterion("funds =", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotEqualTo(Integer value) {
            addCriterion("funds <>", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsGreaterThan(Integer value) {
            addCriterion("funds >", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsGreaterThanOrEqualTo(Integer value) {
            addCriterion("funds >=", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsLessThan(Integer value) {
            addCriterion("funds <", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsLessThanOrEqualTo(Integer value) {
            addCriterion("funds <=", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsIn(List<Integer> values) {
            addCriterion("funds in", values, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotIn(List<Integer> values) {
            addCriterion("funds not in", values, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsBetween(Integer value1, Integer value2) {
            addCriterion("funds between", value1, value2, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotBetween(Integer value1, Integer value2) {
            addCriterion("funds not between", value1, value2, "funds");
            return (Criteria) this;
        }

        public Criteria andProgressStatusIsNull() {
            addCriterion("progress_status is null");
            return (Criteria) this;
        }

        public Criteria andProgressStatusIsNotNull() {
            addCriterion("progress_status is not null");
            return (Criteria) this;
        }

        public Criteria andProgressStatusEqualTo(Integer value) {
            addCriterion("progress_status =", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusNotEqualTo(Integer value) {
            addCriterion("progress_status <>", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusGreaterThan(Integer value) {
            addCriterion("progress_status >", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("progress_status >=", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusLessThan(Integer value) {
            addCriterion("progress_status <", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusLessThanOrEqualTo(Integer value) {
            addCriterion("progress_status <=", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusIn(List<Integer> values) {
            addCriterion("progress_status in", values, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusNotIn(List<Integer> values) {
            addCriterion("progress_status not in", values, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusBetween(Integer value1, Integer value2) {
            addCriterion("progress_status between", value1, value2, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("progress_status not between", value1, value2, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusIsNull() {
            addCriterion("apply_status is null");
            return (Criteria) this;
        }

        public Criteria andApplyStatusIsNotNull() {
            addCriterion("apply_status is not null");
            return (Criteria) this;
        }

        public Criteria andApplyStatusEqualTo(Integer value) {
            addCriterion("apply_status =", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotEqualTo(Integer value) {
            addCriterion("apply_status <>", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusGreaterThan(Integer value) {
            addCriterion("apply_status >", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_status >=", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusLessThan(Integer value) {
            addCriterion("apply_status <", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("apply_status <=", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusIn(List<Integer> values) {
            addCriterion("apply_status in", values, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotIn(List<Integer> values) {
            addCriterion("apply_status not in", values, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusBetween(Integer value1, Integer value2) {
            addCriterion("apply_status between", value1, value2, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_status not between", value1, value2, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andYnIsNull() {
            addCriterion("yn is null");
            return (Criteria) this;
        }

        public Criteria andYnIsNotNull() {
            addCriterion("yn is not null");
            return (Criteria) this;
        }

        public Criteria andYnEqualTo(Integer value) {
            addCriterion("yn =", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnNotEqualTo(Integer value) {
            addCriterion("yn <>", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnGreaterThan(Integer value) {
            addCriterion("yn >", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnGreaterThanOrEqualTo(Integer value) {
            addCriterion("yn >=", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnLessThan(Integer value) {
            addCriterion("yn <", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnLessThanOrEqualTo(Integer value) {
            addCriterion("yn <=", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnIn(List<Integer> values) {
            addCriterion("yn in", values, "yn");
            return (Criteria) this;
        }

        public Criteria andYnNotIn(List<Integer> values) {
            addCriterion("yn not in", values, "yn");
            return (Criteria) this;
        }

        public Criteria andYnBetween(Integer value1, Integer value2) {
            addCriterion("yn between", value1, value2, "yn");
            return (Criteria) this;
        }

        public Criteria andYnNotBetween(Integer value1, Integer value2) {
            addCriterion("yn not between", value1, value2, "yn");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}