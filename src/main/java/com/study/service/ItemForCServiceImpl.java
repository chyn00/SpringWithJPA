package com.study.service;


import com.study.dao.TradeDao;
import com.study.dao.UserInfoDao;
import com.study.domain.UserEntity;
import com.study.domain.UserInfoEntity;
import com.study.dto.ItemDto;
import com.study.domain.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class ItemForCServiceImpl implements ItemForCService {

    @Autowired
    TradeDao tradeDao;

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    AuthService authService;


    @Override
    public ItemDto BuyItem(String id, int item_no) throws Exception {

        UserEntity userEntity = userInfoDao.findById(id);
        ItemEntity itemEntity = tradeDao.findById(item_no).orElse(null);

        int AfterBuyCount = itemEntity.getItem_count()-1;
        int AfterBuyPoint = itemEntity.getItem_price()*5/100;
        int calPoint = userEntity.getPoint() + AfterBuyPoint;


        if(calPoint >=1){//첫구매시
                authService.updateGrade_AfterCheck(id, "silver");
        }else if(calPoint >=500){//500점 이상시
                authService.updateGrade_AfterCheck(id, "gold");
        }

        itemEntity.setItem_count(AfterBuyCount); //아이템수 로직
        userEntity.setPoint(calPoint); //포인트 올라가는 로직

        userInfoDao.save(userEntity);
        tradeDao.saveAndFlush(itemEntity);

        return this.getItemDetailInfo(item_no);
    }

    @Override
    public List<ItemDto> getItemAllInfo() throws Exception {

        //entity로 변환해줬다가 다시가져와서
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<ItemDto>>(){}.getType();
        List<ItemDto> itemDtoList = modelMapper.map(tradeDao.findAll(),listType);

        return itemDtoList;
    }

    @Override
    public ItemDto getItemDetailInfo(int item_no) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        ItemDto itemDto = modelMapper.map(tradeDao.findById(item_no).orElse(null),ItemDto.class);
        return itemDto;
    }
}
