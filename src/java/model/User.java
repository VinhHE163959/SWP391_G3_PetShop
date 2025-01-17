/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author duongvu
 */
public class User {
    /*
    id VARCHAR(10) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    gender BOOLEAN,
    status VARCHAR(50),
    role_id INT,
    phone VARCHAR(20),
    last_log DATETIME,
    pfp TEXT,
    */
    String email, password, name, status, phone, pfp;
    int roleId, id, update_by;
    boolean gender;
    Date lastLog,updated_datey;

    public User() {
    }

    public User(int id, String email, String password, String name, String status, String phone, String pfp, int update_by, int roleId, boolean gender, Date lastLog, Date updated_datey) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.pfp = pfp;
        this.update_by = update_by;
        this.roleId = roleId;
        this.gender = gender;
        this.lastLog = lastLog;
        this.updated_datey = updated_datey;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
    }

    public Date getUpdated_datey() {
        return updated_datey;
    }

    public void setUpdated_datey(Date updated_datey) {
        this.updated_datey = updated_datey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPfp() {
        return pfp;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getLastLog() {
        return lastLog;
    }

    public void setLastLog(Date lastLog) {
        this.lastLog = lastLog;
    }
    
}
