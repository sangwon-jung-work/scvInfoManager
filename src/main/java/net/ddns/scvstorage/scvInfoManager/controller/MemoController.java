package net.ddns.scvstorage.scvInfoManager.controller;

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
@RequestMapping(value = "/memo", produces = "application/json;charset=utf8")
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


    /**
     * 공통코드관리 조회
     * @return 조회된 공통코드 데이터
     */
    @GetMapping("/comcd")
    public Iterable<CommonCdList> getCommonCdList() {
        return commonCdListRepository.findAll();
    }

    /**
     * 공통코드관리 조회(ID)
     * @param key 공통코드관리ID
     * @return 조회된 공통코드 데이터
     */
    @GetMapping("/comcd/{key}")
    public Iterable<CommonCdList> getCommonCdListById(@PathVariable Integer key) {

        return Arrays.asList( commonCdListRepository.findById(key)
            .map(selectedData -> {
                return selectedData;
            
            }).orElseGet( () -> {
                return new CommonCdList();
            }) );
    }

    /**
     * 공통코드관리 등록
     * @param commonCdList 입력할 공통코드 데이터
     * @return 입력된 공통코드 데이터
     */
    @PostMapping("/comcd")
    public CommonCdList postCommonCd(@ModelAttribute CommonCdList commonCdList) {
        return commonCdListRepository.save(commonCdList);
    }

    /**
     * 공통코드관리 수정(ID 1건)
     * @param commonCdList 수정할 공통코드 데이터
     * @param key 수정할 공통코드관리ID
     * @return 수정된 공통코드 데이터
     */
    @PutMapping("/comcd/{key}")
    public CommonCdList putCommonCd(@RequestBody CommonCdList commonCdList, @PathVariable Integer key) {
        
        return commonCdListRepository.findById(key)
            .map(selCommonCdList -> {
                BeanUtils.copyProperties(commonCdList, selCommonCdList);
                selCommonCdList.setCommonCdListId(key);
                return commonCdListRepository.save(selCommonCdList);
            
            }).orElseGet( () -> {
                
                return commonCdListRepository.save(commonCdList);
            });

    }
    
    /**
     * 공통코드관리 삭제(ID 1건)
     * @param key 삭제할 공통코드관리ID
     * @return 삭제된 공통코드 데이터
     */
    @DeleteMapping("/comcd/{key}")
    public CommonCdList deleteCommonCd(@PathVariable Integer key) {
        
        Optional<CommonCdList> comCdDeleteOptional = commonCdListRepository.findById(key);
        if (!comCdDeleteOptional.isPresent()) {
            return null;
        }
        CommonCdList comCdToDelete = comCdDeleteOptional.get();
        commonCdListRepository.delete(comCdToDelete);

        return comCdToDelete;
    }


    /**
     * 방위치관리 조회
     * @return 조회된 방위치 데이터
     */
    @GetMapping("/location")
    public Iterable<LocationCdList> getLocationCdList() {
        return locationCdListRepository.findAll();
    }

    /**
     * 방위치관리 조회(ID)
     * @param key 방위치관리ID
     * @return 조회된 방위치 데이터
     */
    @GetMapping("/location/{key}")
    public Iterable<LocationCdList> getLocationCdListById(@PathVariable Integer key) {

        return Arrays.asList( locationCdListRepository.findById(key)
            .map(selectedData -> {
                return selectedData;
            
            }).orElseGet( () -> {
                return new LocationCdList();
            }) );
    }

    /**
     * 방위치관리 등록
     * @param locationCdList 입력할 방위치 데이터
     * @return 입력된 방위치 데이터
     */
    @PostMapping("/location")
    public LocationCdList postLocationCdList(@ModelAttribute LocationCdList locationCdList) {
        return locationCdListRepository.save(locationCdList);
    }

    /**
     * 방위치관리 수정(ID 1건)
     * @param locationCdList 수정할 방위치 데이터
     * @param key 수정할 방위치관리ID
     * @return 수정된 방위치 데이터
     */
    @PutMapping("/location/{key}")
    public LocationCdList putLocationCdList(@RequestBody LocationCdList locationCdList, @PathVariable Integer key) {
        
        return locationCdListRepository.findById(key)
            .map(selLocationCdList -> {
                BeanUtils.copyProperties(locationCdList, selLocationCdList);
                selLocationCdList.setLocationCdListId(key);
                return locationCdListRepository.save(selLocationCdList);
            
            }).orElseGet( () -> {
                
                return locationCdListRepository.save(locationCdList);
            });

    }
    
    /**
     * 방위치관리 삭제(ID 1건)
     * @param key 삭제할 방위치관리ID
     * @return 삭제된 방위치 데이터
     */
    @DeleteMapping("/location/{key}")
    public LocationCdList deleteLocationCdList(@PathVariable Integer key) {
        
        Optional<LocationCdList> locCdDeleteOptional = locationCdListRepository.findById(key);
        if (!locCdDeleteOptional.isPresent()) {
            return null;
        }
        LocationCdList locCdToDelete = locCdDeleteOptional.get();
        locationCdListRepository.delete(locCdToDelete);
        
        return locCdToDelete;
    }


    /**
     * 메모기록 조회
     * @return 조회된 메모기록 데이터
     */
    @GetMapping("/memotime")
    public Iterable<MemoTime> getMemoTime() {
        return memoTimeRepository.findAll();
    }

    /**
     * 메모기록 조회(ID)
     * @param key 메모기록ID
     * @return 조회된 메모기록 데이터
     */
    @GetMapping("/memotime/{key}")
    public Iterable<MemoTime> getMemoTimeById(@PathVariable Integer key) {

        return Arrays.asList( memoTimeRepository.findById(key)
            .map(selectedData -> {
                return selectedData;
            
            }).orElseGet( () -> {
                return new MemoTime();
            }) );
    }

    /**
     * 메모기록 등록
     * @param memoTime 입력할 메모기록 데이터
     * @return 입력된 메모기록 데이터
     */
    @PostMapping("/memotime")
    public MemoTime postMemoTime(@ModelAttribute MemoTime memoTime) {
        return memoTimeRepository.save(memoTime);
    }

    /**
     * 메모기록 수정(ID 1건)
     * @param memoTime 수정할 메모기록 데이터
     * @param key 메모기록ID
     * @return 수정된 메모기록 데이터
     */
    @PutMapping("/memotime/{key}")
    public MemoTime putMemoTime(@RequestBody MemoTime memoTime, @PathVariable Integer key) {
        
        return memoTimeRepository.findById(key)
            .map(selMemotime -> {
                BeanUtils.copyProperties(memoTime, selMemotime);
                selMemotime.setMemoTimeId(key);
                return memoTimeRepository.save(selMemotime);
            
            }).orElseGet( () -> {
                
                return memoTimeRepository.save(memoTime);
            });

    }
    
    /**
     * 메모기록 삭제(ID 1건)
     * @param key 메모기록ID
     * @return 삭제된 메모기록 데이터
     */
    @DeleteMapping("/memotime/{key}")
    public MemoTime deleteMemoTime(@PathVariable Integer key) {
        
        Optional<MemoTime> memoDeleteOptional = memoTimeRepository.findById(key);
        if (!memoDeleteOptional.isPresent()) {
            return null;
        }
        MemoTime memoToDelete = memoDeleteOptional.get();
        memoTimeRepository.delete(memoToDelete);
        
        return memoToDelete;
    }

}