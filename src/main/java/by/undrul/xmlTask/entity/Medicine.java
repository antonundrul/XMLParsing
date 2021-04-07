package by.undrul.xmlTask.entity;

import java.util.Objects;

public class Medicine {
    private String id;
    private String name;
    private String pharm;
    private MedicineGroup group;
    private String analog;
    private Certificate certificate;
    private int price;

    public Medicine(String id, String name, String pharm,
                    MedicineGroup group, String analog, Certificate certificate, int price) {
        this.id = id;
        this.name = name;
        this.pharm = pharm;
        this.group = group;
        this.analog = analog;
        this.certificate = certificate;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public MedicineGroup getGroup() {
        return group;
    }

    public void setGroup(MedicineGroup group) {
        this.group = group;
    }

    public String getAnalog() {
        return analog;
    }

    public void setAnalog(String analog) {
        this.analog = analog;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return price == medicine.price &&
                id.equals(medicine.id) &&
                name.equals(medicine.name) &&
                pharm.equals(medicine.pharm) &&
                group == medicine.group &&
                analog.equals(medicine.analog) &&
                certificate.equals(medicine.certificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pharm, group, analog, certificate, price);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pharm='" + pharm + '\'' +
                ", group=" + group +
                ", analog='" + analog + '\'' +
                ", certificate=" + certificate +
                ", price=" + price +
                '}';
    }
}
