package com.currencyconverter.demo.repository;

import com.currencyconverter.demo.entity.ConversionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {
    // JpaRepository already gives us save(), findAll(), FindById(), deleteById(), etc.
    // We'll add custom query methods here later if needed (e.g. filter by currency)

}

    

