package net.ddns.scvstorage.scvInfoManager.repository.buy;

import org.springframework.data.repository.CrudRepository;

import net.ddns.scvstorage.scvInfoManager.entity.buy.ContentList;

public interface ContentListRepository extends CrudRepository<ContentList, Integer> {

    Iterable<ContentList> findByContentTypeCd(String contentTypeCd);
}