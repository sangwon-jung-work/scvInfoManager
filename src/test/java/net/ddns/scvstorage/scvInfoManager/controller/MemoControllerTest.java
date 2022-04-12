package net.ddns.scvstorage.scvInfoManager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.ddns.scvstorage.scvInfoManager.ScvInfoManagerApplication;

@SpringBootTest(classes = ScvInfoManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class MemoControllerTest {
    
    @LocalServerPort
	private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
    
    /**
     * 공통코드관리 조회 테스트
     */
    @Test
    @Order(1)
    @DisplayName("CommonCdList findAll StatusCode is OK")
    void getCommonCdList_findAll_getStatusCodeOK() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/memo/comcd"),
                HttpMethod.GET, entity, String.class);
                
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    /**
     * 방위치관리 조회 테스트
     */
    @Test
    @Order(2)
    @DisplayName("LocationCdList findAll StatusCode is OK")
    void getLocationCdList_findAll_getStatusCodeOK() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/memo/location"),
                HttpMethod.GET, entity, String.class);
                
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    /**
     * 메모기록 조회 테스트
     */
    @Test
    @Order(3)
    @DisplayName("MemoTime findAll StatusCode is OK")
    void getMemoTime_findAll_getStatusCodeOK() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/memo/memotime"),
                HttpMethod.GET, entity, String.class);
                
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
