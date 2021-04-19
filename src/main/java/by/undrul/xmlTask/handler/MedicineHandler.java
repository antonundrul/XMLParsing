package by.undrul.xmlTask.handler;

import by.undrul.xmlTask.builder.MedicineXmlAttribute;
import by.undrul.xmlTask.builder.MedicineXmlTag;
import by.undrul.xmlTask.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class MedicineHandler extends DefaultHandler {
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    private static Logger logger = LogManager.getLogger();

    private Set<AbstractMedicine> medicins;
    private EnumSet<MedicineXmlTag> tagsWithTextContent;
    private AbstractMedicine currentMedicine;
    private MedicineXmlTag currentTag;

    public MedicineHandler() {
        medicins = new HashSet<>();
        tagsWithTextContent = EnumSet.range(MedicineXmlTag.NAME, MedicineXmlTag.VOLUME);
    }

    public Set<AbstractMedicine> getMedicins() {
        return medicins;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String pillTag = MedicineXmlTag.PILL.toString();
        String sirupTag = MedicineXmlTag.SIRUP.toString();

        if (pillTag.equals(qName) || sirupTag.equals(qName)) {
            currentMedicine = pillTag.equals(qName)
                    ? new Pill()
                    : new Sirup();

            String idAttribute = MedicineXmlAttribute.ID.toString();
            String pharmWebsiteAttribute = MedicineXmlAttribute.PHARM_WEBSITE.toString();
            // added additional optional attribute to demonstrate proper parsing (including disordered attributes in xml)

            for (int i = 0; i < attributes.getLength(); i++) {
                String attributeName = attributes.getQName(i);

                if (attributeName.equals(idAttribute)) {
                    String medicineId = attributes.getValue(i);
                    currentMedicine.setId(medicineId);
                } else if (attributeName.equals(pharmWebsiteAttribute)) {
                    String pharmWebsite = attributes.getValue(i);

                    if (!pharmWebsite.isBlank()) {
                        currentMedicine.setPharmWebsite(pharmWebsite);
                    }
                } else {
                    logger.warn("Ignored unknown attribute: " + attributeName);
                }
            }
        } else {
            String constantName = toConstantName(qName);
            MedicineXmlTag tag = MedicineXmlTag.valueOf(constantName);

            if (tagsWithTextContent.contains(tag)) {
                currentTag = tag;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String pillTag = MedicineXmlTag.PILL.toString();
        String sirupTag = MedicineXmlTag.SIRUP.toString();

        if (pillTag.equals(qName) || sirupTag.equals(qName)) {
            medicins.add(currentMedicine);
            currentMedicine = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);

        if (currentTag != null) {
            switch (currentTag) {
                case NAME -> currentMedicine.setName(data);
                case PHARM -> currentMedicine.setPharm(data);
                case GROUP -> currentMedicine.setGroup(MedicineGroup.valueOf(data));
                case ANALOG -> currentMedicine.setAnalog(data);
                case CERTIFICATE_NUMBER -> currentMedicine.getCertificate().setCertificateNumber(data);
                case DATE_OF_ISSUE -> currentMedicine.getCertificate().setDateOfIssue(YearMonth.parse(data));
                case DATE_OF_EXPIRATION -> currentMedicine.getCertificate().setDateOfExpiration(YearMonth.parse(data));
                case REGISTERING_ORGANIZATION -> currentMedicine.getCertificate().setRegisteringOrganization(data);
                case PRICE -> currentMedicine.setPrice(Integer.parseInt(data));
                case AMOUNT -> {
                    Pill pill = (Pill) currentMedicine;
                    pill.setAmount(Integer.parseInt(data));
                }
                case DOSAGE -> {
                    Pill pill = (Pill) currentMedicine;
                    pill.setDosage(Integer.parseInt(data));
                }
                case VOLUME -> {
                    Sirup sirup = (Sirup) currentMedicine;
                    sirup.setVolume(Integer.parseInt(data));
                }
                default -> throw new EnumConstantNotPresentException(
                        currentTag.getDeclaringClass(), currentTag.name());
            }
        }

        currentTag = null;
    }

    private String toConstantName(String string) {
        return string.strip()
                .replace(HYPHEN, UNDERSCORE)
                .toUpperCase();
    }
}
