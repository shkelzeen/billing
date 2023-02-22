package com.billing.dataaccess.repsitory;

import com.billing.dataaccess.entity.bill.BillEntity;
import com.billing.dataaccess.repository.BillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @Test
    public void testSaveBill() {
        BillEntity billEntity = new BillEntity();
        BillEntity savedBillEntity = billRepository.save(billEntity);
        assertNotNull(savedBillEntity);
        assertNotNull(savedBillEntity.getId());
        assertNotNull(savedBillEntity.getCreated());
        assertNotNull(savedBillEntity.getLastUpdate());
    }
}
