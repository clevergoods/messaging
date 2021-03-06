package by.vlasov.messaging.repository;

import by.vlasov.messaging.domain.MessageType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MessageTypeRepository extends PagingAndSortingRepository<MessageType, Long> {
    MessageType findByTypeName(String type);
}
