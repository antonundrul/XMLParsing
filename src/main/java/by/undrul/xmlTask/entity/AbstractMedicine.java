package by.undrul.xmlTask.entity;

public abstract class AbstractMedicine {
    private String id;
    private String pharmWebsite;
    private String name;
    private String pharm;
    private MedicineGroup group;
    private String analog;
    private Certificate certificate;
    private int price;

    public AbstractMedicine() {
    }

    public AbstractMedicine(String id, String name, String pharm,
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

    public String getPharmWebsite() {
        return pharmWebsite;
    }

    public void setPharmWebsite(String pharmWebsite) {
        this.pharmWebsite = pharmWebsite;
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
        AbstractMedicine abstractMedicine = (AbstractMedicine) o;
        return price == abstractMedicine.price &&
                id.equals(abstractMedicine.id) &&
                name.equals(abstractMedicine.name) &&
                pharm.equals(abstractMedicine.pharm) &&
                group == abstractMedicine.group &&
                analog.equals(abstractMedicine.analog) &&
                certificate.equals(abstractMedicine.certificate);
    }

    @Override
    public int hashCode() {

        int prime = 31;
        int result = 1;

        result = result * prime + id.hashCode();
        result = result * prime + name.hashCode();
        result = result * prime + pharm.hashCode();
        result = result * prime + group.hashCode();
        result = result * prime + analog.hashCode();
        result = result * prime + certificate.hashCode();
        result = result * prime + Integer.hashCode(price);

        return result;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" id : ").append(id).append('\n');
        sb.append(" pharm website : ").append(pharmWebsite).append('\n');
        sb.append(" name : ").append(name).append('\n');
        sb.append(" pharm : ").append(pharm).append('\n');
        sb.append(" group : ").append(group).append("\n");
        sb.append(" analog : ").append(analog).append('\n');
        sb.append(certificate).append('\n');
        sb.append(" price : ").append(price).append('\n');
        sb.append('}');
        return sb.toString();
    }
}
