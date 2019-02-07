package by.vlasov.messaging.repository;

import by.vlasov.messaging.domain.MessageStatistics;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MessageStatisticsRepository extends PagingAndSortingRepository<MessageStatistics, Long> {
}
