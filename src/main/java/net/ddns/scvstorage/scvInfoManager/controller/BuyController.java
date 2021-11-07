package net.ddns.scvstorage.scvInfoManager.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ddns.scvstorage.scvInfoManager.entity.buy.*;
import net.ddns.scvstorage.scvInfoManager.repository.buy.*;

/** 구입목록
 * swjung
 * 2020.04.17
 */
@RestController
@RequestMapping("/buy")
class BuyController {

    @Autowired
    private ContentListRepository contentListRepository;

    @Autowired
    private DigitalContentListRepository digitalContentListRepository;

    @Autowired
    private ShippingInfoRepository shippingInfoRepository;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    /**
     * 구입이력 조회
     * @param contentTypeCd 타입코드
     * @return
     */
    @GetMapping("/content/{contentTypeCd}")
    public Iterable<ContentList> getContentList(@PathVariable String contentTypeCd) {

        if( contentTypeCd.toUpperCase().equals("ALL") ) {
            return contentListRepository.findAll(); // 타입코드가 입력되지 않았을 경우 전체조회
        } else {
            return contentListRepository.findByContentTypeCd(contentTypeCd.toUpperCase());
        }
        
    }

    /**
     * 구입이력 조회(ID)
     * @param contentTypeCd 타입코드
     * @param contentId 구입이력ID
     * @return
     */
    @GetMapping("/content/{contentTypeCd}/{contentId}")
    public Iterable<ContentList> getContentList(@PathVariable String contentTypeCd, @PathVariable Integer contentId) {

        Integer ids[] = {contentId};
        Iterable<Integer> idList = Arrays.asList(ids);

        if( contentTypeCd.toUpperCase().equals("ALL") ) {
            return contentListRepository.findAllById(idList);
        } else {
            return contentListRepository.findByContentTypeCdAndContentListId(contentTypeCd, contentId);
        }
        
    }

    /*
    @GetMapping("/contentList/{contentTypeCd}/page")
    public Page<ContentList> getContentListPage(@PathVariable String contentTypeCd) {

        Pageable pageable = PageRequest.of(1, 5);

        if( contentTypeCd.toUpperCase().equals("ALL") ) {
            return contentListRepository.findAllPage(pageable); // 타입코드가 입력되지 않았을 경우 전체조회
        } else {
            return contentListRepository.findByContentTypeCdPage(contentTypeCd.toUpperCase(), pageable);
        }
        
    }
    */

    /**
     * 구입이력 등록
     * @param contentList 구입이력 데이터
     * @return
     */
    @PostMapping("/content")
    Iterable<ContentList> postContentList(@ModelAttribute Iterable<ContentList> contentList) {
        return contentListRepository.saveAll(contentList);
    }

    /**
     * 구입이력 수정(ID 1건)
     * @param contentList 수정할 구입이력 데이터
     * @param contentId 구입이력ID
     * @return
     */
    @PutMapping("/contentList/{contentId}")
    ContentList putContent(@RequestBody ContentList contentList, @PathVariable Integer contentId) {
        
        return contentListRepository.findById(contentId)
            .map(selContentList -> {
                BeanUtils.copyProperties(contentList, selContentList);
                selContentList.setContentListId(contentId);
                return contentListRepository.save(selContentList);
            
            }).orElseGet( () -> {
                
                return contentListRepository.save(contentList);
            });

    }
    
    /**
     * 구입이력 삭제(ID 1건)
     * @param contentId 삭제할 구입이력ID
     */
    @DeleteMapping("/content/{contentId}")
    void deleteContent(@PathVariable Integer contentId) {
        contentListRepository.deleteById(contentId);
    }


    /**
     * 구입이력(디지털) 조회
     * @return
     */
    @GetMapping("/dcontent")
    public Iterable<DigitalContentList> getDigitalContentList() {
        return digitalContentListRepository.findAll();
    }

    /** 구입이력(디지털) 등록
     * @param digitalContentList 구입이력(디지털) 데이터
     * @return
     */
    @PostMapping("/dcontent")
    Iterable<DigitalContentList> postDigitalContentList(@ModelAttribute Iterable<DigitalContentList> digitalContentList) {
        return digitalContentListRepository.saveAll(digitalContentList);
    }

    /**
     * 구입이력(디지털) 수정(ID 1건)
     * @param dcontentId 구입이력ID
     * @return
     */
    @PutMapping("/dcontent/{dcontentId}")
    DigitalContentList putDigitalContent(@RequestBody DigitalContentList digitalContentList, @PathVariable Integer dcontentId) {
        
        return digitalContentListRepository.findById(dcontentId)
            .map(selDigitalContentList -> {
                BeanUtils.copyProperties(digitalContentList, selDigitalContentList);
                selDigitalContentList.setDigitalContentListId(dcontentId);
                return digitalContentListRepository.save(selDigitalContentList);
            
            }).orElseGet( () -> {
                
                return digitalContentListRepository.save(digitalContentList);
            });

    }
    
    /**
     * 구입이력(디지털) 삭제(ID 1건)
     * @param dcontentId 삭제할 구입이력ID
     */
    @DeleteMapping("/dcontent/{dcontentId}")
    void deleteDigitalContent(@PathVariable Integer dcontentId) {
        digitalContentListRepository.deleteById(dcontentId);
    }


    /**
     * 배송정보 조회
     * @return
     */
    @GetMapping("/shipping")
    public Iterable<ShippingInfo> getShippingInfoList() {
        return shippingInfoRepository.findAll();
    }

    /** 배송정보 등록
     * @param shippingInfo 배송정보 데이터
     * @return
     */
    @PostMapping("/shipping")
    Iterable<ShippingInfo> postShippingInfoList(@ModelAttribute Iterable<ShippingInfo> shippingInfo) {
        return shippingInfoRepository.saveAll(shippingInfo);
    }

    /**
     * 배송정보 수정(ID 1건)
     * @param shippingId 배송정보ID
     * @return
     */
    @PutMapping("/shipping/{shippingId}")
    ShippingInfo putShippingInfo(@RequestBody ShippingInfo shippingInfo, @PathVariable Integer shippingId) {
        
        return shippingInfoRepository.findById(shippingId)
            .map(selShippingInfoList -> {
                BeanUtils.copyProperties(shippingInfo, selShippingInfoList);
                selShippingInfoList.setShippingInfoId(shippingId);
                return shippingInfoRepository.save(selShippingInfoList);
            
            }).orElseGet( () -> {
                
                return shippingInfoRepository.save(shippingInfo);
            });

    }
    
    /**
     * 배송정보 삭제(ID 1건)
     * @param dcontentId 삭제할 배송정보ID
     */
    @DeleteMapping("/shipping/{shippingId}")
    void deleteShippingInfo(@PathVariable Integer shippingId) {
        shippingInfoRepository.deleteById(shippingId);
    }

}