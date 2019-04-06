package com.lan.innovation.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserProjectExample() {
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

        public Criteria andProjectNumIsNull() {
            addCriterion("project_num is null");
            return (Criteria) this;
        }

        public Criteria andProjectNumIsNotNull() {
            addCriterion("project_num is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNumEqualTo(String value) {
            addCriterion("project_num =", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumNotEqualTo(String value) {
            addCriterion("project_num <>", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumGreaterThan(String value) {
            addCriterion("project_num >", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumGreaterThanOrEqualTo(String value) {
            addCriterion("project_num >=", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumLessThan(String value) {
            addCriterion("project_num <", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumLessThanOrEqualTo(String value) {
            addCriterion("project_num <=", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumLike(String value) {
            addCriterion("project_num like", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumNotLike(String value) {
            addCriterion("project_num not like", value, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumIn(List<String> values) {
            addCriterion("project_num in", values, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumNotIn(List<String> values) {
            addCriterion("project_num not in", values, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumBetween(String value1, String value2) {
            addCriterion("project_num between", value1, value2, "projectNum");
            return (Criteria) this;
        }

        public Criteria andProjectNumNotBetween(String value1, String value2) {
            addCriterion("project_num not between", value1, value2, "projectNum");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNull() {
            addCriterion("user_num is null");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNotNull() {
            addCriterion("user_num is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumEqualTo(String value) {
            addCriterion("user_num =", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotEqualTo(String value) {
            addCriterion("user_num <>", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThan(String value) {
            addCriterion("user_num >", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThanOrEqualTo(String value) {
            addCriterion("user_num >=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThan(String value) {
            addCriterion("user_num <", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThanOrEqualTo(String value) {
            addCriterion("user_num <=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLike(String value) {
            addCriterion("user_num like", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotLike(String value) {
            addCriterion("user_num not like", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumIn(List<String> values) {
            addCriterion("user_num in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotIn(List<String> values) {
            addCriterion("user_num not in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumBetween(String value1, String value2) {
            addCriterion("user_num between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotBetween(String value1, String value2) {
            addCriterion("user_num not between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserLevelIsNull() {
            addCriterion("user_level is null");
            return (Criteria) this;
        }

        public Criteria andUserLevelIsNotNull() {
            addCriterion("user_level is not null");
            return (Criteria) this;
        }

        public Criteria andUserLevelEqualTo(Integer value) {
            addCriterion("user_level =", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotEqualTo(Integer value) {
            addCriterion("user_level <>", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelGreaterThan(Integer value) {
            addCriterion("user_level >", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_level >=", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelLessThan(Integer value) {
            addCriterion("user_level <", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelLessThanOrEqualTo(Integer value) {
            addCriterion("user_level <=", value, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelIn(List<Integer> values) {
            addCriterion("user_level in", values, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotIn(List<Integer> values) {
            addCriterion("user_level not in", values, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelBetween(Integer value1, Integer value2) {
            addCriterion("user_level between", value1, value2, "userLevel");
            return (Criteria) this;
        }

        public Criteria andUserLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("user_level not between", value1, value2, "userLevel");
            return (Criteria) this;
        }

        public Criteria andXgTimeIsNull() {
            addCriterion("xg_time is null");
            return (Criteria) this;
        }

        public Criteria andXgTimeIsNotNull() {
            addCriterion("xg_time is not null");
            return (Criteria) this;
        }

        public Criteria andXgTimeEqualTo(Date value) {
            addCriterion("xg_time =", value, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeNotEqualTo(Date value) {
            addCriterion("xg_time <>", value, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeGreaterThan(Date value) {
            addCriterion("xg_time >", value, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("xg_time >=", value, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeLessThan(Date value) {
            addCriterion("xg_time <", value, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeLessThanOrEqualTo(Date value) {
            addCriterion("xg_time <=", value, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeIn(List<Date> values) {
            addCriterion("xg_time in", values, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeNotIn(List<Date> values) {
            addCriterion("xg_time not in", values, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeBetween(Date value1, Date value2) {
            addCriterion("xg_time between", value1, value2, "xgTime");
            return (Criteria) this;
        }

        public Criteria andXgTimeNotBetween(Date value1, Date value2) {
            addCriterion("xg_time not between", value1, value2, "xgTime");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionIsNull() {
            addCriterion("finalre_jection is null");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionIsNotNull() {
            addCriterion("finalre_jection is not null");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionEqualTo(String value) {
            addCriterion("finalre_jection =", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionNotEqualTo(String value) {
            addCriterion("finalre_jection <>", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionGreaterThan(String value) {
            addCriterion("finalre_jection >", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionGreaterThanOrEqualTo(String value) {
            addCriterion("finalre_jection >=", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionLessThan(String value) {
            addCriterion("finalre_jection <", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionLessThanOrEqualTo(String value) {
            addCriterion("finalre_jection <=", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionLike(String value) {
            addCriterion("finalre_jection like", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionNotLike(String value) {
            addCriterion("finalre_jection not like", value, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionIn(List<String> values) {
            addCriterion("finalre_jection in", values, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionNotIn(List<String> values) {
            addCriterion("finalre_jection not in", values, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionBetween(String value1, String value2) {
            addCriterion("finalre_jection between", value1, value2, "finalreJection");
            return (Criteria) this;
        }

        public Criteria andFinalreJectionNotBetween(String value1, String value2) {
            addCriterion("finalre_jection not between", value1, value2, "finalreJection");
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