package net.axel.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farms")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Farm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Double size;

    private LocalDate creationDate;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Field> fields = new ArrayList<>();
}
