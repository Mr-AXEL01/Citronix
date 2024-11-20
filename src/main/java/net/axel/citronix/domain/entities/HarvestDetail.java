package net.axel.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.axel.citronix.domain.embeddeds.HarvestDetailKey;

import java.io.Serializable;

@Entity
@Table(name = "harvest_details")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HarvestDetail implements Serializable {

    @EmbeddedId
    private HarvestDetailKey id;

    @ManyToOne
    @MapsId("treeId")
    @JoinColumn(name = "tree_id", nullable = false)
    private Tree tree;

    @ManyToOne
    @MapsId("harvestId")
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    private Double quantity;
}
