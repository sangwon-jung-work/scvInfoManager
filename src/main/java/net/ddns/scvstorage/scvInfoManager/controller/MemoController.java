package net.ddns.scvstorage.scvInfoManager.controller;

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


    /**
     * 공통코드관리 조회
     * @return
     */
    @GetMapping("/comcd")
    public Iterable<CommonCdList> getCommonCdList() {
        return commonCdListRepository.findAll();
    }

    /**
     * 공통코드관리 등록
     * @param commonCdList 공통코드관리 데이터
     * @return
     */
    @PostMapping("/comcd")
    public CommonCdList postCommonCd(@ModelAttribute CommonCdList commonCdList) {
        return commonCdListRepository.save(commonCdList);
    }

    /**
     * 공통코드관리 수정(ID 1건)
     * @param contentList 수정할 공통코드관리 데이터
     * @param commonCdId 공통코드관리ID
     * @return
     */
    @PutMapping("/comcd/{commonCdId}")
    public CommonCdList putCommonCd(@RequestBody CommonCdList commonCdList, @PathVariable Integer commonCdId) {
        


        return commonCdListRepository.findById(commonCdId)
            .map(selCommonCdList -> {
                BeanUtils.copyProperties(commonCdList, selCommonCdList);
                selCommonCdList.setCommonCdListId(commonCdId);
                return commonCdListRepository.save(selCommonCdList);
            
            }).orElseGet( () -> {
                
                return commonCdListRepository.save(commonCdList);
            });

    }
    
    /**
     * 공통코드관리 삭제(ID 1건)
     * @param commonCdId 삭제할 공통코드관리ID
     */
    @DeleteMapping("/comcd/{commonCdId}")
    public CommonCdList deleteCommonCd(@PathVariable Integer commonCdId) {
        
        Optional<CommonCdList> comCdDeleteOptional = commonCdListRepository.findById(commonCdId);
        if (!comCdDeleteOptional.isPresent()) {
            return null;
        }
        CommonCdList comCdToDelete = comCdDeleteOptional.get();
        commonCdListRepository.delete(comCdToDelete);

        return comCdToDelete;
    }


    /**
     * 방위치관리 조회
     * @return
     */
    @GetMapping("/location")
    public Iterable<LocationCdList> getLocationCdList() {
        return locationCdListRepository.findAll();
    }

    /**
     * 방위치관리 등록
     * @param locationCdList 방위치관리 데이터
     * @return
     */
    @PostMapping("/location")
    public LocationCdList postLocationCdList(@ModelAttribute LocationCdList locationCdList) {
        return locationCdListRepository.save(locationCdList);
    }

    /**
     * 방위치관리 수정(ID 1건)
     * @param locationCdList 수정할 방위치관리 데이터
     * @param locationId 방위치관리ID
     * @return
     */
    @PutMapping("/location/{locationId}")
    public LocationCdList putLocationCdList(@RequestBody LocationCdList locationCdList, @PathVariable Integer locationId) {
        
        return locationCdListRepository.findById(locationId)
            .map(selLocationCdList -> {
                BeanUtils.copyProperties(locationCdList, selLocationCdList);
                selLocationCdList.setLocationCdListId(locationId);
                return locationCdListRepository.save(selLocationCdList);
            
            }).orElseGet( () -> {
                
                return locationCdListRepository.save(locationCdList);
            });

    }
    
    /**
     * 방위치관리 삭제(ID 1건)
     * @param locationId 삭제할 방위치관리ID
     */
    @DeleteMapping("/location/{locationId}")
    public LocationCdList deleteLocationCdList(@PathVariable Integer locationId) {
        
        Optional<LocationCdList> locCdDeleteOptional = locationCdListRepository.findById(locationId);
        if (!locCdDeleteOptional.isPresent()) {
            return null;
        }
        LocationCdList locCdToDelete = locCdDeleteOptional.get();
        locationCdListRepository.delete(locCdToDelete);
        
        return locCdToDelete;
    }


    /**
     * 메모기록 조회
     * @return
     */
    @GetMapping("/memotime")
    public Iterable<MemoTime> getMemoTime() {
        return memoTimeRepository.findAll();
    }

    /**
     * 메모기록 등록
     * @param memoTime 방위치관리 데이터
     * @return
     */
    @PostMapping("/memotime")
    public MemoTime postMemoTime(@ModelAttribute MemoTime memoTime) {
        return memoTimeRepository.save(memoTime);
    }

    /**
     * 메모기록 수정(ID 1건)
     * @param memoTime 수정할 메모기록 데이터
     * @param memotimeId 메모기록ID
     * @return
     */
    @PutMapping("/memotime/{memotimeId}")
    public MemoTime putMemoTime(@RequestBody MemoTime memoTime, @PathVariable Integer memotimeId) {
        
        return memoTimeRepository.findById(memotimeId)
            .map(selMemotime -> {
                BeanUtils.copyProperties(memoTime, selMemotime);
                selMemotime.setMemoTimeId(memotimeId);
                return memoTimeRepository.save(selMemotime);
            
            }).orElseGet( () -> {
                
                return memoTimeRepository.save(memoTime);
            });

    }
    
    /**
     * 메모기록 삭제(ID 1건)
     * @param locationId 삭제할 메모기록ID
     */
    @DeleteMapping("/memotime/{memotimeId}")
    public MemoTime deleteMemoTime(@PathVariable Integer memotimeId) {
        
        Optional<MemoTime> memoDeleteOptional = memoTimeRepository.findById(memotimeId);
        if (!memoDeleteOptional.isPresent()) {
            return null;
        }
        MemoTime memoToDelete = memoDeleteOptional.get();
        memoTimeRepository.delete(memoToDelete);
        
        return memoToDelete;
    }

}