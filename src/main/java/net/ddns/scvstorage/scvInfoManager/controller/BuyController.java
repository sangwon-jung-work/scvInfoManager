package net.ddns.scvstorage.scvInfoManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    // 데이터 조회
    @GetMapping("/contentList/{contentTypeCd}")
    public Iterable<ContentList> selectContentList(@PathVariable String contentTypeCd) {

        if( contentTypeCd.toUpperCase().equals("ALL") ) {
            return contentListRepository.findAll(); // 타입코드가 입력되지 않았을 경우 전체조회
        } else {
            return contentListRepository.findByContentTypeCd(contentTypeCd.toUpperCase());
        }
        
    }

    /*
    @GetMapping("/contentList/{contentTypeCd}/page")
    public Page<ContentList> selectContentListPage(@PathVariable String contentTypeCd) {

        Pageable pageable = PageRequest.of(1, 5);

        if( contentTypeCd.toUpperCase().equals("ALL") ) {
            return contentListRepository.findAllPage(pageable); // 타입코드가 입력되지 않았을 경우 전체조회
        } else {
            return contentListRepository.findByContentTypeCdPage(contentTypeCd.toUpperCase(), pageable);
        }
        
    }
    */

    @PostMapping("/addContent")
    ContentList addContentList(@ModelAttribute ContentList contentList) {
        return contentListRepository.save(contentList);
    }


    // 데이터 조회
    @GetMapping("/dcontentList")
    public Iterable<DigitalContentList> selectDigitalContentList() {
        return digitalContentListRepository.findAll();
    }

    @PostMapping("/addDContent")
    DigitalContentList addDigitalContentList(@ModelAttribute DigitalContentList digitalContentList) {
        
        //System.out.println(digitalContentList.toString());
        
        return digitalContentListRepository.save(digitalContentList);
    }


    // 데이터 조회
    @GetMapping("/shippingInfoList")
    public Iterable<ShippingInfo> selectShippingInfoList() {
        return shippingInfoRepository.findAll();
    }

    @PostMapping("/addShippingInfo")
    ShippingInfo addShippingInfo(@ModelAttribute ShippingInfo shippingInfo) {
        
        //System.out.println(shippingInfo.toString());
        
        return shippingInfoRepository.save(shippingInfo);
    }
}