package com.nuesoft.lwn.common.entity.user;



public class Priv {
    private Integer rolePrivId;

    private String privName;

    private String roleName;

    private Integer parentId;

    private String updateBy;

    private String updateTime;

    public Integer getRolePrivId() {
        return rolePrivId;
    }

    public void setRolePrivId(Integer rolePrivId) {
        this.rolePrivId = rolePrivId;
    }

    public String getPrivName() {
        return privName;
    }

    public void setPrivName(String privName) {
        this.privName = privName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}