package com.boxing_app.boxing_arena_management_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="arenas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Arena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long arena_id;

    @Column(name="name")
    private String name;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="revenue_split")
    private float revenue_split;

    @Column(name="seats")
    private int seats;

    @Column(name="address")
    private String address;

    @Column(name="open")
    private boolean open;

    @Column(name="safety_compliant")
    private boolean safety_compliant;

}
