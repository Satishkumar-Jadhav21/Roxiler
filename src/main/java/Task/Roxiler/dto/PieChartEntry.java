package Task.Roxiler.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PieChartEntry {
    private String category;
    private Long count;
}