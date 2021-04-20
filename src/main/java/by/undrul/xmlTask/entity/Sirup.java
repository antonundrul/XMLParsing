package by.undrul.xmlTask.entity;

public class Sirup extends AbstractMedicine {
    private int volume;

    public Sirup() {
    }

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

        int prime = 31;
        int result = 1;

        result = result * prime + super.hashCode();
        result = result * prime + Integer.hashCode(volume);

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sirup{\n");
        sb.append(" volume : ").append(volume).append('\n');
        sb.append(super.toString());
        return sb.toString();
    }
}
