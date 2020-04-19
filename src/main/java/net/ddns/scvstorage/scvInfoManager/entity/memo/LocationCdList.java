package net.ddns.scvstorage.scvInfoManager.entity.memo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/** 방위치관리 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
public class LocationCdList {

    @Id
    private Integer locationCdListId; // 방위치관리ID

    @Column(unique = true)
    private String locationCd; // 방위치 코드
    
    @Column
    private String locationDesc; // 방위치 설명(주소)
    @Column
    private String note; // 비고

    public Integer getLocationCdListId() {
        return locationCdListId;
    }

    public void setLocationCdListId(Integer locationCdListId) {
        this.locationCdListId = locationCdListId;
    }

    public String getLocationCd() {
        return locationCd;
    }

    public void setLocationCd(String locationCd) {
        this.locationCd = locationCd;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "LocationCdList [locationCd=" + locationCd + ", locationCdListId=" + locationCdListId + ", locationDesc="
                + locationDesc + ", note=" + note + "]";
    }

}