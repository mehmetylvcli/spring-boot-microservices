package com.mehmetyalvacli.microservice.service;

import com.mehmetyalvacli.microservice.models.ProductDto;
import com.mehmetyalvacli.microservice.models.TransactionLog;
import com.mehmetyalvacli.microservice.repository.TransactionLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class TaxCalculatorService {

    @Autowired
    private TransactionLogsRepository transactionLogsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.url.user-product-service}")
    String userProductServiceUrl;

    public TransactionLog getProductById(Long productId) {
        String url = userProductServiceUrl + "products/get/" + productId;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ProductDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ProductDto.class
        );

        ProductDto productDto = Objects.requireNonNull(response.getBody());
        double taxPrice = calculateTax(productDto);

        return saveTransactionLog(productDto.getId(),taxPrice);
    }

    public TransactionLog saveTransactionLog(Long productId, Double taxPrice) {
        TransactionLog transactionLog = new TransactionLog();

        transactionLog.setProductId(productId);
        transactionLog.setTaxPrice(taxPrice);
        transactionLog.setTransactionTime(new Timestamp(System.currentTimeMillis()));
        return transactionLogsRepository.save(transactionLog);
    }

    public double calculateTax(ProductDto productDto) {
        return productDto.getPrice() * (productDto.getTaxRate() / 100);
    }

    public double calculateTotalPrice(ProductDto productDto) {
        double taxAmount = calculateTax(productDto);
        return productDto.getPrice() + taxAmount;
    }

}

