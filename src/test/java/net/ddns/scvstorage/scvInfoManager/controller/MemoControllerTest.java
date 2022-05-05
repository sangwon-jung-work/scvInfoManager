package net.ddns.scvstorage.scvInfoManager.controller;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.ddns.scvstorage.scvInfoManager.ScvInfoManagerApplication;
import net.ddns.scvstorage.scvInfoManager.entity.memo.CommonCdList;
import net.ddns.scvstorage.scvInfoManager.entity.memo.LocationCdList;
import net.ddns.scvstorage.scvInfoManager.entity.memo.MemoTime;

import static org.hamcrest.Matchers.containsString;

@SpringBootTest(classes = ScvInfoManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(OrderAnnotation.class)
public class MemoControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    /**
     * 공통코드관리 조회 테스트
     * @throws Exception
     */
    @Test
    @Order(1)
    @DisplayName("CommonCdList findAll StatusCode is OK")
    public void getCommonCdList_findAll_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/memo/comcd")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "commonCdListId" )));
    }

    /**
     * 공통코드관리 조회(ID) 테스트
     * @throws Exception
     */
    @Test
    @Order(2)
    @DisplayName("CommonCdList findById, StatusCode is OK")
    public void getCommonCdListById_findById_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/memo/comcd".concat("/21") )
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "commonCdListId" )));
    }

    /**
     * 공통코드관리 입력 테스트
     * @throws Exception
     */
    @Test
    @Order(4)
    @DisplayName("CommonCdList create, StatusCode is OK")
    public void postCommonCd_create_getStatusCodeOK() throws Exception {
        
        final CommonCdList testData = new CommonCdList();
        testData.setCdKind("MEMO_CD");
        testData.setCd("010");
        testData.setCdNm("테스트성공");

        mockMvc.perform(
            MockMvcRequestBuilders.post("/memo/comcd")
                .param("cdKind", testData.getCdKind())
                .param("cd", testData.getCd())
                .param("cdNm", testData.getCdNm())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "commonCdListId" )))
                .andExpect(content().string(containsString( testData.getCdNm() )));
    }

    /**
     * 방위치관리 조회 테스트
     * @throws Exception
     */
    @Test
    @Order(6)
    @DisplayName("LocationCdList findAll StatusCode is OK")
    public void getLocationCdList_findAll_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/memo/location")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "locationCdListId" )));
    }

    /**
     * 방위치관리 조회(ID) 테스트
     * @throws Exception
     */
    @Test
    @Order(7)
    @DisplayName("LocationCdList findById, StatusCode is OK")
    public void getLocationCdListById_findById_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/memo/location".concat("/1982") )
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "locationCdListId" )));
    }

    /**
     * 방위치관리 입력 테스트
     * @throws Exception
     */
    @Test
    @Order(8)
    @DisplayName("LocationCdList create, StatusCode is OK")
    public void postLocationCdList_create_getStatusCodeOK() throws Exception {
        
        final LocationCdList testData = new LocationCdList();
        testData.setLocationCd("099");
        testData.setLocationDesc("서울특별시 관악구 관악산길 354 203호");

        mockMvc.perform(
            MockMvcRequestBuilders.post("/memo/location")
                .param("locationCd", testData.getLocationCd())
                .param("locationDesc", testData.getLocationDesc())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "locationCdListId" )))
                .andExpect(content().string(containsString( testData.getLocationDesc() )));
    }

    /**
     * 메모기록 조회 테스트
     * @throws Exception
     */
    @Test
    @Order(11)
    @DisplayName("MemoTime findAll StatusCode is OK")
    public void getMemoTime_findAll_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/memo/memotime")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "memoTimeId" )));
    }

    /**
     * 메모기록 조회(ID) 테스트
     * @throws Exception
     */
    @Test
    @Order(12)
    @DisplayName("MemoTime findById, StatusCode is OK")
    public void getMemoTimeById_findById_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/memo/memotime".concat("/20") )
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "memoTimeId" )));
    }

    /**
     * 메모기록 입력 테스트
     * @throws Exception
     */
    @Test
    @Order(13)
    @DisplayName("MemoTime create, StatusCode is OK")
    public void postMemoTime_create_getStatusCodeOK() throws Exception {
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
        
        final MemoTime testData = new MemoTime();
        testData.setRoomLocationCd("099");
        testData.setMemoTypeCd("010");
        testData.setMemoDate( new Date() );
        testData.setNote("테스트입력 데이터");

        mockMvc.perform(
            MockMvcRequestBuilders.post("/memo/memotime")
                .param("roomLocationCd", testData.getRoomLocationCd())
                .param("memoTypeCd", testData.getMemoTypeCd())
                .param("memoDate", format.format( testData.getMemoDate() ) )
                .param("note", testData.getNote())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "memoTimeId" )))
                .andExpect(content().string(containsString( testData.getNote() )));
    }

}
