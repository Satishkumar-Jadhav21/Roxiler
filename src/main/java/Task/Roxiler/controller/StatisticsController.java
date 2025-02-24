package Task.Roxiler.controller;


import Task.Roxiler.dto.StatisticsResponse;
import Task.Roxiler.service.StatisticsService;
import Task.Roxiler.utils.MonthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics(
            @RequestParam String month
    ) {
        int monthNumber = MonthConverter.convert(month);
        return ResponseEntity.ok(statisticsService.getStatistics(monthNumber));
    }
}
