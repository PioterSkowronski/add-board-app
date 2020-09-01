package pl.skowronski.addboardapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skowronski.addboardapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
