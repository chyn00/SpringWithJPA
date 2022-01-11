package com.study.dao;

import com.study.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeDao extends JpaRepository<ItemEntity, Integer> {
}
