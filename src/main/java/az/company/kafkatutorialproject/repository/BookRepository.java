package az.company.kafkatutorialproject.repository;

import az.company.kafkatutorialproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}