package net.axel.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "sales")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double unitPrice;

    private String client;

    @Transient
    private Double income;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    public Double getIncome() {
        if (harvest != null && harvest.getTotalQuantity() != null && unitPrice != null) {
            return harvest.getTotalQuantity() * unitPrice;
        }
        return null;
    }

}
