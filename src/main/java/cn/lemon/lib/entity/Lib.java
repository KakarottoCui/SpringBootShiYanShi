package cn.lemon.lib.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Data
public class Lib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String number;

    private String place;

    private String manager;

    private long quantity;

    private String remark;

    private long status;

    private Timestamp createDate;

    private Timestamp updateDate;


}
