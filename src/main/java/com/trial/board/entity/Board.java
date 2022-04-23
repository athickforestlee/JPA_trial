package com.trial.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //entity = table
@Data //BoardController.java의 객체에서 getId(), getTitle(), getContent()를 받아올 수 있음.
public class Board { //@Entity를 통해 JPA가 Board테이블에 있는 설정임을 알고 읽어들인다
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MariaDB는 IDENTITY, ORACLE은 SEQUENCE
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;
}
