package de401t.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "request", schema = "hakaton")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column
    private String solution;
    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @OneToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;
    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @OneToOne
    @JoinColumn(name = "sub_type_id")
    private SubType subType;
    @OneToOne
    @JoinColumn(name = "client_id")
    private User client;
    @OneToOne
    @JoinColumn(name = "assistant_id")
    private User assistant;
    @OneToOne
    @JoinColumn(name = "executor_id")
    private User executor;
    @Column
    private Date createDate;
    @Column
    private Date deadlineDate;
    @Column
    private Date updateDate;
    @Column
    private String comment;
}