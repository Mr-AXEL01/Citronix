package net.axel.citronix.domain.embeddeds;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record HarvestDetailKey(
        @Column(name = "tree_id") Long treeId,
        @Column(name = "harvest_id") Long harvestId
) {
}
