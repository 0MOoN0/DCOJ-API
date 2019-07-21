package com.dcoj.entity.example;

import com.dcoj.exam.ExamJudgeStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Leon
 */
public class ExaminationSubmissionEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExaminationSubmissionEntityExample() {
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

        public Criteria andExamStatusIsNull() {
            addCriterion("exam_status is null");
            return (Criteria) this;
        }

        public Criteria andExamStatusIsNotNull() {
            addCriterion("exam_status is not null");
            return (Criteria) this;
        }

        public Criteria andExamStatusEqualTo(ExamJudgeStatus value) {
            addCriterion("exam_status =", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusNotEqualTo(ExamJudgeStatus value) {
            addCriterion("exam_status <>", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusGreaterThan(ExamJudgeStatus value) {
            addCriterion("exam_status >", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusGreaterThanOrEqualTo(ExamJudgeStatus value) {
            addCriterion("exam_status >=", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusLessThan(ExamJudgeStatus value) {
            addCriterion("exam_status <", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusLessThanOrEqualTo(ExamJudgeStatus value) {
            addCriterion("exam_status <=", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusLike(ExamJudgeStatus value) {
            addCriterion("exam_status like", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusNotLike(ExamJudgeStatus value) {
            addCriterion("exam_status not like", value, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusIn(List<ExamJudgeStatus> values) {
            addCriterion("exam_status in", values, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusNotIn(List<ExamJudgeStatus> values) {
            addCriterion("exam_status not in", values, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusBetween(ExamJudgeStatus value1, ExamJudgeStatus value2) {
            addCriterion("exam_status between", value1, value2, "examStatus");
            return (Criteria) this;
        }

        public Criteria andExamStatusNotBetween(ExamJudgeStatus value1, ExamJudgeStatus value2) {
            addCriterion("exam_status not between", value1, value2, "examStatus");
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

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Integer value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Integer value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Integer value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Integer value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Integer> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Integer> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
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

        public Criteria andQueryableTimeEqualTo(Date value) {
            addCriterion("queryable_time =", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeNotEqualTo(Date value) {
            addCriterion("queryable_time <>", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeGreaterThan(Date value) {
            addCriterion("queryable_time >", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("queryable_time >=", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeLessThan(Date value) {
            addCriterion("queryable_time <", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeLessThanOrEqualTo(Date value) {
            addCriterion("queryable_time <=", value, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeIn(List<Date> values) {
            addCriterion("queryable_time in", values, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeNotIn(List<Date> values) {
            addCriterion("queryable_time not in", values, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeBetween(Date value1, Date value2) {
            addCriterion("queryable_time between", value1, value2, "queryableTime");
            return (Criteria) this;
        }

        public Criteria andQueryableTimeNotBetween(Date value1, Date value2) {
            addCriterion("queryable_time not between", value1, value2, "queryableTime");
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