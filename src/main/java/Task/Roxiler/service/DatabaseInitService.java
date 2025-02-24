package Task.Roxiler.service;

import Task.Roxiler.model.Product;
import Task.Roxiler.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DatabaseInitService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitService.class);
    private static final int MAX_TITLE_LENGTH = 1000;
    private static final int MAX_CATEGORY_LENGTH = 1000;
    private static final int MAX_DESCRIPTION_LENGTH = 65535; // TEXT column max

    @Autowired
    private ProductRepository productRepository;

    public void initializeDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        Product[] products = restTemplate.getForObject(
                "https://s3.amazonaws.com/roxiler.com/product_transaction.json",
                Product[].class
        );

        if (products != null) {
            Arrays.stream(products).forEach(this::sanitizeProduct);

            try {
                productRepository.saveAll(List.of(products));
                logger.info("Successfully initialized database with {} products", products.length);
            } catch (Exception e) {
                logger.error("Database initialization failed: {}", e.getMessage());
                throw new RuntimeException("Database initialization failed", e);
            }
        }
    }

    private void sanitizeProduct(Product product) {
        // Truncate fields to match database column sizes
        if (product.getTitle() != null) {
            product.setTitle(truncate(product.getTitle(), MAX_TITLE_LENGTH));
        }
        if (product.getCategory() != null) {
            product.setCategory(truncate(product.getCategory(), MAX_CATEGORY_LENGTH));
        }
        if (product.getDescription() != null) {
            product.setDescription(truncate(product.getDescription(), MAX_DESCRIPTION_LENGTH));
        }

        // Ensure price has exactly 2 decimal places
        if (product.getPrice() != null) {
            product.setPrice(product.getPrice().setScale(2, RoundingMode.HALF_UP));
        }
    }

    private String truncate(String value, int maxLength) {
        return value.length() > maxLength ? value.substring(0, maxLength) : value;
    }
}