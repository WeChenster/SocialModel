package com.smy.model;
/**
 * @Description: java类作用描述
 * @Author: lwt
 * @CreateDate: 2018/9/17
 * @Version: 1.0
 */

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *@auther:Administrator
 *@Date:2018/9/17
 *@descriotion:
 */
@Entity
@Table(name = "user_agree", schema = "socialmodel", catalog = "")
public class UserAgree implements Serializable {
    private long id;
    private Long userId;
    private Long srcId;
    private String reason;
    private Timestamp createTime;
    private String friendFrom;
    private Integer type;
    private Integer idDel;
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
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "src_id")
    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
    @Column(name = "friend_from")
    public String getFriendFrom() {
        return friendFrom;
    }

    public void setFriendFrom(String friendFrom) {
        this.friendFrom = friendFrom;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "id_del")
    public Integer getIdDel() {
        return idDel;
    }

    public void setIdDel(Integer idDel) {
        this.idDel = idDel;
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

        UserAgree userAgree = (UserAgree) o;

        if (id != userAgree.id) return false;
        if (userId != null ? !userId.equals(userAgree.userId) : userAgree.userId != null) return false;
        if (srcId != null ? !srcId.equals(userAgree.srcId) : userAgree.srcId != null) return false;
        if (reason != null ? !reason.equals(userAgree.reason) : userAgree.reason != null) return false;
        if (createTime != null ? !createTime.equals(userAgree.createTime) : userAgree.createTime != null) return false;
        if (friendFrom != null ? !friendFrom.equals(userAgree.friendFrom) : userAgree.friendFrom != null) return false;
        if (type != null ? !type.equals(userAgree.type) : userAgree.type != null) return false;
        if (idDel != null ? !idDel.equals(userAgree.idDel) : userAgree.idDel != null) return false;
        if (memo != null ? !memo.equals(userAgree.memo) : userAgree.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (srcId != null ? srcId.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (friendFrom != null ? friendFrom.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idDel != null ? idDel.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
