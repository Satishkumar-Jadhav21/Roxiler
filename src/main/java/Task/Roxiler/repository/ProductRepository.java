package Task.Roxiler.repository;

import Task.Roxiler.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "MONTH(p.dateOfSale) = :month AND " +
            "(LOWER(p.title) LIKE %:search% OR " +
            "LOWER(p.description) LIKE %:search% OR " +
            "CAST(p.price AS string) LIKE %:search%)")
    Page<Product> findByMonthAndSearch(
            @Param("month") int month,
            @Param("search") String search,
            Pageable pageable
    );

    @Query("SELECT SUM(p.price) FROM Product p WHERE MONTH(p.dateOfSale) = :month AND p.sold = true")
    Double getTotalSaleAmount(@Param("month") int month);

    @Query("SELECT COUNT(p) FROM Product p WHERE MONTH(p.dateOfSale) = :month AND p.sold = true")
    Long countSold(@Param("month") int month);

    @Query("SELECT COUNT(p) FROM Product p WHERE MONTH(p.dateOfSale) = :month AND p.sold = false")
    Long countUnsold(@Param("month") int month);

    @Query("SELECT p.category, COUNT(p) FROM Product p WHERE MONTH(p.dateOfSale) = :month GROUP BY p.category")
    List<Object[]> getCategoryCounts(@Param("month") int month);

    @Query("SELECT p FROM Product p WHERE MONTH(p.dateOfSale) = :month")
    List<Product> findByMonth(@Param("month") int month);
}
