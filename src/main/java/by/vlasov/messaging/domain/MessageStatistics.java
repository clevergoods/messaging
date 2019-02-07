package by.vlasov.messaging.domain;

import by.vlasov.messaging.repositoryListener.RepoMessageStatisticsListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(RepoMessageStatisticsListener.class)
@Table(name = "messages_statistics")
@SQLDelete(sql = "UPDATE messages_statistics SET deleted = true WHERE id = ?")
@Loader(namedQuery = "findMessageStatisticsById")
@NamedQuery(name = "findMessageStatisticsById", query = "SELECT m FROM MessageStatistics m WHERE m.id = ?1 AND m.deleted = false")
@Where(clause = "deleted = false")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageStatistics implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "messages_statistics_seq", sequenceName = "messages_statistics_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_statistics_seq")
    private Long id;

    @Column(name = "received_date")
    private Date receivedDate;

    @Column(name = "processing_time_ms")
    private Long proccessingTime;

    @Column(name = "processed_date")
    private Date processedDate;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "deleted")
    private Boolean deleted;
}
