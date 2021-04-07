package by.undrul.xmlTask.entity;

import java.util.Objects;

public class Pill extends Medicine{
    private int amount;
    private int dosage;

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
        return Objects.hash(super.hashCode(), amount, dosage);
    }

    @Override
    public String toString() {
        return "Pill{" +
                "amount=" + amount +
                ", dosage=" + dosage +
                '}';
    }
}
