package Task.Roxiler.controller;


import Task.Roxiler.dto.PieChartEntry;
import Task.Roxiler.service.PieChartService;
import Task.Roxiler.utils.MonthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PieChartController {

    @Autowired private PieChartService pieChartService;

    @GetMapping("/pie-chart")
    public ResponseEntity<List<PieChartEntry>> getPieChart(
            @RequestParam String month
    ) {
        return ResponseEntity.ok(
                pieChartService.getPieChartData(MonthConverter.convert(month))
        );
    }
}