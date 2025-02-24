package Task.Roxiler.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class TransactionResponse<T> {
    private List<T> content;
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;

    public TransactionResponse(Page<T> page) {
        this.content = page.getContent();
        this.currentPage = page.getNumber() + 1; // Spring Data pages are 0-based
        this.pageSize = page.getSize();
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}