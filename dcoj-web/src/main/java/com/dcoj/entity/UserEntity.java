package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * @author Leon
 */

 public class UserEntity {
    @Id
    private String uid;

    @JSONField(name = "student_id")
    @Field("student_id")
    private String studentId;

    private String email;

    private String nickname;

    private Integer avatar;

    private String password;

    private Set<String> roles;

    @JSONField(name = "submit_times")
    @Field("submit_time")
    private Integer submitTimes;

    @JSONField(name = "contest_times")
    @Field("contest_times")
    private Integer contestTimes;

    @JSONField(name = "ac_times")
    @Field("ac_times")
    private Integer ACTimes;

    @JSONField(name = "wa_times")
    @Field("wa_times")
    private Integer WATimes;

    @JSONField(name = "rte_times")
    @Field("rte_times")
    private Integer RTETimes;

    @JSONField(name = "tle_times")
    @Field("tle_times")
    private Integer TLETimes;

    @JSONField(name = "ce_times")
    @Field("ce_times")
    private Integer CETimes;

    @JSONField(name = "finished_problems")
    @Field("finished_problems")
    private Integer finishedProblems;

    private Integer gender;

    private String motto;

    @JSONField(name = "register_time")
    // system currentTimeMillis
    private Long registerTime;

    //0:unverified; 1:verified
    private Integer verified;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


    public Integer getSubmitTimes() {
        return submitTimes;
    }

    public void setSubmitTimes(Integer submitTimes) {
        this.submitTimes = submitTimes;
    }

    public Integer getContestTimes() {
        return contestTimes;
    }

    public void setContestTimes(Integer contestTimes) {
        this.contestTimes = contestTimes;
    }

    public Integer getACTimes() {
        return ACTimes;
    }

    public void setACTimes(Integer ACTimes) {
        this.ACTimes = ACTimes;
    }

    public Integer getWATimes() {
        return WATimes;
    }

    public void setWATimes(Integer WATimes) {
        this.WATimes = WATimes;
    }

    public Integer getRTETimes() {
        return RTETimes;
    }

    public void setRTETimes(Integer RTETimes) {
        this.RTETimes = RTETimes;
    }

    public Integer getTLETimes() {
        return TLETimes;
    }

    public void setTLETimes(Integer TLETimes) {
        this.TLETimes = TLETimes;
    }

    public Integer getCETimes() {
        return CETimes;
    }

    public void setCETimes(Integer CETimes) {
        this.CETimes = CETimes;
    }

    public Integer getFinishedProblems() {
        return finishedProblems;
    }

    public void setFinishedProblems(Integer finishedProblems) {
        this.finishedProblems = finishedProblems;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }
}
