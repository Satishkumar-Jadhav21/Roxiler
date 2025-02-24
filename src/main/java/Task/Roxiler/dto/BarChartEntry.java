package Task.Roxiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BarChartEntry {
    private String range;
    private Long count;
}
