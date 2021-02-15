package net.ddns.scvstorage.scvInfoManager.repository.memo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.ddns.scvstorage.scvInfoManager.entity.memo.LocationCdList;
import net.ddns.scvstorage.scvInfoManager.repository.common.ComboData;

public interface LocationCdListRepository extends CrudRepository<LocationCdList, Integer> {
    
    // 방위치 정보를 콤보 생성용으로 조회한다
    @Query(value = "SELECT a.location_cd AS code, a.location_desc AS codename "
            + "FROM location_cd_list a ORDER BY a.location_cd DESC ", nativeQuery = true)
    List<ComboData> getLocationCdData();

    // 기 생성된 코드정보를 콤보 생성용으로 조회한다
    @Query(value = "SELECT a.location_cd AS code, a.location_cd AS codename "
            + "FROM location_cd_list a GROUP BY a.location_cd ", nativeQuery = true)
    List<ComboData> getLocationCdList();
}