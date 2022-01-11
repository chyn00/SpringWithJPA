package com.study.controller;


import com.study.service.ItemForCService;
import com.study.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    ItemForCService itemForCService;
    @Autowired
    JwtService jwtService;

    @GetMapping("/allItemInfo")
    public ResponseEntity<Map<String, Object>> getAllItemInfo() {


        Map<String,Object> resultMap= new HashMap<>();

        try {
            resultMap.put("itemList", itemForCService.getItemAllInfo());
            resultMap.put("message","SUCCESS");
        } catch (Exception e) {
            resultMap.put("message","실패");
            e.printStackTrace();
        }


        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }

    @PutMapping("/item")
    public ResponseEntity<Map<String, Object>> BuyItem(
            @RequestParam(value = "jwt", required = false) String jwt,
            @RequestParam(value = "item_no", required = false) int item_no) throws UnsupportedEncodingException {

        String id = jwtService.getIdByJWT(jwt);

        Map<String,Object> resultMap= new HashMap<>();

        try {
            resultMap.put("itemDetail", itemForCService.BuyItem(id, item_no));
            resultMap.put("message","SUCCESS");
        } catch (Exception e) {
            resultMap.put("message","실패");
            e.printStackTrace();
        }


        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }


    @GetMapping("/oneItemInfo")
    public ResponseEntity<Map<String, Object>> getDetailItemInfo(@RequestParam(value = "item_no", required = false) int item_no) {


        Map<String,Object> resultMap= new HashMap<>();

        try {
            resultMap.put("itemDetailInfo", itemForCService.getItemDetailInfo(item_no));
            resultMap.put("message","SUCCESS");
        } catch (Exception e) {
            resultMap.put("message","실패");
            e.printStackTrace();
        }


        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }



}
