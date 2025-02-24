package Task.Roxiler.service;


import Task.Roxiler.dto.CombinedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombinedDataService {

    @Autowired private StatisticsService statisticsService;
    @Autowired private BarChartService barChartService;
    @Autowired private PieChartService pieChartService;

    public CombinedResponse getCombinedData(int month) {
        return new CombinedResponse(
                statisticsService.getStatistics(month),
                barChartService.getBarChartData(month),
                pieChartService.getPieChartData(month)
        );
    }
}