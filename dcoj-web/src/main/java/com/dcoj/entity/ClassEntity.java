package com.dcoj.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ClassEntity implements Serializable {
    private Integer classId;

    private String className;

    private String major;

    private Integer classNumber;

    private String schoolYear;

    private String secondaryCollege;

    private String schoolName;

    private String describes;

    private Timestamp modifiedTime;

    private Date createTime;

    private Integer isOrder;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear == null ? null : schoolYear.trim();
    }

    public String getSecondaryCollege() {
        return secondaryCollege;
    }

    public void setSecondaryCollege(String secondaryCollege) {
        this.secondaryCollege = secondaryCollege == null ? null : secondaryCollege.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(Integer isOrder) {
        this.isOrder = isOrder;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ClassEntity other = (ClassEntity) that;
        return (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getClassName() == null ? other.getClassName() == null : this.getClassName().equals(other.getClassName()))
            && (this.getMajor() == null ? other.getMajor() == null : this.getMajor().equals(other.getMajor()))
            && (this.getClassNumber() == null ? other.getClassNumber() == null : this.getClassNumber().equals(other.getClassNumber()))
            && (this.getSchoolYear() == null ? other.getSchoolYear() == null : this.getSchoolYear().equals(other.getSchoolYear()))
            && (this.getSecondaryCollege() == null ? other.getSecondaryCollege() == null : this.getSecondaryCollege().equals(other.getSecondaryCollege()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
            && (this.getDescribes() == null ? other.getDescribes() == null : this.getDescribes().equals(other.getDescribes()))
            && (this.getModifiedTime() == null ? other.getModifiedTime() == null : this.getModifiedTime().equals(other.getModifiedTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsOrder() == null ? other.getIsOrder() == null : this.getIsOrder().equals(other.getIsOrder()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());
        result = prime * result + ((getMajor() == null) ? 0 : getMajor().hashCode());
        result = prime * result + ((getClassNumber() == null) ? 0 : getClassNumber().hashCode());
        result = prime * result + ((getSchoolYear() == null) ? 0 : getSchoolYear().hashCode());
        result = prime * result + ((getSecondaryCollege() == null) ? 0 : getSecondaryCollege().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getDescribes() == null) ? 0 : getDescribes().hashCode());
        result = prime * result + ((getModifiedTime() == null) ? 0 : getModifiedTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsOrder() == null) ? 0 : getIsOrder().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classId=").append(classId);
        sb.append(", className=").append(className);
        sb.append(", major=").append(major);
        sb.append(", classNumber=").append(classNumber);
        sb.append(", schoolYear=").append(schoolYear);
        sb.append(", secondaryCollege=").append(secondaryCollege);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", describes=").append(describes);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", isOrder=").append(isOrder);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}