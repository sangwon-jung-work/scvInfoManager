package net.ddns.scvstorage.scvInfoManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ddns.scvstorage.scvInfoManager.common.util.DateUtil;
import net.ddns.scvstorage.scvInfoManager.entity.buy.*;
import net.ddns.scvstorage.scvInfoManager.entity.memo.*;

@Controller
@RequestMapping("/web")
public class WebController {

    final String BUY_PATH = "/buy/";
    final String MEMO_PATH = "/memo/";

    final String CON1_STR = "content";
    final String CON2_STR = "dcontent";
    final String CON3_STR = "shipping";

    final String MEM1_STR = "comcd";
    final String MEM2_STR = "locationcd";
    final String MEM3_STR = "memotime";

    final String LIST_STR = "list";
    final String INSERT_STR = "insert";


    // 상품구입정보
    @GetMapping(BUY_PATH + CON1_STR + LIST_STR)
    public String selectContentList() {

        return CON1_STR + LIST_STR;
    }

    @GetMapping(BUY_PATH + CON1_STR + INSERT_STR)
    public String insertContent(Model model) {

        ContentList contentList = new ContentList();

        contentList.setXrateYn("N");
        contentList.setWriteReviewYn("N");
        contentList.setReViewYn("N");

        model.addAttribute("ContentList", contentList);

        return CON1_STR + INSERT_STR;
    }

    // 디지털음원 구입정보
    @GetMapping(BUY_PATH + CON2_STR + LIST_STR)
    public String selectDigitalContentList() {

        return CON2_STR + LIST_STR;
    }

    @GetMapping(BUY_PATH + CON2_STR + INSERT_STR)
    public String insertDigitalContent(Model model) {

        DigitalContentList digitalContentList = new DigitalContentList();

        digitalContentList.setBuyingDate( DateUtil.stringToDate( DateUtil.getCurrentDate("yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm" ) );

        model.addAttribute("DigitalContentList", digitalContentList);

        return CON2_STR + INSERT_STR;
    }

    // 배송정보
    @GetMapping(BUY_PATH + CON3_STR + LIST_STR)
    public String selectShippingInfoList() {

        return CON3_STR + LIST_STR;
    }

    @GetMapping(BUY_PATH + CON3_STR + INSERT_STR)
    public String insertShippingInfo(Model model) {

        model.addAttribute("ShippingInfo", new ShippingInfo());

        return CON3_STR + INSERT_STR;
    }


    // 공통코드 관리
    @GetMapping(MEMO_PATH + MEM1_STR + LIST_STR)
    public String selectCommonCdList() {

        return MEM1_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH + MEM1_STR + INSERT_STR)
    public String insertCommonCd(Model model) {

        CommonCdList commonCdList = new CommonCdList();

        model.addAttribute("CommonCdList", commonCdList);

        return MEM1_STR + INSERT_STR;
    }

    // 방 위치관리
    @GetMapping(MEMO_PATH + MEM2_STR + LIST_STR)
    public String selectLocationCdList() {

        return MEM2_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH + MEM2_STR + INSERT_STR)
    public String insertLocationCd(Model model) {

        model.addAttribute("LocationCdList", new LocationCdList());

        return MEM2_STR + INSERT_STR;
    }

    // 메모기록
    @GetMapping(MEMO_PATH + MEM3_STR + LIST_STR)
    public String selectMemoTime() {

        return MEM3_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH + MEM3_STR + INSERT_STR)
    public String insertMemoTime(Model model) {

        MemoTime memoTime = new MemoTime();

        memoTime.setMemoDate( DateUtil.stringToDate( DateUtil.getCurrentDate("yyyy-MM-dd") ) );

        model.addAttribute("MemoTime", memoTime);

        return MEM3_STR + INSERT_STR;
    }
}