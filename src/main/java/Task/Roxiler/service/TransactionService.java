package Task.Roxiler.service;


import Task.Roxiler.dto.TransactionResponse;
import Task.Roxiler.model.Product;
import Task.Roxiler.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private ProductRepository productRepository;
    public TransactionResponse<Product> getTransactions(int month, String search, int page, int perPage) {
        PageRequest pageable = PageRequest.of(page - 1, perPage);
        Page<Product> pageResult = productRepository.findByMonthAndSearch(
                month,
                search.toLowerCase(),
                pageable
        );
        return new TransactionResponse<>(pageResult);
    }

}
