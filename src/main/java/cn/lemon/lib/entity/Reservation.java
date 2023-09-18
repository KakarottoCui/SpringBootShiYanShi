package cn.lemon.lib.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.*;

/**
 * reservation
 * @author 
 */
@Table
@Entity
@Data
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private long type;

    private long libId;

    private long gradeId;

    private long startWeek;

    private long endWeek;

    private long day;

    private long part;

    private long status;

    /**
     * 通知内容
     */
    private String remark;

    private Date createTime;

    private Date updateTime;

    @Transient
    private String libName;

    @Transient
    private String reservationName;

    @Transient
    private String typeName;

    @Transient
    private String isAudit;

    private static final long serialVersionUID = 1L;

}