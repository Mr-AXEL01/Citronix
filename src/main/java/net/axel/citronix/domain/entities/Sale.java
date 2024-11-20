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

    private Double UnitPrice;

    private String client;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;
}
