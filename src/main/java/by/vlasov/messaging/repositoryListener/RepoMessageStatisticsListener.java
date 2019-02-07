package by.vlasov.messaging.repositoryListener;

import by.vlasov.messaging.domain.MessageStatistics;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Component
public class RepoMessageStatisticsListener {

    @PrePersist
    @PreUpdate
    public void beforeSave(MessageStatistics entity) {
        Date processedDate = new Date();
        Date receivedDate = entity.getReceivedDate();
        long processingTime = processedDate.getTime() - receivedDate.getTime();
        entity.setProccessingTime(processingTime);
        entity.setProcessedDate(processedDate);
        entity.setDeleted(false);
    }
}
