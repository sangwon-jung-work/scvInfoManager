package net.ddns.scvstorage.scvInfoManager.entity.memo;

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

import com.fasterxml.jackson.annotation.JsonFormat;

/** 메모기록 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class MemoTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memoTimeId; // 메모기록ID

    @Column(unique = true)
    @NotNull
    private String roomLocationCd; // 방위치코드
    @Column(unique = true)
    @NotNull
    private String memoTypeCd; // 메모대상(종류) 코드
    @Column(unique = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @NotNull
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