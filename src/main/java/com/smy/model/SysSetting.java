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
@Table(name = "sys_setting", schema = "socialmodel", catalog = "")
public class SysSetting implements Serializable {
    private long id;
    private String userUrl;
    private String tradeUrl;
    private String lifeUrl;
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
    @Column(name = "user_url")
    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    @Basic
    @Column(name = "trade_url")
    public String getTradeUrl() {
        return tradeUrl;
    }

    public void setTradeUrl(String tradeUrl) {
        this.tradeUrl = tradeUrl;
    }

    @Basic
    @Column(name = "life_url")
    public String getLifeUrl() {
        return lifeUrl;
    }

    public void setLifeUrl(String lifeUrl) {
        this.lifeUrl = lifeUrl;
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

        SysSetting that = (SysSetting) o;

        if (id != that.id) return false;
        if (userUrl != null ? !userUrl.equals(that.userUrl) : that.userUrl != null) return false;
        if (tradeUrl != null ? !tradeUrl.equals(that.tradeUrl) : that.tradeUrl != null) return false;
        if (lifeUrl != null ? !lifeUrl.equals(that.lifeUrl) : that.lifeUrl != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userUrl != null ? userUrl.hashCode() : 0);
        result = 31 * result + (tradeUrl != null ? tradeUrl.hashCode() : 0);
        result = 31 * result + (lifeUrl != null ? lifeUrl.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
