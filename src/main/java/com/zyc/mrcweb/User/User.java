package com.zyc.mrcweb.User;

import com.zyc.mrcweb.MRC.ResultSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int usageTimes;
    @Column(nullable = false)
    private int allowUsageTimes;
    @Column(nullable = false)
    private String regTime;
    @Column(nullable = false)
    private boolean member;

    public User(){
        super();
    }
    public User(String username, String password){
        Calendar calendar=Calendar.getInstance();
        this.usageTimes=0;
        this.allowUsageTimes=0;
        this.username=username;
        this.password=password;
        this.regTime=calendar.getTime().toString();
        this.member=false;
    }

    public int getUsageTimes() {
        return usageTimes;
    }
    public int getAllowUsageTimes() {
        return allowUsageTimes;
    }
    public Long getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getRegTime() {
        return regTime;
    }
    public String getUsername() {
        return username;
    }
    public boolean getMember() {
        return member;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }
    public void setUsageTimes(int usageTimes) {
        this.usageTimes = usageTimes;
    }
    public void setAllowUsageTimes(int allowUsageTimes) {
        this.allowUsageTimes = allowUsageTimes;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMember(boolean member) {
        this.member = member;
    }
}
