package net.ddns.scvstorage.scvInfoManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/commonCdList")
    public Iterable<CommonCdList> selectCommonCdList() {
        return commonCdListRepository.findAll();
    }

    @GetMapping("/locationCdList")
    public Iterable<LocationCdList> selectLocationCdList() {
        return locationCdListRepository.findAll();
    }

    @GetMapping("/memoTime")
    public Iterable<MemoTime> selectMemoTime() {
        return memoTimeRepository.findAll();
    }


    @PostMapping("/addCommonCdList")
    CommonCdList addCommonCdList(@RequestBody CommonCdList commonCdList) {
        return commonCdListRepository.save(commonCdList);
    }

    @PostMapping("/addLocationCdList")
    LocationCdList addLocationCdList(@RequestBody LocationCdList locationCdList) {
        return locationCdListRepository.save(locationCdList);
    }

    @PostMapping("/addMemoTime")
    MemoTime addMemoTime(@RequestBody MemoTime memoTime) {
        return memoTimeRepository.save(memoTime);
    }
}