package by.undrul.xmlTask.builder;

import by.undrul.xmlTask.entity.AbstractMedicine;
import by.undrul.xmlTask.exception.MedicineException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMedicineBuilder {
    protected Set<AbstractMedicine> medicins;

    public AbstractMedicineBuilder() {
        medicins = new HashSet<>();
    }

    public AbstractMedicineBuilder(Set<AbstractMedicine> medicins) {
        this.medicins = medicins;
    }

    public Set<AbstractMedicine> getMedicins() {
        return medicins;
    }

    public abstract void buildMedicins(String xmlPath) throws MedicineException;
}
