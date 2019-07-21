package com.dcoj.entity.example;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 */
public class ExaminationSubmissionDetailEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExaminationSubmissionDetailEntityExample() {
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

        public Criteria andExamSubmissionDetailIdIsNull() {
            addCriterion("exam_submission_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdIsNotNull() {
            addCriterion("exam_submission_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdEqualTo(Integer value) {
            addCriterion("exam_submission_detail_id =", value, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdNotEqualTo(Integer value) {
            addCriterion("exam_submission_detail_id <>", value, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdGreaterThan(Integer value) {
            addCriterion("exam_submission_detail_id >", value, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_submission_detail_id >=", value, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdLessThan(Integer value) {
            addCriterion("exam_submission_detail_id <", value, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_submission_detail_id <=", value, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdIn(List<Integer> values) {
            addCriterion("exam_submission_detail_id in", values, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdNotIn(List<Integer> values) {
            addCriterion("exam_submission_detail_id not in", values, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_submission_detail_id between", value1, value2, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_submission_detail_id not between", value1, value2, "examSubmissionDetailId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdIsNull() {
            addCriterion("exam_submission_id is null");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdIsNotNull() {
            addCriterion("exam_submission_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdEqualTo(Integer value) {
            addCriterion("exam_submission_id =", value, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdNotEqualTo(Integer value) {
            addCriterion("exam_submission_id <>", value, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdGreaterThan(Integer value) {
            addCriterion("exam_submission_id >", value, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_submission_id >=", value, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdLessThan(Integer value) {
            addCriterion("exam_submission_id <", value, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_submission_id <=", value, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdIn(List<Integer> values) {
            addCriterion("exam_submission_id in", values, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdNotIn(List<Integer> values) {
            addCriterion("exam_submission_id not in", values, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_submission_id between", value1, value2, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andExamSubmissionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_submission_id not between", value1, value2, "examSubmissionId");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetIsNull() {
            addCriterion("answer_sheet is null");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetIsNotNull() {
            addCriterion("answer_sheet is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetEqualTo(JSONObject value) {
            addCriterion("answer_sheet =", value, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetNotEqualTo(JSONObject value) {
            addCriterion("answer_sheet <>", value, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetGreaterThan(JSONObject value) {
            addCriterion("answer_sheet >", value, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetGreaterThanOrEqualTo(JSONObject value) {
            addCriterion("answer_sheet >=", value, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetLessThan(JSONObject value) {
            addCriterion("answer_sheet <", value, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetLessThanOrEqualTo(JSONObject value) {
            addCriterion("answer_sheet <=", value, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetIn(List<JSONObject> values) {
            addCriterion("answer_sheet in", values, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetNotIn(List<JSONObject> values) {
            addCriterion("answer_sheet not in", values, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetBetween(JSONObject value1, JSONObject value2) {
            addCriterion("answer_sheet between", value1, value2, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andAnswerSheetNotBetween(JSONObject value1, JSONObject value2) {
            addCriterion("answer_sheet not between", value1, value2, "answerSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetIsNull() {
            addCriterion("result_sheet is null");
            return (Criteria) this;
        }

        public Criteria andResultSheetIsNotNull() {
            addCriterion("result_sheet is not null");
            return (Criteria) this;
        }

        public Criteria andResultSheetEqualTo(JSONObject value) {
            addCriterion("result_sheet =", value, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetNotEqualTo(JSONObject value) {
            addCriterion("result_sheet <>", value, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetGreaterThan(JSONObject value) {
            addCriterion("result_sheet >", value, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetGreaterThanOrEqualTo(JSONObject value) {
            addCriterion("result_sheet >=", value, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetLessThan(JSONObject value) {
            addCriterion("result_sheet <", value, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetLessThanOrEqualTo(JSONObject value) {
            addCriterion("result_sheet <=", value, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetIn(List<JSONObject> values) {
            addCriterion("result_sheet in", values, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetNotIn(List<JSONObject> values) {
            addCriterion("result_sheet not in", values, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetBetween(JSONObject value1, JSONObject value2) {
            addCriterion("result_sheet between", value1, value2, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andResultSheetNotBetween(JSONObject value1, JSONObject value2) {
            addCriterion("result_sheet not between", value1, value2, "resultSheet");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeIsNull() {
            addCriterion("queryable_time is null");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeIsNotNull() {
            addCriterion("queryable_time is not null");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeEqualTo(Timestamp value) {
            addCriterion("queryable_time =", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeNotEqualTo(Timestamp value) {
            addCriterion("queryable_time <>", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeGreaterThan(Timestamp value) {
            addCriterion("queryable_time >", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("queryable_time >=", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeLessThan(Timestamp value) {
            addCriterion("queryable_time <", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("queryable_time <=", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeIn(List<Timestamp> values) {
            addCriterion("queryable_time in", values, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeNotIn(List<Timestamp> values) {
            addCriterion("queryable_time not in", values, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("queryable_time between", value1, value2, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("queryable_time not between", value1, value2, "queryableTime");
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