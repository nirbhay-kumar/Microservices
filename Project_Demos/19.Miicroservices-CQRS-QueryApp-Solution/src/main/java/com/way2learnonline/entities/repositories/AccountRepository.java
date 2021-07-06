package com.way2learnonline.entities.repositories;

import org.springframework.data.repository.CrudRepository;

import com.way2learnonline.entities.AccountQueryEntity;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
