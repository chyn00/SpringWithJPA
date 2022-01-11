package com.study.service;

import com.study.dao.TradeDao;
import com.study.dto.ItemDto;
import com.study.domain.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
@Service
public class ItemForAServiceImpl implements ItemForAService {

    @Autowired
    TradeDao tradeDao;


    @Override
    public void addItem(ItemDto itemDto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        ItemEntity itemEntity = modelMapper.map(itemDto,ItemEntity.class);

        tradeDao.saveAndFlush(itemEntity);
    }

    @Override
    public List<ItemDto> getItemAllInfo() throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<ItemDto>>(){}.getType();
        List<ItemDto> itemDtoList = modelMapper.map(tradeDao.findAll(),listType);

        return itemDtoList;
    }

    @Override
    public void deleteItem(int item_no) throws Exception {
        tradeDao.deleteById(item_no);
    }
}
