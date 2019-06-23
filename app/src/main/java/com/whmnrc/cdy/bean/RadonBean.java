package com.whmnrc.cdy.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RadonBean implements Serializable {

    private double radonValue;

    private String address;

    private String desc;

    private Date createTime;

    @Id(autoincrement = true)
    private long id;

    private String name;

    @Transient
    private boolean isSelect;

    @Generated(hash = 606333715)
    public RadonBean(double radonValue, String address, String desc,
            Date createTime, long id, String name) {
        this.radonValue = radonValue;
        this.address = address;
        this.desc = desc;
        this.createTime = createTime;
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1635888606)
    public RadonBean() {
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public double getRadonValue() {
        return radonValue;
    }

    public void setRadonValue(double radonValue) {
        this.radonValue = radonValue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
