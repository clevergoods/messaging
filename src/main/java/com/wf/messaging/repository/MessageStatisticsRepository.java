package com.wf.messaging.repository;

import com.wf.messaging.domain.MessageStatistics;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MessageStatisticsRepository extends PagingAndSortingRepository<MessageStatistics, Long> {
}
