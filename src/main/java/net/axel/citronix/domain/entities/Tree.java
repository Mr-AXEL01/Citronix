package net.axel.citronix.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Transient
    private Integer age;

    @Transient
    private Double productivity;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    public Double getProductivity(int age) {
        return getaDouble(age);
    }

    public Integer getAge(LocalDate plantingDate) {
        if (plantingDate == null) {
            throw new IllegalStateException("Planting date is required to calculate age.");
        }
        return getaInteger(plantingDate);
    }

    @NotNull
    public static Double getaDouble(int age) {
        return switch (age) {
            case 1, 2 -> 2.5;
            case 3, 4, 5, 6, 7, 8, 9, 10 -> 12.0;
            case 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> 20.0;
            default -> 0.0;
        };
    }

    @NotNull
    public static Integer getaInteger(LocalDate plantingDate) {
        return (int) ChronoUnit.YEARS.between(plantingDate, LocalDate.now());
    }
}
