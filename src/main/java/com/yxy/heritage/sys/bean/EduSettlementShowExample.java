package com.yxy.heritage.sys.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EduSettlementShowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EduSettlementShowExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andNoSettlementIsNull() {
            addCriterion("no_settlement is null");
            return (Criteria) this;
        }

        public Criteria andNoSettlementIsNotNull() {
            addCriterion("no_settlement is not null");
            return (Criteria) this;
        }

        public Criteria andNoSettlementEqualTo(BigDecimal value) {
            addCriterion("no_settlement =", value, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementNotEqualTo(BigDecimal value) {
            addCriterion("no_settlement <>", value, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementGreaterThan(BigDecimal value) {
            addCriterion("no_settlement >", value, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("no_settlement >=", value, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementLessThan(BigDecimal value) {
            addCriterion("no_settlement <", value, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementLessThanOrEqualTo(BigDecimal value) {
            addCriterion("no_settlement <=", value, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementIn(List<BigDecimal> values) {
            addCriterion("no_settlement in", values, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementNotIn(List<BigDecimal> values) {
            addCriterion("no_settlement not in", values, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("no_settlement between", value1, value2, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andNoSettlementNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("no_settlement not between", value1, value2, "noSettlement");
            return (Criteria) this;
        }

        public Criteria andClearingIsNull() {
            addCriterion("clearing is null");
            return (Criteria) this;
        }

        public Criteria andClearingIsNotNull() {
            addCriterion("clearing is not null");
            return (Criteria) this;
        }

        public Criteria andClearingEqualTo(BigDecimal value) {
            addCriterion("clearing =", value, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingNotEqualTo(BigDecimal value) {
            addCriterion("clearing <>", value, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingGreaterThan(BigDecimal value) {
            addCriterion("clearing >", value, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("clearing >=", value, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingLessThan(BigDecimal value) {
            addCriterion("clearing <", value, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingLessThanOrEqualTo(BigDecimal value) {
            addCriterion("clearing <=", value, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingIn(List<BigDecimal> values) {
            addCriterion("clearing in", values, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingNotIn(List<BigDecimal> values) {
            addCriterion("clearing not in", values, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("clearing between", value1, value2, "clearing");
            return (Criteria) this;
        }

        public Criteria andClearingNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("clearing not between", value1, value2, "clearing");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledIsNull() {
            addCriterion("already_settled is null");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledIsNotNull() {
            addCriterion("already_settled is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledEqualTo(BigDecimal value) {
            addCriterion("already_settled =", value, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledNotEqualTo(BigDecimal value) {
            addCriterion("already_settled <>", value, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledGreaterThan(BigDecimal value) {
            addCriterion("already_settled >", value, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("already_settled >=", value, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledLessThan(BigDecimal value) {
            addCriterion("already_settled <", value, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledLessThanOrEqualTo(BigDecimal value) {
            addCriterion("already_settled <=", value, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledIn(List<BigDecimal> values) {
            addCriterion("already_settled in", values, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledNotIn(List<BigDecimal> values) {
            addCriterion("already_settled not in", values, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_settled between", value1, value2, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andAlreadySettledNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_settled not between", value1, value2, "alreadySettled");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(Integer value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(Integer value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(Integer value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(Integer value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(Integer value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<Integer> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<Integer> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(Integer value1, Integer value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(Integer value1, Integer value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNull() {
            addCriterion("school_name is null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNotNull() {
            addCriterion("school_name is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameEqualTo(String value) {
            addCriterion("school_name =", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotEqualTo(String value) {
            addCriterion("school_name <>", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThan(String value) {
            addCriterion("school_name >", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("school_name >=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThan(String value) {
            addCriterion("school_name <", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("school_name <=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLike(String value) {
            addCriterion("school_name like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotLike(String value) {
            addCriterion("school_name not like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIn(List<String> values) {
            addCriterion("school_name in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotIn(List<String> values) {
            addCriterion("school_name not in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameBetween(String value1, String value2) {
            addCriterion("school_name between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotBetween(String value1, String value2) {
            addCriterion("school_name not between", value1, value2, "schoolName");
            return (Criteria) this;
        }
    }

    /**
     */
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