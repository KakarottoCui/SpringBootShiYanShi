package cn.lemon.lib.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Admin {

    private String sex;

    private long status;

}