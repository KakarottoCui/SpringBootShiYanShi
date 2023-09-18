package cn.lemon.lib.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String url;

    private int parentId;

    private int sort;

    @Transient
    private List<Menu> child;

}
