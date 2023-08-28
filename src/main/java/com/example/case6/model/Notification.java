package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String name;
    @Lob
    private String context;
    private Date date = Date.valueOf(LocalDate.now());
    @ManyToOne
    private Status status; // trạng thái đọc hoặc chưa
    @ManyToOne
    private Account user; // người gửi
    @ManyToOne
    private Shop shop; // người nhận
}
