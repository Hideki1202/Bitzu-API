package com.bitzu.demo.models;

import jakarta.persistence.*;

@Table
@Entity(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    public Long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }
}
