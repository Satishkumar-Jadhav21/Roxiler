package Task.Roxiler.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticsResponse {
    private Double totalSaleAmount;
    private Long totalSoldItems;
    private Long totalUnsoldItems;
}