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

    final String BUY_PATH_U = "/buy/";
    final String MEMO_PATH_U = "/memo/";

    final String BUY_PATH_F = "buy/";
    final String MEMO_PATH_F = "memo/";

    final String CON1_STR = "content";
    final String CON2_STR = "dcontent";
    final String CON3_STR = "shippinginfo";

    final String MEM1_STR = "comcd";
    final String MEM2_STR = "locationcd";
    final String MEM3_STR = "memotime";

    final String LIST_STR = "list";
    final String INSERT_STR = "insert";


    // 상품구입정보
    @GetMapping(BUY_PATH_U + CON1_STR + LIST_STR)
    public String selectContentList() {

        return BUY_PATH_F + CON1_STR + LIST_STR;
    }

    @GetMapping(BUY_PATH_U + CON1_STR + INSERT_STR)
    public String insertContent(Model model) {

        ContentList contentList = new ContentList();

        contentList.setXrateYn("N");
        contentList.setWriteReviewYn("N");
        contentList.setReViewYn("N");

        model.addAttribute("ContentList", contentList);

        return BUY_PATH_F + CON1_STR + INSERT_STR;
    }

    // 디지털음원 구입정보
    @GetMapping(BUY_PATH_U + CON2_STR + LIST_STR)
    public String selectDigitalContentList() {

        return BUY_PATH_F + CON2_STR + LIST_STR;
    }

    @GetMapping(BUY_PATH_U + CON2_STR + INSERT_STR)
    public String insertDigitalContent(Model model) {

        DigitalContentList digitalContentList = new DigitalContentList();

        digitalContentList.setBuyingDate( DateUtil.stringToDate( DateUtil.getCurrentDate("yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm" ) );

        model.addAttribute("DigitalContentList", digitalContentList);

        return BUY_PATH_F + CON2_STR + INSERT_STR;
    }

    // 배송정보
    @GetMapping(BUY_PATH_U + CON3_STR + LIST_STR)
    public String selectShippingInfoList() {

        return BUY_PATH_F + CON3_STR + LIST_STR;
    }

    @GetMapping(BUY_PATH_U + CON3_STR + INSERT_STR)
    public String insertShippingInfo(Model model) {

        model.addAttribute("ShippingInfo", new ShippingInfo());

        return BUY_PATH_F + CON3_STR + INSERT_STR;
    }


    // 공통코드 관리
    @GetMapping(MEMO_PATH_U + MEM1_STR + LIST_STR)
    public String selectCommonCdList() {

        return MEMO_PATH_F + MEM1_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH_U + MEM1_STR + INSERT_STR)
    public String insertCommonCd(Model model) {

        CommonCdList commonCdList = new CommonCdList();

        model.addAttribute("CommonCdList", commonCdList);

        return MEMO_PATH_F + MEM1_STR + INSERT_STR;
    }

    // 방 위치관리
    @GetMapping(MEMO_PATH_U + MEM2_STR + LIST_STR)
    public String selectLocationCdList() {

        return MEMO_PATH_F + MEM2_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH_U + MEM2_STR + INSERT_STR)
    public String insertLocationCd(Model model) {

        model.addAttribute("LocationCdList", new LocationCdList());

        return MEMO_PATH_F + MEM2_STR + INSERT_STR;
    }

    // 메모기록
    @GetMapping(MEMO_PATH_U + MEM3_STR + LIST_STR)
    public String selectMemoTime() {

        return MEMO_PATH_F + MEM3_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH_U + MEM3_STR + INSERT_STR)
    public String insertMemoTime(Model model) {

        MemoTime memoTime = new MemoTime();

        memoTime.setMemoDate( DateUtil.stringToDate( DateUtil.getCurrentDate("yyyy-MM-dd") ) );

        model.addAttribute("MemoTime", memoTime);

        return MEMO_PATH_F + MEM3_STR + INSERT_STR;
    }
}