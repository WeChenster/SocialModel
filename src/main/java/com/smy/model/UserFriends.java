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
@Table(name = "user_friends", schema = "socialmodel", catalog = "")
public class UserFriends implements Serializable {
    private long id;
    private Long userId;
    private Long recUser;
    private Timestamp createTime;
    private String friendMemo;
    private Integer top;
    private Integer msgNot;
    private String backg;
    private Integer hideMe;
    private Integer hideHer;
    private Integer isBlacklist;
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
    @Column(name = "rec_user")
    public Long getRecUser() {
        return recUser;
    }

    public void setRecUser(Long recUser) {
        this.recUser = recUser;
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
    @Column(name = "friend_memo")
    public String getFriendMemo() {
        return friendMemo;
    }

    public void setFriendMemo(String friendMemo) {
        this.friendMemo = friendMemo;
    }

    @Basic
    @Column(name = "top")
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @Basic
    @Column(name = "msg_not")
    public Integer getMsgNot() {
        return msgNot;
    }

    public void setMsgNot(Integer msgNot) {
        this.msgNot = msgNot;
    }

    @Basic
    @Column(name = "backg")
    public String getBackg() {
        return backg;
    }

    public void setBackg(String backg) {
        this.backg = backg;
    }

    @Basic
    @Column(name = "hide_me")
    public Integer getHideMe() {
        return hideMe;
    }

    public void setHideMe(Integer hideMe) {
        this.hideMe = hideMe;
    }

    @Basic
    @Column(name = "hide_her")
    public Integer getHideHer() {
        return hideHer;
    }

    public void setHideHer(Integer hideHer) {
        this.hideHer = hideHer;
    }

    @Basic
    @Column(name = "is_blacklist")
    public Integer getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Integer isBlacklist) {
        this.isBlacklist = isBlacklist;
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

        UserFriends that = (UserFriends) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (recUser != null ? !recUser.equals(that.recUser) : that.recUser != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (friendMemo != null ? !friendMemo.equals(that.friendMemo) : that.friendMemo != null) return false;
        if (top != null ? !top.equals(that.top) : that.top != null) return false;
        if (msgNot != null ? !msgNot.equals(that.msgNot) : that.msgNot != null) return false;
        if (backg != null ? !backg.equals(that.backg) : that.backg != null) return false;
        if (hideMe != null ? !hideMe.equals(that.hideMe) : that.hideMe != null) return false;
        if (hideHer != null ? !hideHer.equals(that.hideHer) : that.hideHer != null) return false;
        if (isBlacklist != null ? !isBlacklist.equals(that.isBlacklist) : that.isBlacklist != null) return false;
        if (idDel != null ? !idDel.equals(that.idDel) : that.idDel != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (recUser != null ? recUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (friendMemo != null ? friendMemo.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (msgNot != null ? msgNot.hashCode() : 0);
        result = 31 * result + (backg != null ? backg.hashCode() : 0);
        result = 31 * result + (hideMe != null ? hideMe.hashCode() : 0);
        result = 31 * result + (hideHer != null ? hideHer.hashCode() : 0);
        result = 31 * result + (isBlacklist != null ? isBlacklist.hashCode() : 0);
        result = 31 * result + (idDel != null ? idDel.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
