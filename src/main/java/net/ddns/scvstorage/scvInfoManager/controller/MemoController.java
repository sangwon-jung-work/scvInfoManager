package net.ddns.scvstorage.scvInfoManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ddns.scvstorage.scvInfoManager.entity.memo.*;
import net.ddns.scvstorage.scvInfoManager.repository.memo.*;

/** 날짜메모 관리용
 * swjung
 * 2020.04.17
 */
@RestController
@RequestMapping("/memo")
public class MemoController {

    @Autowired
    private CommonCdListRepository commonCdListRepository;

    @Autowired
    private LocationCdListRepository locationCdListRepository;

    @Autowired
    private MemoTimeRepository memoTimeRepository;

    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/comcd")
    public Iterable<CommonCdList> selectCommonCdList() {
        return commonCdListRepository.findAll();
    }

    @PostMapping("/comcd")
    CommonCdList addCommonCd(@ModelAttribute CommonCdList commonCdList) {
        return commonCdListRepository.save(commonCdList);
    }


    @GetMapping("/locationcd")
    public Iterable<LocationCdList> selectLocationCdList() {
        return locationCdListRepository.findAll();
    }

    @PostMapping("/locationcd")
    LocationCdList addLocationCdList(@ModelAttribute LocationCdList locationCdList) {
        return locationCdListRepository.save(locationCdList);
    }


    @GetMapping("/memotime")
    public Iterable<MemoTime> selectMemoTime() {
        return memoTimeRepository.findAll();
    }

    @PostMapping("/memotime")
    MemoTime addMemoTime(@ModelAttribute MemoTime memoTime) {
        return memoTimeRepository.save(memoTime);
    }
}