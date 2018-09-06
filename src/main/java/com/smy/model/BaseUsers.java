package com.smy.model;
/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/4
 * @Version: 1.0
 */

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *@auther:Administrator1
 *@Date:2018/9/4
 *@descriotion:
 */
@Entity
@Table(name = "base_users", schema = "socialmodel", catalog = "")
public class BaseUsers implements Serializable {
    private long id;
    private String chanAdd;
    private Timestamp createTime;
    private String memo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "chan_add")
    public String getChanAdd() {
        return chanAdd;
    }

    public void setChanAdd(String chanAdd) {
        this.chanAdd = chanAdd;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseUsers baseUsers = (BaseUsers) o;

        if (id != baseUsers.id) return false;
        if (chanAdd != null ? !chanAdd.equals(baseUsers.chanAdd) : baseUsers.chanAdd != null) return false;
        if (createTime != null ? !createTime.equals(baseUsers.createTime) : baseUsers.createTime != null) return false;
        if (memo != null ? !memo.equals(baseUsers.memo) : baseUsers.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (chanAdd != null ? chanAdd.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
