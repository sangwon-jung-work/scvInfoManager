package net.ddns.scvstorage.scvInfoManager.entity.memo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/** 메모기록 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
public class MemoTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer memoTimeId; // 메모기록ID

    @Column(unique = true)
    private String roomLocationCd; // 방위치코드
    @Column(unique = true)
    private String memoTypeCd; // 메모대상(종류) 코드
    @Column(unique = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memoDate; // 메모일자
    
    @Column
    private String note; // 비고

    public Integer getMemoTimeId() {
        return memoTimeId;
    }

    public void setMemoTimeId(Integer memoTimeId) {
        this.memoTimeId = memoTimeId;
    }
    
    public String getRoomLocationCd() {
        return roomLocationCd;
    }

    public void setRoomLocationCd(String roomLocationCd) {
        this.roomLocationCd = roomLocationCd;
    }

    public String getMemoTypeCd() {
        return memoTypeCd;
    }

    public void setMemoTypeCd(String memoTypeCd) {
        this.memoTypeCd = memoTypeCd;
    }

    public Date getMemoDate() {
        return memoDate;
    }

    public void setMemoDate(Date memoDate) {
        this.memoDate = memoDate;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "MemoTime [memoDate=" + memoDate + ", memoTimeId=" + memoTimeId + ", memoTypeCd=" + memoTypeCd
                + ", note=" + note + ", roomLocationCd=" + roomLocationCd + "]";
    }

}