package org.proj.app.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idc;

    @Column(name = "content")
    private String content;

    @Column(name = "date_created")
    private Date date_created;
/*
    @ManyToOne
    @JoinColumn(name = "id")
    private Image image;
*/
}
