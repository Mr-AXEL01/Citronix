package net.axel.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trees")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Tree implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate plantingDate;

    private Integer age;

    private Double productivity;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();
}
