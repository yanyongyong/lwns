package com.nuesoft.lwn.common.entity.member;

import java.util.List;

public class Member {
    private Integer memberId;

    private String memberName;

    private String memberPhone;

    private String memberAddress;

    private String createdStore;

    private Integer memberIntegral;

    private Double memberBalance;

    private String remark;

    private Grade grade;

    private Integer recharge;

    public Integer getRecharge() {
        return recharge;
    }

    public void setRecharge(Integer recharge) {
        this.recharge = recharge;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getCreatedStore() {
        return createdStore;
    }

    public void setCreatedStore(String createdStore) {
        this.createdStore = createdStore;
    }

    public Integer getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(Integer memberIntegral) {
        this.memberIntegral = memberIntegral;
    }

    public Double getMemberBalance() {
        return memberBalance;
    }

    public void setMemberBalance(Double memberBalance) {
        this.memberBalance = memberBalance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}