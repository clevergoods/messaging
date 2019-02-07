package by.vlasov.messaging.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import by.vlasov.messaging.repositoryListener.RepoMessageListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@EntityListeners(RepoMessageListener.class)
@Table(name = "messages")
@SQLDelete(sql = "UPDATE messages SET deleted = true WHERE id = ?")
@Loader(namedQuery = "findMessageById")
@NamedQuery(name = "findMessageById", query = "SELECT m FROM Message m WHERE m.id = ?1 AND m.deleted = false")
@Where(clause = "deleted = false")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "messages_seq", sequenceName = "messages_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_seq")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private MessageType type;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "data")
    private String data;

    @Column(name = "organization")
    private String organization;

    @JsonIgnore
    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "metainformation")
    @Convert(converter = MetainformationJsonConverter.class)
    private Metainformation metainformation;

    @JsonIgnore
    @OneToOne(orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "statistics_id")
    private MessageStatistics statistics;
}
