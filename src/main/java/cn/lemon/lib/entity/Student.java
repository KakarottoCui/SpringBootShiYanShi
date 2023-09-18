package cn.lemon.lib.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private long gradeId;

    private String sex;

    private long age;

    private long status;

    @Transient
    private String gradeName;

}
