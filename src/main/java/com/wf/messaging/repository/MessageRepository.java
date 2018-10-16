package com.wf.messaging.repository;

import com.wf.messaging.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {
}
