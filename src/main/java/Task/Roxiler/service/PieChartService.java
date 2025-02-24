package Task.Roxiler.service;


import Task.Roxiler.dto.PieChartEntry;
import Task.Roxiler.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PieChartService {

    @Autowired
    private ProductRepository productRepository;

    public List<PieChartEntry> getPieChartData(int month) {
        return productRepository.getCategoryCounts(month).stream()
                .map(obj -> new PieChartEntry((String) obj[0], (Long) obj[1]))
                .collect(Collectors.toList());
    }
}