package net.ddns.scvstorage.scvInfoManager.repository;

import org.springframework.data.repository.CrudRepository;

import net.ddns.scvstorage.scvInfoManager.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}