package by.undrul.xmlTask.entity;

import java.util.Objects;

public class Pill extends AbstractMedicine {
    private int amount;
    private int dosage;

    public Pill() {
        super();
    }

    public Pill(String id, String name, String pharm, MedicineGroup group,
                String analog, Certificate certificate, int price, int amount, int dosage) {
        super(id, name, pharm, group, analog, certificate, price);
        this.amount = amount;
        this.dosage = dosage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pill pill = (Pill) o;
        return amount == pill.amount &&
                dosage == pill.dosage;
    }

    @Override
    public int hashCode() {

        int prime = 31;
        int result = 1;

        result = result * prime + super.hashCode();
        result = result * prime + Integer.hashCode(amount);
        result = result * prime + Integer.hashCode(dosage);

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pill{\n");
        sb.append(" amount : ").append(amount).append('\n');
        sb.append(" dosage : ").append(dosage).append('\n');
        sb.append(super.toString());
        return sb.toString();
    }
}
