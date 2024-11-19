package net.axel.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity
@Table(name = "fields")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Field implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double area;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farm_id")
    private Farm farm;
}
