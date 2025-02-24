package Task.Roxiler.controller;


import Task.Roxiler.dto.TransactionResponse;
import Task.Roxiler.model.Product;
import Task.Roxiler.service.TransactionService;
import Task.Roxiler.utils.MonthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<TransactionResponse<Product>> getTransactions(
            @RequestParam String month,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPage
    ) {
        int monthNumber = MonthConverter.convert(month);
        return ResponseEntity.ok(
                transactionService.getTransactions(
                        monthNumber,
                        search != null ? search : "",
                        page,
                        perPage
                )
        );
    }
}