package by.vlasov.messaging.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "message_type")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageType implements Serializable {

    @Id
    @JsonIgnore
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "message_type_seq", sequenceName = "message_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_type_seq")
    private Long id;

    @Column(name = "type_name", nullable = false, unique = true)
    String typeName;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    @Cascade(CascadeType.ALL)
    private List<Message> messages;
}
