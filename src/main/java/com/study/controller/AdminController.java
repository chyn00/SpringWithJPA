package com.study.controller;



import com.study.dto.ItemDto;
import com.study.service.ItemForAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    ItemForAService itemForAService;

    @PostMapping("/item")
    public ResponseEntity<Map<String, Object>> addItem(ItemDto itemDto) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String,Object> resultMap= new HashMap<>();

        try {
            itemForAService.addItem(itemDto);
            resultMap.put("message","SUCCESS");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("message","저장안됨");
            e.printStackTrace();
            status = HttpStatus.BAD_REQUEST;
        }


        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @DeleteMapping("/item")
    public ResponseEntity<Map<String, Object>> deleteItem(@RequestParam(value = "item_no", required = false) int item_no) {


        Map<String,Object> resultMap= new HashMap<>();

        try {
            itemForAService.deleteItem(item_no);
            resultMap.put("message","SUCCESS");
        } catch (Exception e) {
            resultMap.put("message","안지워짐");
            e.printStackTrace();
        }


        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }





}
