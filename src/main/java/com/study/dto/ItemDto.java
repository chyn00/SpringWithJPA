package com.study.dto;


import lombok.*;


@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {


    int item_no;

    private String item_name;

    private int item_price;

    private int item_count;



}
