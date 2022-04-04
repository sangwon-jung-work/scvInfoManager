package net.ddns.scvstorage.scvInfoManager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
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
public class BuyControllerTest {
    
    @LocalServerPort
	private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

    @Test
    public void getContentList_findAll_getStatusCodeOK() throws IOException, InterruptedException {
        
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/content/all"),
                HttpMethod.GET, entity, String.class);
                
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void getContentList_find_Bluray_getStatusCodeOK() throws IOException, InterruptedException {
        
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/content/".concat(ContentList.contentType.Bluray.name())),
                HttpMethod.GET, entity, String.class);
                
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void getDigitalContentList_findAll_getStatusCodeOK() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/dcontent"),
                HttpMethod.GET, entity, String.class);
                
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());   
    }

    @Test
    public void getShippingInfoList_findAll_getStatusCodeOK() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/buy/shipping"),
                HttpMethod.GET, entity, String.class);
                
        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());        
    }

    private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
