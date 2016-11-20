package com.nuesoft.lwn.common.entity.goods;

public class GoodsSp {
    private Integer spId;

    private String spName;

    private String spGoods;

    private String spCategory;

    private String spStore;

    private Double spPrice;

    private String spTime;

    private String remark;

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName == null ? null : spName.trim();
    }

    public String getSpGoods() {
        return spGoods;
    }

    public void setSpGoods(String spGoods) {
        this.spGoods = spGoods == null ? null : spGoods.trim();
    }

    public String getSpCategory() {
        return spCategory;
    }

    public void setSpCategory(String spCategory) {
        this.spCategory = spCategory == null ? null : spCategory.trim();
    }

    public String getSpStore() {
        return spStore;
    }

    public void setSpStore(String spStore) {
        this.spStore = spStore == null ? null : spStore.trim();
    }

    public Double getSpPrice() {
        return spPrice;
    }

    public void setSpPrice(Double spPrice) {
        this.spPrice = spPrice;
    }

    public String getSpTime() {
        return spTime;
    }

    public void setSpTime(String spTime) {
        this.spTime = spTime == null ? null : spTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}