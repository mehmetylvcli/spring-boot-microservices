package com.mehmetyalvacli.microservice.controller;

import com.mehmetyalvacli.microservice.models.TransactionLog;
import com.mehmetyalvacli.microservice.service.TaxCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tax-calculate")
public class TaxCalculateController {

    @Autowired
    TaxCalculatorService taxCalculatorService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionLog> calculateTaxByProductId(@PathVariable Long id) {

        TransactionLog transactionLog = taxCalculatorService.getProductById(id);

        return ResponseEntity.ok(transactionLog);
    }

}
