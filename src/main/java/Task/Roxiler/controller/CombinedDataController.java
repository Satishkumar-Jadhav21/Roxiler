package Task.Roxiler.controller;


import Task.Roxiler.dto.CombinedResponse;
import Task.Roxiler.service.CombinedDataService;
import Task.Roxiler.utils.MonthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombinedDataController {

    @Autowired private CombinedDataService combinedDataService;

    @GetMapping("/combined")
    public ResponseEntity<CombinedResponse> getCombinedData(
            @RequestParam String month
    ) {
        return ResponseEntity.ok(
                combinedDataService.getCombinedData(MonthConverter.convert(month))
        );
    }
}