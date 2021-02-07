package net.ddns.scvstorage.scvInfoManager.entity.memo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/** 공통코드관리 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class CommonCdList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer commonCdListId; // 공통코드관리ID

    @Column(unique = true)
    @NotNull
    private String cdKind; // 공통코드 구분
    
    @Column(unique = true)
    @NotNull
    private String cd; // 공통코드

    @Column
    @NotNull
    private String cdNm; // 공통코드명

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CommonCdList [cd=" + cd + ", cdKind=" + cdKind + ", cdNm=" + cdNm + ", commonCdListId=" + commonCdListId
                + ", note=" + note + "]";
    }

}