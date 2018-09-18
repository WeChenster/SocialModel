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
@Table(name = "user_comments", schema = "socialmodel", catalog = "")
public class UserComments implements Serializable {
    private long id;
    private Long img;
    private Long userId;
    private Integer type;
    private Long recUser;
    private String content;
    private Timestamp createTime;
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
    @Column(name = "img")
    public Long getImg() {
        return img;
    }

    public void setImg(Long img) {
        this.img = img;
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
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

        UserComments that = (UserComments) o;

        if (id != that.id) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (recUser != null ? !recUser.equals(that.recUser) : that.recUser != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (idDel != null ? !idDel.equals(that.idDel) : that.idDel != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (recUser != null ? recUser.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (idDel != null ? idDel.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
