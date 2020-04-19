package net.ddns.scvstorage.scvInfoManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    //@Autowired
    //private ShippingInfoRepository shippingInfoRepository;
    
    @GetMapping("/contentList/{contentTypeCd}")
    public Iterable<ContentList> selectContentList(@PathVariable String contentTypeCd) {
        return contentListRepository.findByContentTypeCd(contentTypeCd);
    }

    @GetMapping("/digitalContentList")
    public Iterable<DigitalContentList> selectDigitalContentList() {
        return digitalContentListRepository.findAll();
    }


    @PostMapping("/addContentList")
    ContentList addContentList(@RequestBody ContentList contentList) {
        return contentListRepository.save(contentList);
    }

    @PostMapping("/addDigitalContentList")
    DigitalContentList addDigitalContentList(@RequestBody DigitalContentList digitalContentList) {
        return digitalContentListRepository.save(digitalContentList);
    }
}