package me.zhengjie.orcl.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SVR_YC_SAMPLE_DEFINE")
public class Lineload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "YC_ID")
    private char YC_ID;

    @Column(name = "FAC_ID")
    private String FAC_ID;

    @Column(name = "HISTORY_TABLE_NAME")
    private String HISTORY_TABLE_NAME;

    @Column(name = "HISTORY_COLUMN_NAME")
    private String HISTORY_COLUMN_NAME;

    @Column(name = "SAMPLE_INTERVAL")
    private String SAMPLE_INTERVAL;

    @Column(name = "IF_VARPERIOD")
    private String IF_VARPERIOD;

    @Column(name = "LAST_CHANGE_TIME")
    private String LAST_CHANGE_TIME;

    @Column(name = "IS_PAUSE")
    private String IS_PAUSE;

    @Column(name = "FIRST_DEFINE_TIME")
    private String FIRST_DEFINE_TIME;
}
