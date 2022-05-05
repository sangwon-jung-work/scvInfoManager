package net.ddns.scvstorage.scvInfoManager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import net.ddns.scvstorage.scvInfoManager.ScvInfoManagerApplication;
import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList;
import net.ddns.scvstorage.scvInfoManager.entity.buy.DigitalContentList;
import net.ddns.scvstorage.scvInfoManager.entity.buy.ShippingInfo;
import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList.contentType;

import static org.hamcrest.Matchers.containsString;

@SpringBootTest(classes = ScvInfoManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(OrderAnnotation.class)
public class BuyControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    /**
     * 구입이력 조회(전체) 테스트
     * @throws Exception
     */
    @Test
    @Order(1)
    @DisplayName("ContentList findAll, StatusCode is OK")
    public void getContentListByTypeCd_findAll_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/buy/content/all")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( ContentList.contentType.Album.name() )));
    }

    /**
     * 구입이력 조회(구입구분코드별) 테스트
     * @throws Exception
     */
    @Test
    @Order(2)
    @DisplayName("ContentList find by contentType, StatusCode is OK")
    public void getContentListByTypeCd_find_Bluray_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/buy/content/".concat(ContentList.contentType.Bluray.name()))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( ContentList.contentType.Bluray.name() )));
    }

    /**
     * 구입이력 조회(ID) 테스트
     * @throws Exception
     */
    @Test
    @Order(3)
    @DisplayName("ContentList find by contentType and ID, StatusCode is OK")
    public void getContentListById_findBy_contentTypeAndID_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/buy/content/".concat(ContentList.contentType.Album.name()).concat("/1430") )
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "contentListId" )));
    }

    /**
     * 구입이력 입력 테스트
     * @throws Exception
     */
    @Test
    @Order(4)
    @DisplayName("ContentList create, StatusCode is OK")
    public void postContentList_create_getStatusCodeOK() throws Exception {
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd" );
        
        final ContentList testData = new ContentList();
        testData.setContentTypeCd( contentType.Bluray );
        testData.setTitle( "극장판 테스트를 위하여" );
        testData.setVersion( "넘버링한정생산판" );
        testData.setReceiptDate( new Date() );
        testData.setSalesCountry( "KOR" );
        testData.setConditionType( "신품" );
        testData.setBuyingLocation( "알라딘" );
        testData.setPaymentMethod( "VISA1123" );
        testData.setReleaseDate( new Date() );
        testData.setMemo( "테스트데이터" );

        mockMvc.perform(
            MockMvcRequestBuilders.post("/buy/content")
                .param("contentTypeCd", testData.getContentTypeCd().name())
                .param("title", testData.getTitle())
                .param("version", testData.getVersion())
                .param("receiptDate", format.format( testData.getReceiptDate() ) )
                .param("salesCountry", testData.getSalesCountry())
                .param("conditionType", testData.getConditionType())
                .param("buyingLocation", testData.getBuyingLocation())
                .param("paymentMethod", testData.getPaymentMethod())
                .param("releaseDate", format.format( testData.getReceiptDate() ) )
                .param("memo", testData.getMemo())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "contentListId" )))
                .andExpect(content().string(containsString( testData.getTitle() )));
    }

    /**
     * 구입이력(디지털) 조회 테스트
     * @return
     * @throws Exception
     */
    @Test
    @Order(7)
    @DisplayName("DigitalContentList findAll StatusCode is OK")
    public void getDigitalContentList_findAll_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/buy/dcontent")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "digitalContentListId" )));
        
    }

    /**
     * 구입이력(디지털) 조회(ID) 테스트
     * @throws Exception
     */
    @Test
    @Order(8)
    @DisplayName("DigitalContentList findById, StatusCode is OK")
    public void getDigitalContentListById_findById_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/buy/dcontent".concat("/2") )
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "digitalContentListId" )));
    }

    /**
     * 구입이력 입력 테스트
     * @throws Exception
     */
    @Test
    @Order(9)
    @DisplayName("DigitalContentList create, StatusCode is OK")
    public void postDigitalContentList_create_getStatusCodeOK() throws Exception {
        
        DateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm" );
        DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd" );
        
        final DigitalContentList testData = new DigitalContentList();
        testData.setTitle( "테스트를 위하여" );
        testData.setArtist( "테스트 가수" );
        testData.setAlbum( "Test Album Name" );
        testData.setFormat("FLAC");
        testData.setSampleRate(48000);
        testData.setBitDepth(24);
        testData.setSize( Float.valueOf("130.56") );
        testData.setBuyingDate( new Date() );
        testData.setBuyingLocation("벅스");
        testData.setReleaseLabel("KakaoM");
        testData.setReleaseDate( new Date() );
        testData.setMemo( "테스트데이터" );

        mockMvc.perform(
            MockMvcRequestBuilders.post("/buy/dcontent")
                .param("title", testData.getTitle())
                .param("artist", testData.getArtist())
                .param("album", testData.getAlbum())
                .param("format", testData.getFormat() )
                .param("sampleRate", String.valueOf( testData.getSampleRate() ) )
                .param("bitDepth", String.valueOf( testData.getBitDepth() ) )
                .param("size", String.valueOf( testData.getSize() ) )
                .param("buyingDate", isoFormat.format( testData.getBuyingDate() ) )
                .param("buyingLocation", testData.getBuyingLocation() )
                .param("releaseLabel", testData.getReleaseLabel() )
                .param("releaseDate", simpleFormat.format( testData.getReleaseDate() ) )
                .param("memo", testData.getMemo())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "digitalContentListId" )))
                .andExpect(content().string(containsString( testData.getTitle() )));
    }

    /**
     * 배송정보 조회 테스트
     */
    @Test
    @Order(12)
    @DisplayName("ShippingInfoList findAll StatusCode is OK")
    public void getShippingInfoList_findAll_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/buy/shipping")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "shippingInfoId" )));
        
    }

    /**
     * 배송정보 조회(ID) 테스트
     * @throws Exception
     */
    @Test
    @Order(13)
    @DisplayName("ShippingInfoList findById, StatusCode is OK")
    public void getShippingInfoListById_findById_getStatusCodeOK() throws Exception {
        
        mockMvc.perform(
            MockMvcRequestBuilders.get("/buy/shipping".concat("/1083") )
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "shippingInfoId" )));
    }

    /**
     * 구입이력 입력 테스트
     * @throws Exception
     */
    @Test
    @Order(14)
    @DisplayName("ShippingInfoList create, StatusCode is OK")
    public void postShippingInfoList_create_getStatusCodeOK() throws Exception {
        
        final ShippingInfo testData = new ShippingInfo();
        testData.setBuyingLocation("이하넥스");
        testData.setPaymentMethod("AMEX4457");
        testData.setMemo( "테스트데이터" );

        mockMvc.perform(
            MockMvcRequestBuilders.post("/buy/shipping")
                .param("buyingLocation", testData.getBuyingLocation())
                .param("paymentMethod", testData.getPaymentMethod())
                .param("memo", testData.getMemo())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString( "shippingInfoId" )))
                .andExpect(content().string(containsString( testData.getBuyingLocation() )));
    }
}
