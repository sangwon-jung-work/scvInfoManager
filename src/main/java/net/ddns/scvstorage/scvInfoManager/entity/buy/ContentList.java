package net.ddns.scvstorage.scvInfoManager.entity.buy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

/** 상품구입정보 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class ContentList {

    public enum contentType {
        Bluray("Bluray"),
        DVD("DVD"),
        Album("Album"),
        Book("Book"),
        Figure("Figure"),
        all("all");

        private String type;

        private contentType(String type) {
            this.type = type;
        }

        public String getCode() {
            return type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contentListId; // 구입정보ID

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    private contentType contentTypeCd; // 구입구분코드

    @Column
    @NotNull
    private String title; // 제목/타이틀
    @Column
    private String artist; // 아티스트
    @Column
    private String contentNote; // 비고
    @Column
    private String version; // 버전

    @Column
    private String genre; // 분류(장르)
    @Column
    private String xrateYn; // 성인구분
    @Column
    private String writeReviewYn; // 감상정리여부
    @Column
    private String reViewYn; // 재감상여부
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receiptDate; // 수령일자
    @Column
    private String contentBlogUrl; // 관련글

    @Column
    @NotNull
    private String salesCountry; // 상품발매국가
    @Column
    @NotNull
    private String conditionType; // 상품종류
    @Column
    private String currency; // 상품가격통화
    @Column
    private Float amount; // 상품금액
    @Column
    private String orderNum; // 상품주문정보
    @Column
    @NotNull
    private String buyingLocation; // 상품구입처
    @Column
    @NotNull
    private String paymentMethod; // 결제수단
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date releaseDate; // 발매일자
    @Column
    private Integer shippingInfoId; // 배송정보ID

    @Column
    private String memo; // 메모

    public Integer getContentListId() {
        return contentListId;
    }

    public void setContentListId(Integer contentListId) {
        this.contentListId = contentListId;
    }

    public contentType getContentTypeCd() {
        return contentTypeCd;
    }

    public void setContentTypeCd(String contentTypeCd) {
        try {
            this.contentTypeCd = contentType.valueOf(contentTypeCd);
        } catch( IllegalArgumentException | NullPointerException e ) {
            this.contentTypeCd = null;
        }
    }

    /** contentTypeCd 를 String 에서 enum 으로 변환
     * 
     * @param contentTypeCd 변환하고자 하는 contentTypeCd
     * @return 변환된 contentType enum, 오류시 null
     */
    public static contentType convertContentTypeCd(String contentTypeCd) {
        
        // 입력여부 체크
        if( contentTypeCd.length() == 0 )
            return null; // 값이 없으면 null
        else if( contentTypeCd.toLowerCase().equals("all") )
            return contentType.valueOf(contentTypeCd); // all 일 경우 첫 문자를 대문자로 하지 않고 반환
        else
            contentTypeCd = contentTypeCd.toLowerCase(); // 그 외 값은 소문자 처리 후 아래 과정
        
        contentType rtnValue;
        try {
            // contentType 이 전체(all) 가 아닐 경우, enum 포멧에 맞게 조정
            contentTypeCd = contentTypeCd.substring(0, 1).concat( contentTypeCd.substring(1) );
            rtnValue = contentType.valueOf(contentTypeCd);
        } catch( IllegalArgumentException | NullPointerException e ) {
            rtnValue = null;
        }
        return rtnValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getContentNote() {
        return contentNote;
    }

    public void setContentNote(String contentNote) {
        this.contentNote = contentNote;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getXrateYn() {
        return xrateYn;
    }

    public void setXrateYn(String xrateYn) {
        this.xrateYn = xrateYn;
    }

    public String getWriteReviewYn() {
        return writeReviewYn;
    }

    public void setWriteReviewYn(String writeReviewYn) {
        this.writeReviewYn = writeReviewYn;
    }

    public String getReViewYn() {
        return reViewYn;
    }

    public void setReViewYn(String reViewYn) {
        this.reViewYn = reViewYn;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getContentBlogUrl() {
        return contentBlogUrl;
    }

    public void setContentBlogUrl(String contentBlogUrl) {
        this.contentBlogUrl = contentBlogUrl;
    }

    public String getSalesCountry() {
        return salesCountry;
    }

    public void setSalesCountry(String salesCountry) {
        this.salesCountry = salesCountry;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getBuyingLocation() {
        return buyingLocation;
    }

    public void setBuyingLocation(String buyingLocation) {
        this.buyingLocation = buyingLocation;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getShippingInfoId() {
        return shippingInfoId;
    }

    public void setShippingInfoId(Integer shippingInfoId) {
        this.shippingInfoId = shippingInfoId;
    }
    
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "ContentList [amount=" + amount + ", artist=" + artist + ", buyingLocation=" + buyingLocation
                + ", conditionType=" + conditionType + ", contentBlogUrl=" + contentBlogUrl + ", contentListId="
                + contentListId + ", contentNote=" + contentNote + ", contentTypeCd=" + contentTypeCd + ", currency="
                + currency + ", genre=" + genre + ", memo=" + memo + ", orderNum=" + orderNum + ", paymentMethod="
                + paymentMethod + ", reViewYn=" + reViewYn + ", receiptDate=" + receiptDate + ", releaseDate="
                + releaseDate + ", salesCountry=" + salesCountry + ", shippingInfoId=" + shippingInfoId + ", title="
                + title + ", version=" + version + ", writeReviewYn=" + writeReviewYn + ", xrateYn=" + xrateYn + "]";
    }

}