package by.undrul.xmlTask.builder;

public enum MedicineXmlAttribute {
    ID,
    PHARM_WEBSITE;

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace(UNDERSCORE, HYPHEN);
    }
}
