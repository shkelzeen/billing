package com.billing.dataaccess.repository;

import com.billing.dataaccess.entity.bill.BillEntity;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<BillEntity, Long> {
}
