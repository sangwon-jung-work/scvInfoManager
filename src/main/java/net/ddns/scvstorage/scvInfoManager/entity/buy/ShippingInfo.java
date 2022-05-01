package net.ddns.scvstorage.scvInfoManager.entity.buy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/** 배송정보 테이블 구조
 *  swjung
 *  2020.04.17
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shippingInfoId; // 배송정보ID

    @Column
    private String currency; // 배송가격통화
    @Column
    private Float amount; // 배송비
    @Column
    private String orderNum; // 배송주문번호
    @Column
    @NotNull
    private String buyingLocation; // 배송처
    @Column
    @NotNull
    private String paymentMethod; // 결제수단

    @Column
    private String memo; // 메모

    public Integer getShippingInfoId() {
        return shippingInfoId;
    }

    public void setShippingInfoId(Integer shippingInfoId) {
        this.shippingInfoId = shippingInfoId;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "ShippingInfo [amount=" + amount + ", buyingLocation=" + buyingLocation + ", currency=" + currency
                + ", memo=" + memo + ", orderNum=" + orderNum + ", paymentMethod=" + paymentMethod + ", shippingInfoId="
                + shippingInfoId + "]";
    }

}