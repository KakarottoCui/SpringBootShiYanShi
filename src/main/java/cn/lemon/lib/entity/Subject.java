package cn.lemon.lib.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 课程表
 * */
@Table
@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long gradeId;

    private long teacherId;

    private long startWeek;

    private long endWeek;

    private long day;

    private long part;

    /**
     * 班级名
     * */
    @Transient
    private String gradeName;

    /**
     * 教师名
     * */
    @Transient
    private String teacherName;

}
