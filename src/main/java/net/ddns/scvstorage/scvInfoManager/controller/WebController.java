package net.ddns.scvstorage.scvInfoManager.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ddns.scvstorage.scvInfoManager.common.util.DateUtil;
import net.ddns.scvstorage.scvInfoManager.entity.buy.*;
import net.ddns.scvstorage.scvInfoManager.entity.memo.*;
import net.ddns.scvstorage.scvInfoManager.repository.buy.*;
import net.ddns.scvstorage.scvInfoManager.repository.memo.*;
import net.ddns.scvstorage.scvInfoManager.repository.common.ComboData;

@Controller
@RequestMapping("/web")
public class WebController {

    final String BUY_PATH_U = "/buy/";
    final String MEMO_PATH_U = "/memo/";

    final String BUY_PATH_F = "buy/";
    final String MEMO_PATH_F = "memo/";

    final String CON1_STR = "content";
    final String CON2_STR = "dcontent";
    final String CON3_STR = "shipping";

    final String MEM1_STR = "comcd";
    final String MEM2_STR = "location";
    final String MEM3_STR = "memotime";

    final String LIST_STR = "list";
    final String INSERT_STR = "insert";

    @Autowired
    private ContentListRepository contentListRepository;

    @Autowired
    private DigitalContentListRepository digitalContentListRepository;

    @Autowired
    private ShippingInfoRepository shippingInfoRepository;

    @Autowired
    private LocationCdListRepository locationCdListRepository;

    @Autowired
    private CommonCdListRepository commonCdListRepository;
    
    // 공통 YN 콤보 데이터
    List<String> commonYn = Arrays.asList("N","Y");

    // 상품구입정보
    @GetMapping(BUY_PATH_U + CON1_STR + LIST_STR)
    public String selectContentList() {

        return BUY_PATH_F + CON1_STR + LIST_STR;
    }

