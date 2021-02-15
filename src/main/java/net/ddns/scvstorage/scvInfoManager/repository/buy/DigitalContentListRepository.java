package net.ddns.scvstorage.scvInfoManager.repository.buy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.ddns.scvstorage.scvInfoManager.entity.buy.DigitalContentList;
import net.ddns.scvstorage.scvInfoManager.repository.common.ComboData;

public interface DigitalContentListRepository extends CrudRepository<DigitalContentList, Integer> {

    // 사전 기록되어 있던 포멧 조회(Distinct)
    @Query(value = "SELECT c.format AS code, c.format AS codename " 
            + "FROM digital_content_list AS c GROUP BY c.format", nativeQuery = true)
    List<ComboData> getFormat();

    // 사전 기록되어 있던 통화 조회(Distinct)
    @Query(value = "SELECT c.currency AS code, c.currency AS codename "
            + "FROM digital_content_list AS c GROUP BY c.currency", nativeQuery = true)
    List<ComboData> getCurrency();

    // 사전 기록되어 있던 구입처 조회(Distinct)
    @Query(value = "SELECT c.buying_location AS code, c.buying_location AS codename "
            + "FROM digital_content_list AS c GROUP BY c.buying_location", nativeQuery = true)
    List<ComboData> getBuyingLocation();

    // 사전 기록되어 있던 레이블 조회(Distinct)
    @Query(value = "SELECT c.release_label AS code, c.release_label AS codename "
            + "FROM digital_content_list AS c GROUP BY c.release_label", nativeQuery = true)
    List<ComboData> getReleaseLabel();
}