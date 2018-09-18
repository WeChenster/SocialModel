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
@Table(name = "user_dyn_msg", schema = "socialmodel", catalog = "")
public class UserDynMsg implements Serializable {
    private long id;
    private Long userId;
    private Long recUser;
    private String content;
    private String img;
    private Integer type;
    private Timestamp createTime;
    private Integer goodNum;
    private Integer commNum;
    private Integer forwardNum;
    private Integer idDel;
    private String memo;
    private Integer isStrange;
    private Integer isForward;
    private String forwardRes;
    private Long msgId;

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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "good_num")
    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

    @Basic
    @Column(name = "comm_num")
    public Integer getCommNum() {
        return commNum;
    }

    public void setCommNum(Integer commNum) {
        this.commNum = commNum;
    }

    @Basic
    @Column(name = "forward_num")
    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
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

    @Basic
    @Column(name = "is_strange")
    public Integer getIsStrange() {
        return isStrange;
    }

    public void setIsStrange(Integer isStrange) {
        this.isStrange = isStrange;
    }

    @Basic
    @Column(name = "is_forward")
    public Integer getIsForward() {
        return isForward;
    }

    public void setIsForward(Integer isForward) {
        this.isForward = isForward;
    }

    @Basic
    @Column(name = "forward_res")
    public String getForwardRes() {
        return forwardRes;
    }

    public void setForwardRes(String forwardRes) {
        this.forwardRes = forwardRes;
    }

    @Basic
    @Column(name = "msg_id")
    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDynMsg that = (UserDynMsg) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (recUser != null ? !recUser.equals(that.recUser) : that.recUser != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (goodNum != null ? !goodNum.equals(that.goodNum) : that.goodNum != null) return false;
        if (commNum != null ? !commNum.equals(that.commNum) : that.commNum != null) return false;
        if (forwardNum != null ? !forwardNum.equals(that.forwardNum) : that.forwardNum != null) return false;
        if (idDel != null ? !idDel.equals(that.idDel) : that.idDel != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (isStrange != null ? !isStrange.equals(that.isStrange) : that.isStrange != null) return false;
        if (isForward != null ? !isForward.equals(that.isForward) : that.isForward != null) return false;
        if (forwardRes != null ? !forwardRes.equals(that.forwardRes) : that.forwardRes != null) return false;
        if (msgId != null ? !msgId.equals(that.msgId) : that.msgId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (recUser != null ? recUser.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (goodNum != null ? goodNum.hashCode() : 0);
        result = 31 * result + (commNum != null ? commNum.hashCode() : 0);
        result = 31 * result + (forwardNum != null ? forwardNum.hashCode() : 0);
        result = 31 * result + (idDel != null ? idDel.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (isStrange != null ? isStrange.hashCode() : 0);
        result = 31 * result + (isForward != null ? isForward.hashCode() : 0);
        result = 31 * result + (forwardRes != null ? forwardRes.hashCode() : 0);
        result = 31 * result + (msgId != null ? msgId.hashCode() : 0);
        return result;
    }
}
