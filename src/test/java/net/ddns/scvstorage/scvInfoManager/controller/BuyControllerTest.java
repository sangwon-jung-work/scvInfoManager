package net.ddns.scvstorage.scvInfoManager.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.ddns.scvstorage.scvInfoManager.ScvInfoManagerApplication;
import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList;

@SpringBootTest(classes = ScvInfoManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class BuyControllerTest {
    
    @LocalServerPort
	private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

    Logger logger = LoggerFactory.getLogger(BuyControllerTest.class);
    
    /**
     * 구입이력 조회(전체) 테스트
     */
    @Test
    @Order(1)
    @DisplayName("ContentList findAll StatusCode is OK")
    public void getContentList_findAll_getStatusCodeOK() throws IOException, InterruptedException {
        
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/content/all"),
                HttpMethod.GET, entity, String.class);
        
        String resBody = response.getBody();
        logger.info("getContentList_findAll_getStatusCodeOK() body length {}", resBody.length());
        assertNotNull(resBody);
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    /**
     * 구입이력 조회(구입구분코드별) 테스트
     */
    @Test
    @Order(2)
    @DisplayName("ContentList find by contentType StatusCode is OK")
    public void getContentList_find_Bluray_getStatusCodeOK() throws IOException, InterruptedException {
        
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/content/".concat(ContentList.contentType.Bluray.name())),
                HttpMethod.GET, entity, String.class);
                
        String resBody = response.getBody();
        logger.info("getContentList_find_Bluray_getStatusCodeOK() body length {}", resBody.length());
        assertNotNull(resBody);
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    /**
     * 구입이력(디지털) 조회 테스트
     * @return
     */
    @Test
    @Order(3)
    @DisplayName("DigitalContentList findAll StatusCode is OK")
    public void getDigitalContentList_findAll_getStatusCodeOK() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/dcontent"),
                HttpMethod.GET, entity, String.class);
                
        String resBody = response.getBody();
        logger.info("getDigitalContentList_findAll_getStatusCodeOK() body length {}", resBody.length());
        assertNotNull(resBody);
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());   
    }

    /**
     * 배송정보 조회 테스트
     */
    @Test
    @Order(4)
    @DisplayName("ShippingInfoList findAll StatusCode is OK")
    public void getShippingInfoList_findAll_getStatusCodeOK() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/shipping"),
                HttpMethod.GET, entity, String.class);
                
        String resBody = response.getBody();
        logger.info("getShippingInfoList_findAll_getStatusCodeOK() body length {}", resBody.length());
        assertNotNull(resBody);
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());        
    }

    private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
