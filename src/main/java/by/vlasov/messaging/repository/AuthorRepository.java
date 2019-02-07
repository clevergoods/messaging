package by.vlasov.messaging.repository;

import by.vlasov.messaging.domain.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
    Author findByAuthorName(String name);
}
