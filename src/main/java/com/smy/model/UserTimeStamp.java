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
 *@auther:Administrator
 *@Date:2018/9/4
 *@descriotion:
 */
@Entity
@Table(name = "user_time_stamp", schema = "socialmodel", catalog = "")
public class UserTimeStamp implements Serializable {
    private long id;
    private Long userId;
    private Long udmsgId;
    private Integer isMine;
    private Timestamp createTime;

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
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "udmsg_id")
    public Long getUdmsgId() {
        return udmsgId;
    }

    public void setUdmsgId(Long udmsgId) {
        this.udmsgId = udmsgId;
    }

    @Basic
    @Column(name = "is_mine")
    public Integer getIsMine() {
        return isMine;
    }

    public void setIsMine(Integer isMine) {
        this.isMine = isMine;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTimeStamp that = (UserTimeStamp) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (udmsgId != null ? !udmsgId.equals(that.udmsgId) : that.udmsgId != null) return false;
        if (isMine != null ? !isMine.equals(that.isMine) : that.isMine != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (udmsgId != null ? udmsgId.hashCode() : 0);
        result = 31 * result + (isMine != null ? isMine.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
