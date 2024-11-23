package net.axel.citronix.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.axel.citronix.domain.embeddeds.HarvestDetailKey;
import org.hibernate.annotations.processing.HQL;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("harvestId")
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    private Double quantity;

    public HarvestDetail(Tree tree, Harvest harvest, Double quantity) {
        this.id = new HarvestDetailKey(tree.getId(), harvest.getId());
        this.tree = tree;
        this.harvest = harvest;
        this.quantity = quantity;
    }
}
