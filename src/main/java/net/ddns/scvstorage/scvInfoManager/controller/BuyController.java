package net.ddns.scvstorage.scvInfoManager.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ddns.scvstorage.scvInfoManager.common.util.ObjectUtil;
import net.ddns.scvstorage.scvInfoManager.entity.buy.*;
import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList.contentType;
import net.ddns.scvstorage.scvInfoManager.repository.buy.*;

/** 구입목록
 * swjung
 * 2020.04.17
 */
@RestController
@RequestMapping(value = "/buy", produces = "application/json;charset=utf8")
public class BuyController {

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
     * @return 조회된 구입이력 데이터
     */
    @GetMapping("/content/{contentTypeCd}")
    public Iterable<ContentList> getContentListByTypeCd(@PathVariable String contentTypeCd) {

        contentType typeChk = ContentList.convertContentTypeCd(contentTypeCd);

        if( typeChk == contentType.all ) {
            return contentListRepository.findAll(); // contentType 이 전체(all) 일 경우 전체 조회
        } else if( typeChk != null ) {
            return contentListRepository.findByContentTypeCd(typeChk);
        } else {
            // 비정상 케이스일 경우 조회하지 않음
            return new ArrayList<ContentList>();
        }
    }

    /**
     * 구입이력 조회(ID)
     * @param contentTypeCd 타입코드
     * @param key 구입이력ID
     * @return 조회된 구입이력 데이터
     */
    @GetMapping("/content/{contentTypeCd}/{key}")
    public Iterable<ContentList> getContentListById(@PathVariable String contentTypeCd, @PathVariable Integer key) {

        Integer ids[] = {key};
        Iterable<Integer> idList = Arrays.asList(ids);

        contentType typeChk = ContentList.convertContentTypeCd(contentTypeCd);

        if( typeChk == contentType.all ) {
            return contentListRepository.findAllById(idList);
        } else if( typeChk != null ) {
            return contentListRepository.findByContentTypeCdAndContentListId( typeChk, key);
        } else {
            // 비정상 케이스일 경우 조회하지 않음
            return new ArrayList<ContentList>();
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
     * @param contentList 입력할 구입이력 데이터
     * @return 입력된 구입이력 데이터
     */
    @PostMapping("/content")
    public ContentList postContentList(@ModelAttribute ContentList contentList) {
        return contentListRepository.save(contentList);
    }

    /**
     * 구입이력 수정(ID 1건)
     * @param contentList 수정할 구입이력 데이터
     * @param key 구입이력ID
     * @return 수정된 구입이력 데이터
     */
    @PutMapping("/content/{key}")
    public ContentList putContent(@ModelAttribute ContentList contentList, @PathVariable Integer key) {
        
        return contentListRepository.findById(key)
            .map(selContentList -> {
                BeanUtils.copyProperties(contentList, selContentList, ObjectUtil.getNullPropertyNames(contentList));
                selContentList.setContentListId(key);
                return contentListRepository.save(selContentList);
            
            }).orElseGet( () -> {
                
                return contentListRepository.save(contentList);
            });

    }
    
    /**
     * 구입이력 삭제(ID 1건)
     * @param key 삭제할 구입이력ID
     * @return 삭제된 구입이력 데이터
     */
    @DeleteMapping("/content/{key}")
    public ContentList deleteContent(@PathVariable Integer key) {
        
        Optional<ContentList> contentDeleteOptional = contentListRepository.findById(key);
        if (!contentDeleteOptional.isPresent()) {
            return null;
        }
        ContentList contentToDelete = contentDeleteOptional.get();
        contentListRepository.delete(contentToDelete);
        
        return contentToDelete;
    }

    /**
     * 구입이력(디지털) 조회
     * @return 조회된 구입이력(디지털) 데이터
     */
    @GetMapping("/dcontent")
    public Iterable<DigitalContentList> getDigitalContentList() {
        return digitalContentListRepository.findAll();
    }

    /**
     * 구입이력(디지털) 조회(ID)
     * @param key 구입이력(디지털) ID
     * @return 조회된 구입이력(디지털) 데이터
     */
    @GetMapping("/dcontent/{key}")
    public Iterable<DigitalContentList> getDigitalContentListById(@PathVariable Integer key) {

        return Arrays.asList( digitalContentListRepository.findById(key)
            .map(selectedData -> {
                return selectedData;
            
            }).orElseGet( () -> {
                return new DigitalContentList();
            }) );
    }

    /** 구입이력(디지털) 등록
     * @param digitalContentList 입력할 구입이력(디지털) 데이터
     * @return 입력된 구입이력(디지털) 데이터
     */
    @PostMapping("/dcontent")
    public DigitalContentList postDigitalContentList(@ModelAttribute DigitalContentList digitalContentList) {
        return digitalContentListRepository.save(digitalContentList);
    }

    /**
     * 구입이력(디지털) 수정(ID 1건)
     * @param digitalContentList 수정할 구입이력(디지털) 데이터
     * @param key 수정할 구입이력(디지털) ID
     * @return 수정된 구입이력(디지털) 데이터
     */
    @PutMapping("/dcontent/{key}")
    public DigitalContentList putDigitalContent(@ModelAttribute DigitalContentList digitalContentList, @PathVariable Integer key) {
        
        return digitalContentListRepository.findById(key)
            .map(selDigitalContentList -> {
                BeanUtils.copyProperties(digitalContentList, selDigitalContentList, ObjectUtil.getNullPropertyNames(digitalContentList));
                selDigitalContentList.setDigitalContentListId(key);
                return digitalContentListRepository.save(selDigitalContentList);
            
            }).orElseGet( () -> {
                
                return digitalContentListRepository.save(digitalContentList);
            });

    }
    
    /**
     * 구입이력(디지털) 삭제(ID 1건)
     * @param key 삭제할 구입이력ID
     * @return 삭제된 구입이력(디지털) 데이터
     */
    @DeleteMapping("/dcontent/{key}")
    public DigitalContentList deleteDigitalContent(@PathVariable Integer key) {
        
        Optional<DigitalContentList> dContentDeleteOptional = digitalContentListRepository.findById(key);
        if (!dContentDeleteOptional.isPresent()) {
            return null;
        }
        DigitalContentList dContentToDelete = dContentDeleteOptional.get();
        digitalContentListRepository.delete(dContentToDelete);
        
        return dContentToDelete;
    }


    /**
     * 배송정보 조회
     * @return 조회된 배송정보 데이터
     */
    @GetMapping("/shipping")
    public Iterable<ShippingInfo> getShippingInfoList() {
        return shippingInfoRepository.findAll();
    }

    /**
     * 배송정보 조회(ID)
     * @param key 배송정보ID
     * @return 조회된 배송정보 데이터
     */
    @GetMapping("/shipping/{key}")
    public Iterable<ShippingInfo> getShippingInfoListById(@PathVariable Integer key) {

        return Arrays.asList( shippingInfoRepository.findById(key)
            .map(selectedData -> {
                return selectedData;
            
            }).orElseGet( () -> {
                return new ShippingInfo();
            }) );
    }

    /** 배송정보 등록
     * @param shippingInfo 입력할 배송정보 데이터
     * @return 입력된 배송정보 데이터
     */
    @PostMapping("/shipping")
    public ShippingInfo postShippingInfoList(@ModelAttribute ShippingInfo shippingInfo) {
        return shippingInfoRepository.save(shippingInfo);
    }

    /**
     * 배송정보 수정(ID 1건)
     * @param shippingInfo 입력할 배송정보 데이터
     * @param key 수정할 배송정보ID
     * @return 수정된 배송정보 데이터
     */
    @PutMapping("/shipping/{key}")
    public ShippingInfo putShippingInfo(@ModelAttribute ShippingInfo shippingInfo, @PathVariable Integer key) {
        
        return shippingInfoRepository.findById(key)
            .map(selShippingInfoList -> {
                BeanUtils.copyProperties(shippingInfo, selShippingInfoList, ObjectUtil.getNullPropertyNames(shippingInfo));
                selShippingInfoList.setShippingInfoId(key);
                return shippingInfoRepository.save(selShippingInfoList);
            
            }).orElseGet( () -> {
                
                return shippingInfoRepository.save(shippingInfo);
            });

    }
    
    /**
     * 배송정보 삭제(ID 1건)
     * @param key 삭제할 배송정보ID
     * @return 삭제된 배송정보 데이터
     */
    @DeleteMapping("/shipping/{key}")
    public ShippingInfo deleteShippingInfo(@PathVariable Integer key) {
        
        Optional<ShippingInfo> shoppingDeleteOptional = shippingInfoRepository.findById(key);
        if (!shoppingDeleteOptional.isPresent()) {
            return null;
        }
        ShippingInfo shoppingToDelete = shoppingDeleteOptional.get();
        shippingInfoRepository.delete(shoppingToDelete);
        
        return shoppingToDelete;
    }

}