package com.way2learnonline.services.queries;

import java.util.List;

import com.way2learnonline.entities.AccountQueryEntity;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
    public AccountQueryEntity getAccount(String accountNumber);
}
