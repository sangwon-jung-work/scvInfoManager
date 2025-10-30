package net.ddns.scvstorage.scvInfoManager.repository.memo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.ddns.scvstorage.scvInfoManager.entity.memo.CommonCdList;
import net.ddns.scvstorage.scvInfoManager.repository.common.ComboData;

public interface CommonCdListRepository extends CrudRepository<CommonCdList, Integer> {

    // 기 생성된 구분코드를 조회한다
    @Query(value = "SELECT a.cd_kind AS code, a.cd_kind AS codename "
    + "FROM common_cd_list a GROUP BY a.cd_kind ORDER BY a.cd_kind ", nativeQuery = true)
    List<ComboData> getCdKindList();

    // 공통코드 값을 콤보 생성용으로 조회한다
    @Query(value = "SELECT a.cd AS code, CONCAT(a.cd,' ',a.cd_nm) AS codename "
            + "FROM common_cd_list a WHERE a.cd_kind = :cdKind ORDER BY a.cd ", nativeQuery = true)
    List<ComboData> getCommonCdData( @Param("cdKind") String cdKind );

}