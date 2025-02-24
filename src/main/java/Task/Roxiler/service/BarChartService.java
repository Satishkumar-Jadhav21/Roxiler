package Task.Roxiler.service;


import Task.Roxiler.dto.BarChartEntry;
import Task.Roxiler.model.Product;
import Task.Roxiler.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BarChartService {

    @Autowired
    private ProductRepository productRepository;

    public List<BarChartEntry> getBarChartData(int month) {
        List<Product> products = productRepository.findByMonth(month);
        long[] ranges = new long[10];

        for (Product product : products) {
            double price = product.getPrice().doubleValue();
            if (price <= 100) ranges[0]++;
            else if (price <= 200) ranges[1]++;
            else if (price <= 300) ranges[2]++;
            else if (price <= 400) ranges[3]++;
            else if (price <= 500) ranges[4]++;
            else if (price <= 600) ranges[5]++;
            else if (price <= 700) ranges[6]++;
            else if (price <= 800) ranges[7]++;
            else if (price <= 900) ranges[8]++;
            else ranges[9]++;
        }

        List<BarChartEntry> entries = new ArrayList<>();
        entries.add(new BarChartEntry("0-100", ranges[0]));
        entries.add(new BarChartEntry("101-200", ranges[1]));
        entries.add(new BarChartEntry("201-300", ranges[2]));
        entries.add(new BarChartEntry("301-400", ranges[3]));
        entries.add(new BarChartEntry("401-500", ranges[4]));
        entries.add(new BarChartEntry("501-600", ranges[5]));
        entries.add(new BarChartEntry("601-700", ranges[6]));
        entries.add(new BarChartEntry("701-800", ranges[7]));
        entries.add(new BarChartEntry("801-900", ranges[8]));
        entries.add(new BarChartEntry("901-above", ranges[9]));

        return entries;
    }
}