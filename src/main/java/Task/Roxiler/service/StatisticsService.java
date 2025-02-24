package Task.Roxiler.service;


import Task.Roxiler.dto.StatisticsResponse;
import  Task.Roxiler.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    private ProductRepository productRepository;

    public StatisticsResponse getStatistics(int month) {
        Double totalSale = productRepository.getTotalSaleAmount(month) != null ?
                productRepository.getTotalSaleAmount(month) : 0.0;
        Long soldItems = productRepository.countSold(month) != null ?
                productRepository.countSold(month) : 0L;
        Long unsoldItems = productRepository.countUnsold(month) != null ?
                productRepository.countUnsold(month) : 0L;

        return new StatisticsResponse(totalSale, soldItems, unsoldItems);
    }
}
