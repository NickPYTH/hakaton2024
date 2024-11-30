package de401t.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "equipment", schema = "public")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column
    private String name;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String note;
}