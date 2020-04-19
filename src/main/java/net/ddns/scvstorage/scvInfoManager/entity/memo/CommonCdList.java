package net.ddns.scvstorage.scvInfoManager.entity.memo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** 공통코드관리 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
public class CommonCdList {

    @Id
    private Integer commonCdListId; // 공통코드관리ID

    @Column(unique = true)
    private String cdKind; // 공통코드 구분
    
    @Column(unique = true)
    private String cd; // 공통코드

    @Column
    private String cdNm; // 공통코드명

    @Column
    @Temporal(TemporalType.DATE)
    private Date addDate; // 등록일자

    @Column
    private String note; // 비고
    
    public Integer getCommonCdListId() {
        return commonCdListId;
    }

    public void setCommonCdListId(Integer commonCdListId) {
        this.commonCdListId = commonCdListId;
    }
    
    public String getCdKind() {
        return cdKind;
    }

    public void setCdKind(String cdKind) {
        this.cdKind = cdKind;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCdNm() {
        return cdNm;
    }

    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CommonCdList [addDate=" + addDate + ", cd=" + cd + ", cdKind=" + cdKind + ", cdNm=" + cdNm
                + ", commonCdListId=" + commonCdListId + ", note=" + note + "]";
    }

}