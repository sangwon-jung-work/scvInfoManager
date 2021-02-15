package net.ddns.scvstorage.scvInfoManager.repository.buy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList;
import net.ddns.scvstorage.scvInfoManager.repository.common.ComboData;

public interface ContentListRepository extends CrudRepository<ContentList, Integer> {

    Iterable<ContentList> findByContentTypeCd(String contentTypeCd);

    Iterable<ContentList> findByContentTypeCdAndContentListId(String contentTypeCd, Integer contentListId);

    // 사전 기록되어 있던 장르 조회(Distinct)
    @Query(value = "SELECT c.genre AS code, c.genre AS codename " 
            + "FROM content_list AS c GROUP BY c.genre", nativeQuery = true)
    List<ComboData> getGenre();

    // 사전 기록되어 있던 상품발매국가 조회(Distinct)
    @Query(value = "SELECT c.sales_country AS code, c.sales_country AS codename " 
            + "FROM content_list AS c GROUP BY c.sales_country", nativeQuery = true)
    List<ComboData> getSalesCountry();

    // 사전 기록되어 있던 상품종류 조회(Distinct)
    @Query(value = "SELECT c.condition_type AS code, c.condition_type AS codename "
            + "FROM content_list AS c GROUP BY c.condition_type", nativeQuery = true)
    List<ComboData> getConditionType();

    // 사전 기록되어 있던 상품가격통화 조회(Distinct)
    @Query(value = "SELECT c.currency AS code, c.currency AS codename "
            + "FROM content_list AS c GROUP BY c.currency", nativeQuery = true)
    List<ComboData> getCurrency();

    // 사전 기록되어 있던 상품구입처 조회(Distinct)
    @Query(value = "SELECT c.buying_location AS code, c.buying_location AS codename "
            + "FROM content_list AS c GROUP BY c.buying_location", nativeQuery = true)
    List<ComboData> getBuyingLocation();

    // 사전 기록되어 있던 결제수단 조회(Distinct)
    @Query(value = "SELECT c.payment_method AS code, c.payment_method AS codename "
            + "FROM content_list AS c GROUP BY c.payment_method", nativeQuery = true)
    List<ComboData> getPaymentMethod();

    //Page<ContentList> findAllPage(Pageable pageable);

    //Page<ContentList> findByContentTypeCdPage(String contentTypeCd, Pageable pageable);
}