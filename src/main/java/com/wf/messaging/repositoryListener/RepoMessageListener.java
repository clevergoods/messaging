package com.wf.messaging.repositoryListener;

import com.wf.messaging.domain.Message;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class RepoMessageListener {

    @PrePersist
    @PreUpdate
    public void beforeSave(Message entity) {
        entity.setDeleted(false);
    }
}
