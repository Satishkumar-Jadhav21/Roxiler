package Task.Roxiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CombinedResponse {
    private StatisticsResponse statistics;
    private List<BarChartEntry> barChart;
    private List<PieChartEntry> pieChart;
}