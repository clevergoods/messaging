package com.wf.messaging.repository;

import com.wf.messaging.domain.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
    Author findByAuthorName(String name);
}