    @GetMapping(BUY_PATH_U + CON1_STR + INSERT_STR)
    public String insertContent(Model model) {

        // 공통으로 사용할 콤보용 YN value
        model.addAttribute("ynComboValue", commonYn);

        // 장르를 조회해서 콤보로 만든다
        List<ComboData> allGenre = contentListRepository.getGenre();
        model.addAttribute("allGenre", allGenre);

        // 상품발매국가를 조회해서 콤보로 만든다
        List<ComboData> allSalesCountry = contentListRepository.getSalesCountry();
        model.addAttribute("allSalesCountry", allSalesCountry);

        // 상품종류를 조회해서 콤보로 만든다
        List<ComboData> allConditionType = contentListRepository.getConditionType();
        model.addAttribute("allConditionType", allConditionType);

        // 상품가격통화를 조회해서 콤보로 만든다
        List<ComboData> allCurrency = contentListRepository.getCurrency();
        model.addAttribute("allCurrency", allCurrency);

        // 상품구입처를 조회해서 콤보로 만든다
        List<ComboData> allBuyingLocation = contentListRepository.getBuyingLocation();
        model.addAttribute("allBuyingLocation", allBuyingLocation);

        // 결제수단을 조회해서 콤보로 만든다
        List<ComboData> allPaymentMethod = contentListRepository.getPaymentMethod();
        model.addAttribute("allPaymentMethod", allPaymentMethod);

        // 주문번호가 있는 배송정보를 조회해서 콤보로 만든다
        List<ComboData> allShippingInfo = shippingInfoRepository.getOrderNumOrderByPK();
        model.addAttribute("allshippingInfo", allShippingInfo);

        ContentList contentList = new ContentList();

        contentList.setXrateYn("N"); // 성인구분
        contentList.setWriteReviewYn("N"); // 감상정리여부
        contentList.setReViewYn("N"); // 재감상여부

        // 화면 초기 데이터
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

        // 포멧을 조회해서 콤보로 만든다
        List<ComboData> allFormat = digitalContentListRepository.getFormat();
        model.addAttribute("allFormat", allFormat);

        // 통화를 조회해서 콤보로 만든다
        List<ComboData> allCurrency = digitalContentListRepository.getCurrency();
        model.addAttribute("allCurrency", allCurrency);

        // 구입처를 조회해서 콤보로 만든다
        List<ComboData> allBuyingLocation = digitalContentListRepository.getBuyingLocation();
        model.addAttribute("allBuyingLocation", allBuyingLocation);

        // 레이블을 조회해서 콤보로 만든다
        List<ComboData> allReleaseLabel = digitalContentListRepository.getReleaseLabel();
        model.addAttribute("allReleaseLabel", allReleaseLabel);

        DigitalContentList digitalContentList = new DigitalContentList();

        // 구입일시
        digitalContentList.setBuyingDate( DateUtil.stringToDate( DateUtil.getCurrentDate("yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm" ) );

        // 화면 초기 데이터
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

        // 배송가격통화를 조회해서 콤보로 만든다
        List<ComboData> allCurrency = shippingInfoRepository.getCurrency();
        model.addAttribute("allCurrency", allCurrency);

        // 배송처를 조회해서 콤보로 만든다
        List<ComboData> allBuyingLocation = shippingInfoRepository.getBuyingLocation();
        model.addAttribute("allBuyingLocation", allBuyingLocation);

        // 결제수단을 조회해서 콤보로 만든다
        List<ComboData> allPaymentMethod = shippingInfoRepository.getPaymentMethod();
        model.addAttribute("allPaymentMethod", allPaymentMethod);

        // 화면 초기 데이터
        model.addAttribute("ShippingInfo", new ShippingInfo());

        return BUY_PATH_F + CON3_STR + INSERT_STR;
    }


    // 방 위치관리
    @GetMapping(MEMO_PATH_U + MEM2_STR + LIST_STR)
    public String selectLocationCdList() {

        return MEMO_PATH_F + MEM2_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH_U + MEM2_STR + INSERT_STR)
    public String insertLocationCd(Model model) {

        // 기 생성된 방위치코드를 조회해 온다
        List<ComboData> allLocationCd = locationCdListRepository.getLocationCdList();
        model.addAttribute("allLocationCd", allLocationCd);
        
        // 화면 초기 데이터
        model.addAttribute("LocationCdList", new LocationCdList());

        return MEMO_PATH_F + MEM2_STR + INSERT_STR;
    }

    // 공통코드 관리
    @GetMapping(MEMO_PATH_U + MEM1_STR + LIST_STR)
    public String selectCommonCdList() {

        return MEMO_PATH_F + MEM1_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH_U + MEM1_STR + INSERT_STR)
    public String insertCommonCd(Model model) {

        // 기 생성된 구분코드를 조회해 온다
        List<ComboData> allCdKindList = commonCdListRepository.getCdKindList();
        model.addAttribute("allCdKindList", allCdKindList);

        CommonCdList commonCdList = new CommonCdList();

        // 화면 초기 데이터
        model.addAttribute("CommonCdList", commonCdList);

        return MEMO_PATH_F + MEM1_STR + INSERT_STR;
    }

    // 메모기록
    @GetMapping(MEMO_PATH_U + MEM3_STR + LIST_STR)
    public String selectMemoTime() {

        return MEMO_PATH_F + MEM3_STR + LIST_STR;
    }

    @GetMapping(MEMO_PATH_U + MEM3_STR + INSERT_STR)
    public String insertMemoTime(Model model) {

        // 방위치코드를 조회해서 콤보로 만든다
        List<ComboData> allLocationCd = locationCdListRepository.getLocationCdData();
        model.addAttribute("allLocationCd", allLocationCd);

        // 메모대상코드를 조회해서 콤보로 만든다
        List<ComboData> memeKindComboData = commonCdListRepository.getCommonCdData("MEMO_KIND");
        model.addAttribute("memeKindComboData", memeKindComboData);

        MemoTime memoTime = new MemoTime();

        // 메모일자
        memoTime.setMemoDate( DateUtil.stringToDate( DateUtil.getCurrentDate("yyyy-MM-dd") ) );

        // 화면 초기 데이터
        model.addAttribute("MemoTime", memoTime);

        return MEMO_PATH_F + MEM3_STR + INSERT_STR;
    }
}