package by.undrul.xmlTask.builder;

public enum MedicineXmlTag {
    MEDICINS,
    PILL,
    SIRUP,
    NAME,
    PHARM,
    GROUP,
    ANALOG,
    CERTIFICATE,
    CERTIFICATE_NUMBER,
    DATE_OF_ISSUE,
    DATE_OF_EXPIRATION,
    REGISTERING_ORGANIZATION,
    PRICE,
    DOSAGE,
    AMOUNT,
    VOLUME;

    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace(UNDERSCORE, HYPHEN);
    }

}
