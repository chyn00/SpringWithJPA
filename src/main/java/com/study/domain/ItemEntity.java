package com.study.domain;


import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class ItemEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    int item_no;


    @Column(name = "item_name", length = 50)
    private String item_name;

    @Column(name = "item_price")
    private int item_price;

    @Column(name = "item_count")
    private int item_count;

}
