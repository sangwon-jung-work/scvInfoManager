package net.ddns.scvstorage.scvInfoManager.repository.buy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.ddns.scvstorage.scvInfoManager.entity.buy.ShippingInfo;
import net.ddns.scvstorage.scvInfoManager.repository.common.ComboData;

public interface ShippingInfoRepository extends CrudRepository<ShippingInfo, Integer> {

    // 사전 기록되어 있던 배송가격통화 조회(Distinct)
    @Query(value = "SELECT c.currency AS code, c.currency AS codename "
    + "FROM shipping_info AS c GROUP BY c.currency", nativeQuery = true)
    List<ComboData> getCurrency();

    // 사전 기록되어 있던 배송처 조회(Distinct)
    @Query(value = "SELECT c.buying_location AS code, c.buying_location AS codename "
    + "FROM shipping_info AS c GROUP BY c.buying_location", nativeQuery = true)
    List<ComboData> getBuyingLocation();

    // 사전 기록되어 있던 결제수단 조회(Distinct)
    @Query(value = "SELECT c.payment_method AS code, c.payment_method AS codename "
    + "FROM shipping_info AS c GROUP BY c.payment_method", nativeQuery = true)
    List<ComboData> getPaymentMethod();

    // 사전 기록되어 있던 상품발매국가 조회(Distinct)
    @Query(value = "SELECT c.shipping_info_id AS code, c.order_num AS codename " 
            + "FROM shipping_info AS c WHERE c.order_num IS NOT NULL "
            + "ORDER BY c.shipping_info_id DESC", nativeQuery = true)
    List<ComboData> getOrderNumOrderByPK();
}