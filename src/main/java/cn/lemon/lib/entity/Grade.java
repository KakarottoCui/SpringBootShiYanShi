package cn.lemon.lib.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}
