package net.ddns.scvstorage.scvInfoManager.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
        Iterable<Integer> idList = Arrays. asList(ids);

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
    ContentList postContentList(@ModelAttribute ContentList contentList) {
        return contentListRepository.save(contentList);
    }

    /**
     * 구입이력 수정(1건)
     * @param contentList 수정할 구입이력 데이터
     * @param contentId 구입이력ID
     * @return
     
    @PutMapping("/contentList/{contentId}")
    ContentList putContentList(@RequestBody List<ContentList> contentList, @PathVariable Integer contentId) {
        
        System.out.println(contentList.size());

        return contentListRepository.findById(contentId)
            .map(contentlist -> {
                BeanUtils.copyProperties(contentList, contentlist);
                contentlist.setContentListId(contentId);
                return contentlist;
                //return contentListRepository.save(contentlist);
            })
            .orElseGet(() -> {
                contentList.setContentListId(contentId);
                return contentList;
                //return contentListRepository.save(contentList);
            });
    }
    */

    /**
     * 구입이력 삭제
     * @param contentId 삭제할 구입이력ID
     */
    @DeleteMapping("/content/{contentId}")
    void deleteContentList(@PathVariable Integer contentId) {
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
    DigitalContentList postDigitalContentList(@ModelAttribute DigitalContentList digitalContentList) {
        return digitalContentListRepository.save(digitalContentList);
    }

    /**
     * 
     * @return
     */


    // 데이터 조회
    @GetMapping("/shippinginfo")
    public Iterable<ShippingInfo> selectShippingInfoList() {
        return shippingInfoRepository.findAll();
    }

    @PostMapping("/shippinginfo")
    ShippingInfo addShippingInfo(@ModelAttribute ShippingInfo shippingInfo) {
        
        //System.out.println(shippingInfo.toString());
        
        return shippingInfoRepository.save(shippingInfo);
    }
}