package by.vlasov.messaging.repositoryListener;

import by.vlasov.messaging.domain.Message;
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
