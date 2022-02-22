package com.study.controller;

import com.study.configure.mockConfig;
import com.study.dao.TradeDao;
import com.study.service.ItemForAService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@mockConfig
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.*"})
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {
    static String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VySW5mbyIsIm5pY2tOYW1lIjoi7Iug7Lap7ZiEIiwiaWQiOiJ5YmozQG5hdmVyLmNvbSIsImV4cCI6MTY0NTUzMjIyOSwiZW1haWwiOiJ5YmozQG5hdmVyLmNvbSJ9.qXl-m18fQx6aRshJ1AmksrTy394k05VXA6VsgtTA35g";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_addItem() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("jwt", jwt);
        info.add("item_name", "감자탕");
        info.add("item_price", "990");
        info.add("item_count", "100");

        MvcResult result =  mockMvc.perform(post("/api/item")
                .params(info))
                .andReturn();
        System.out.println("addItem test 결과 : ");
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void test_buyItem() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("jwt", jwt);
        info.add("item_no", "1");
        MvcResult result =  mockMvc.perform(put("/api/item")
                .params(info))
                .andReturn();
        System.out.println("buyItem Test 결과 : ");
        System.out.println(result.getResponse().getContentAsString());

    }


    @Test
    public void test_getAllItemInfo() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("jwt", jwt);
        MvcResult result =  mockMvc.perform(get("/api/allItemInfo")
                .params(info))
                .andReturn();
        System.out.println("getAll Test 결과 : ");
        System.out.println(result.getResponse().getContentAsString());

    }



}