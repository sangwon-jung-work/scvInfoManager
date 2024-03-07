package net.ddns.scvstorage.scvInfoManager.entity.buy;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

/** 디지털음원 구입정보 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class DigitalContentList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer digitalContentListId; // 디지털음원구입정보ID

    @Column
    @NotNull
    private String title; // 제목
    @Column
    @NotNull
    private String artist; // 아티스트
    @Column
    @NotNull
    private String album; // 앨범

    @Column
    @NotNull
    private String format; // 포멧
    @Column
    @NotNull
    private int sampleRate; // 샘플링레이트
    @Column
    @NotNull
    private int bitDepth; // 비트
    @Column
    private int bitrate; // 비트레이트

    @Column
    @NotNull
    private Float size; // 용량(MB)
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private Date buyingDate; // 구입일자
    @Column
    private String currency; // 통화
    @Column
    private Float amount; // 가격
    @Column
    @NotNull
    private String buyingLocation; // 구입처
    @Column
    @NotNull
    private String releaseLabel; // 레이블
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date releaseDate; // 발매일자

    @Column
    private String memo; // 메모

    public Integer getDigitalContentListId() {
        return digitalContentListId;
    }

    public void setDigitalContentListId(Integer digitalContentListId) {
        this.digitalContentListId = digitalContentListId;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getBitDepth() {
        return bitDepth;
    }

    public void setBitDepth(int bitDepth) {
        this.bitDepth = bitDepth;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Date getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
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

    public String getBuyingLocation() {
        return buyingLocation;
    }

    public void setBuyingLocation(String buyingLocation) {
        this.buyingLocation = buyingLocation;
    }

    public String getReleaseLabel() {
        return releaseLabel;
    }

    public void setReleaseLabel(String releaseLabel) {
        this.releaseLabel = releaseLabel;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "DigitalContentList [album=" + album + ", amount=" + amount + ", artist=" + artist + ", bitDepth="
                + bitDepth + ", bitrate=" + bitrate + ", buyingDate=" + buyingDate + ", buyingLocation="
                + buyingLocation + ", currency=" + currency + ", digitalContentListId=" + digitalContentListId
                + ", format=" + format + ", memo=" + memo + ", releaseDate=" + releaseDate + ", releaseLabel="
                + releaseLabel + ", sampleRate=" + sampleRate + ", size=" + size + ", title=" + title + "]";
    }
    
}