package by.undrul.xmlTask.entity;

import java.util.Objects;

public class Sirup extends Medicine{
    private int volume;

    public Sirup(String id, String name, String pharm, MedicineGroup group,
                 String analog, Certificate certificate, int price, int volume) {
        super(id, name, pharm, group, analog, certificate, price);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sirup sirup = (Sirup) o;
        return volume == sirup.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume);
    }

    @Override
    public String toString() {
        return "Sirup{" +
                "volume=" + volume +
                '}';
    }
}
