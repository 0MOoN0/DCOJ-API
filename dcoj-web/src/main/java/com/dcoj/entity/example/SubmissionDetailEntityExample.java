package com.dcoj.entity.example;

import com.alibaba.fastjson.JSONObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SubmissionDetailEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubmissionDetailEntityExample() {
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

        public Criteria andSdIdIsNull() {
            addCriterion("sd_id is null");
            return (Criteria) this;
        }

        public Criteria andSdIdIsNotNull() {
            addCriterion("sd_id is not null");
            return (Criteria) this;
        }

        public Criteria andSdIdEqualTo(Integer value) {
            addCriterion("sd_id =", value, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdNotEqualTo(Integer value) {
            addCriterion("sd_id <>", value, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdGreaterThan(Integer value) {
            addCriterion("sd_id >", value, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sd_id >=", value, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdLessThan(Integer value) {
            addCriterion("sd_id <", value, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdLessThanOrEqualTo(Integer value) {
            addCriterion("sd_id <=", value, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdIn(List<Integer> values) {
            addCriterion("sd_id in", values, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdNotIn(List<Integer> values) {
            addCriterion("sd_id not in", values, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdBetween(Integer value1, Integer value2) {
            addCriterion("sd_id between", value1, value2, "sdId");
            return (Criteria) this;
        }

        public Criteria andSdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sd_id not between", value1, value2, "sdId");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailIsNull() {
            addCriterion("judge_detail is null");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailIsNotNull() {
            addCriterion("judge_detail is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailEqualTo(JSONObject value) {
            addCriterion("judge_detail =", value, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailNotEqualTo(JSONObject value) {
            addCriterion("judge_detail <>", value, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailGreaterThan(JSONObject value) {
            addCriterion("judge_detail >", value, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailGreaterThanOrEqualTo(JSONObject value) {
            addCriterion("judge_detail >=", value, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailLessThan(JSONObject value) {
            addCriterion("judge_detail <", value, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailLessThanOrEqualTo(JSONObject value) {
            addCriterion("judge_detail <=", value, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailIn(List<JSONObject> values) {
            addCriterion("judge_detail in", values, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailNotIn(List<JSONObject> values) {
            addCriterion("judge_detail not in", values, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailBetween(JSONObject value1, JSONObject value2) {
            addCriterion("judge_detail between", value1, value2, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andJudgeDetailNotBetween(JSONObject value1, JSONObject value2) {
            addCriterion("judge_detail not between", value1, value2, "judgeDetail");
            return (Criteria) this;
        }

        public Criteria andSubIdIsNull() {
            addCriterion("sub_id is null");
            return (Criteria) this;
        }

        public Criteria andSubIdIsNotNull() {
            addCriterion("sub_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubIdEqualTo(Integer value) {
            addCriterion("sub_id =", value, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdNotEqualTo(Integer value) {
            addCriterion("sub_id <>", value, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdGreaterThan(Integer value) {
            addCriterion("sub_id >", value, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_id >=", value, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdLessThan(Integer value) {
            addCriterion("sub_id <", value, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdLessThanOrEqualTo(Integer value) {
            addCriterion("sub_id <=", value, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdIn(List<Integer> values) {
            addCriterion("sub_id in", values, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdNotIn(List<Integer> values) {
            addCriterion("sub_id not in", values, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdBetween(Integer value1, Integer value2) {
            addCriterion("sub_id between", value1, value2, "subId");
            return (Criteria) this;
        }

        public Criteria andSubIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_id not between", value1, value2, "subId");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Timestamp value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Timestamp value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Timestamp value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Timestamp value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Timestamp value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Timestamp> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Timestamp> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Timestamp value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Timestamp value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Timestamp value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Timestamp value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Timestamp value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Timestamp> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Timestamp> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Timestamp value1, Timestamp value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
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