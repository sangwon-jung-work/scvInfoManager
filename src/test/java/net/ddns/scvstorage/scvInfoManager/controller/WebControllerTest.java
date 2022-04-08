package net.ddns.scvstorage.scvInfoManager.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.ddns.scvstorage.scvInfoManager.ScvInfoManagerApplication;

@SpringBootTest(classes = ScvInfoManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class WebControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     *  root 조회(index) 테스트
     */
    @Test
    @Order(1)
    public void getPageLoad_index_getStatusCodeOK() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/")
                .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }

    /**
     *  구입이력 입력화면 조회 테스트
     */
    @Test
    @Order(2)
    public void getPageLoad_insertContent_getStatusCodeOK() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/web/buy/contentinsert")
                .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }

    /**
     *  구입이력(디지털) 입력화면 조회 테스트
     */
    @Test
    @Order(3)
    public void getPageLoad_insertDContent_getStatusCodeOK() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/web/buy/dcontentinsert")
                .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }
    
    /**
     *  배송정보 입력화면 조회 테스트
     */
    @Test
    @Order(4)
    public void getPageLoad_insertShipping_getStatusCodeOK() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/web/buy/shippinginsert")
                .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }
    
    /**
     *  공통코드 입력화면 조회 테스트
     */
    @Test
    @Order(5)
    public void getPageLoad_insertCommonCode_getStatusCodeOK() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/web/memo/comcdinsert")
                .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }
    
    /**
     *  방위치 입력화면 조회 테스트
     */
    @Test
    @Order(6)
    public void getPageLoad_insertLocationCode_getStatusCodeOK() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/web/memo/locationinsert")
                .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }
    
    /**
     *  메모기록 입력화면 조회 테스트
     */
    @Test
    @Order(7)
    public void getPageLoad_insertMemotime_getStatusCodeOK() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/web/memo/memotimeinsert")
                .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());
    }
}