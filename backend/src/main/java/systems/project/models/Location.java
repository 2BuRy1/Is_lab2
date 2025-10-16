package systems.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    @Id
    @SequenceGenerator(
            name = "location_seq_gen",
            sequenceName = "location_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "location_seq_gen")
    private Long id;

    @Column(nullable = false)
    private Integer x; //Поле не может быть null

    private float y;

    @Column(nullable = false)
    private Float z; //Поле не может быть null
}
