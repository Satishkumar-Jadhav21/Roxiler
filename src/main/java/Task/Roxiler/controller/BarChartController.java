package Task.Roxiler.controller;


import Task.Roxiler.dto.BarChartEntry;
import Task.Roxiler.service.BarChartService;
import Task.Roxiler.utils.MonthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BarChartController {

    @Autowired private BarChartService barChartService;

    @GetMapping("/bar-chart")
    public ResponseEntity<List<BarChartEntry>> getBarChart(
            @RequestParam String month
    ) {
        return ResponseEntity.ok(
                barChartService.getBarChartData(MonthConverter.convert(month))
        );
    }
}