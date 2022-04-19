package com.example.webshop.repository;

import com.example.webshop.model.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long> {
    List<BuyOrder> findAllByCustomerId(Long customerId);
}
