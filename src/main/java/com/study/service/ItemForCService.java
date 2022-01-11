package com.study.service;


import com.study.dto.ItemDto;

import java.util.List;

public interface ItemForCService {


    //update 역할을 하게될것임
    ItemDto BuyItem(String id, int item_no) throws Exception;
    List<ItemDto> getItemAllInfo() throws Exception;
    ItemDto getItemDetailInfo(int item_no) throws Exception;

}
