package cn.lemon.lib.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private long phone;

    private String sex;

    private long age;

    private long status;
}
