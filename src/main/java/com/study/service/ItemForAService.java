package com.study.service;

import com.study.dto.ItemDto;

import java.util.List;

public interface ItemForAService {

    void addItem(ItemDto itemDto) throws Exception;
    List<ItemDto> getItemAllInfo() throws Exception;
    void deleteItem(int item_no) throws Exception;
}
