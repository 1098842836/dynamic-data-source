package com.yl.spring.simpleBeanContainer;

public class UserInfo {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名字
     */
    private String userName;

    private String orgId;

    /**
     * 登录名
     */
    private String staffCode;

    /**
     * 角色id
     */
    private String roleCode;

    private String areaId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(final String orgId) {
        this.orgId = orgId;
    }

    public String getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(final String staffCode) {
        this.staffCode = staffCode;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(final String roleCode) {
        this.roleCode = roleCode;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(final String areaId) {
        this.areaId = areaId;
    }
}
