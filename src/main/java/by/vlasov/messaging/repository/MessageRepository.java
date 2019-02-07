package by.vlasov.messaging.repository;

import by.vlasov.messaging.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    Message findByUuid(String uuid);

    Page<Message> findAll(Pageable pageable);
}
