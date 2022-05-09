package net.ddns.scvstorage.scvInfoManager.controller;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
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
import net.ddns.scvstorage.scvInfoManager.common.util.DateUtil;
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
    @Order(3)
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
     * 공통코드관리 수정 테스트
     * 수정할 내용만 전송하여, 수정사항 반영과 기존정보 조회여부 확인
     * @throws Exception
     */
    @Test
    @Order(4)
    @DisplayName("CommonCdList modify, StatusCode is OK")
    public void putCommonCd_modify_getStatusCodeOK() throws Exception {
        
        final CommonCdList testData = new CommonCdList();
        testData.setNote("테스트 수정내용");

        mockMvc.perform(
            MockMvcRequestBuilders.put("/memo/comcd/22")
                .param("note", testData.getNote())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "commonCdListId" )))
                .andExpect(content().string(containsString( testData.getNote() )))
                .andExpect(content().string(containsString( "MEMO_KIND" )));
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
     * 방위치관리 수정 테스트
     * 수정할 내용만 전송하여, 수정사항 반영과 기존정보 조회여부 확인
     * @throws Exception
     */
    @Test
    @Order(9)
    @DisplayName("LocationCdList modify, StatusCode is OK")
    public void putLocationCdList_modify_getStatusCodeOK() throws Exception {
        
        final LocationCdList testData = new LocationCdList();
        testData.setNote("테스트 비고입력");

        mockMvc.perform(
            MockMvcRequestBuilders.put("/memo/location/16")
                .param("note", testData.getNote())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "locationCdListId" )))
                .andExpect(content().string(containsString( testData.getNote() )))
                .andExpect(content().string(containsString( "강서구" )));
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

    /**
     * 메모기록 수정 테스트
     * 수정할 내용만 전송하여, 수정사항 반영과 기존정보 조회여부 확인
     * @throws Exception
     */
    @Test
    @Order(14)
    @DisplayName("MemoTime modify, StatusCode is OK")
    public void putMemoTime_modify_getStatusCodeOK() throws Exception {
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd" );

        final MemoTime testData = new MemoTime();
        testData.setNote("테스트 비고입력");
        testData.setMemoDate( DateUtil.getRandomDate() );

        mockMvc.perform(
            MockMvcRequestBuilders.put("/memo/memotime/2183")
                .param("note", testData.getNote())
                .param("memoDate", format.format( testData.getMemoDate() ) )
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "memoTimeId" )))
                .andExpect(content().string(containsString( testData.getNote() )))
                .andExpect(content().string(containsString( format.format( testData.getMemoDate() ) )))
                .andExpect(content().string(containsString( "017" )));
    }

}
