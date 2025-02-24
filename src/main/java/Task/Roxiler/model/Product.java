package Task.Roxiler.model;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    private Long id;
//    private String title;
//    private String description;
    private BigDecimal price;
//    private String category;

    @Column(name = "date_of_sale")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfSale;

    private Boolean sold;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(length = 2000)
    private String title;

    @Column(length = 2000)
    private String category;
}
