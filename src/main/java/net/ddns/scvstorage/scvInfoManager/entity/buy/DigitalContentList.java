package net.ddns.scvstorage.scvInfoManager.entity.buy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** 디지털음원 구입정보 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
public class DigitalContentList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer digitalContentListId; // 디지털음원구입정보ID

    @Column
    private String title; // 제목
    @Column
    private String artist; // 아티스트
    @Column
    private String album; // 앨범

    @Column
    private String format; // 포멧
    @Column
    private int sampleRate; // 샘플링레이트
    @Column
    private int bitDepth; // 비트
    @Column
    private int bitrate; // 비트레이트

    @Column
    private float size; // 용량(MB)
    @Column
    @Temporal(TemporalType.DATE)
    private Date buyingDate; // 구입일자
    @Column
    private String currency; // 통화
    @Column
    private int amount; // 가격
    @Column
    private String buyingLocation; // 구입처
    @Column
    private String releaseLabel; // 레이블
    @Column
    @Temporal(TemporalType.DATE)
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

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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