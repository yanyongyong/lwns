package com.nuesoft.lwn.common.entity.member;

public class Grade {
    private Integer gradeId;

    private String gradeName;

    private Integer integralNadir;

    private Integer integralApex;

    private Double integralDiscount;

    private String remark;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public Integer getIntegralNadir() {
        return integralNadir;
    }

    public void setIntegralNadir(Integer integralNadir) {
        this.integralNadir = integralNadir;
    }

    public Integer getIntegralApex() {
        return integralApex;
    }

    public void setIntegralApex(Integer integralApex) {
        this.integralApex = integralApex;
    }

    public Double getIntegralDiscount() {
        return integralDiscount;
    }

    public void setIntegralDiscount(Double integralDiscount) {
        this.integralDiscount = integralDiscount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}