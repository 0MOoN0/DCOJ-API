package com.dcoj.entity.example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ObjectSubmissionEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObjectSubmissionEntityExample() {
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

        public Criteria andObjectSubmitIdIsNull() {
            addCriterion("object_submit_id is null");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdIsNotNull() {
            addCriterion("object_submit_id is not null");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdEqualTo(Integer value) {
            addCriterion("object_submit_id =", value, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdNotEqualTo(Integer value) {
            addCriterion("object_submit_id <>", value, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdGreaterThan(Integer value) {
            addCriterion("object_submit_id >", value, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("object_submit_id >=", value, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdLessThan(Integer value) {
            addCriterion("object_submit_id <", value, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdLessThanOrEqualTo(Integer value) {
            addCriterion("object_submit_id <=", value, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdIn(List<Integer> values) {
            addCriterion("object_submit_id in", values, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdNotIn(List<Integer> values) {
            addCriterion("object_submit_id not in", values, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdBetween(Integer value1, Integer value2) {
            addCriterion("object_submit_id between", value1, value2, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andObjectSubmitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("object_submit_id not between", value1, value2, "objectSubmitId");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdIsNull() {
            addCriterion("object_problem_id is null");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdIsNotNull() {
            addCriterion("object_problem_id is not null");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdEqualTo(Integer value) {
            addCriterion("object_problem_id =", value, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdNotEqualTo(Integer value) {
            addCriterion("object_problem_id <>", value, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdGreaterThan(Integer value) {
            addCriterion("object_problem_id >", value, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("object_problem_id >=", value, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdLessThan(Integer value) {
            addCriterion("object_problem_id <", value, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdLessThanOrEqualTo(Integer value) {
            addCriterion("object_problem_id <=", value, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdIn(List<Integer> values) {
            addCriterion("object_problem_id in", values, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdNotIn(List<Integer> values) {
            addCriterion("object_problem_id not in", values, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdBetween(Integer value1, Integer value2) {
            addCriterion("object_problem_id between", value1, value2, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andObjectProblemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("object_problem_id not between", value1, value2, "objectProblemId");
            return (Criteria) this;
        }

        public Criteria andResultStatusIsNull() {
            addCriterion("result_status is null");
            return (Criteria) this;
        }

        public Criteria andResultStatusIsNotNull() {
            addCriterion("result_status is not null");
            return (Criteria) this;
        }

        public Criteria andResultStatusEqualTo(Byte value) {
            addCriterion("result_status =", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusNotEqualTo(Byte value) {
            addCriterion("result_status <>", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusGreaterThan(Byte value) {
            addCriterion("result_status >", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("result_status >=", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusLessThan(Byte value) {
            addCriterion("result_status <", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusLessThanOrEqualTo(Byte value) {
            addCriterion("result_status <=", value, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusIn(List<Byte> values) {
            addCriterion("result_status in", values, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusNotIn(List<Byte> values) {
            addCriterion("result_status not in", values, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusBetween(Byte value1, Byte value2) {
            addCriterion("result_status between", value1, value2, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andResultStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("result_status not between", value1, value2, "resultStatus");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
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

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Byte value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Byte value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Byte value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Byte value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Byte value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Byte value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Byte> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Byte> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Byte value1, Byte value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Byte value1, Byte value2) {
            addCriterion("score not between", value1, value2, "score");
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