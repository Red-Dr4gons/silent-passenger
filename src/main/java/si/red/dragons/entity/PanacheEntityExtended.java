package si.red.dragons.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;

public class PanacheEntityExtended extends PanacheEntityBase {

    public PanacheEntityExtended() {

    }

    public void save() {
        JpaOperations.getEntityManager().merge(this);
    }
}
