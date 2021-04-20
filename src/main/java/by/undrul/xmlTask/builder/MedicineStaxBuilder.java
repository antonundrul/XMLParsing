package by.undrul.xmlTask.builder;

import by.undrul.xmlTask.entity.*;
import by.undrul.xmlTask.exception.MedicineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.time.YearMonth;
import java.util.Optional;

import static by.undrul.xmlTask.builder.MedicineXmlTag.SIRUP;

public class MedicineStaxBuilder extends AbstractMedicineBuilder {
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';

    private static final String PILL_TAG = MedicineXmlTag.PILL.toString();
    private static final String SIRUP_TAG = SIRUP.toString();

    private static Logger logger = LogManager.getLogger();

    @Override
    public void buildMedicins(String xmlPath) throws MedicineException {
        logger.info("Method to build medicins from " + MedicineSaxBuilder.class + " called");

        String name;

        try {
            Source source = new StreamSource(xmlPath);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(source);

            while (reader.hasNext()) {
                int type = reader.next();

                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    if (name.equals(PILL_TAG)) {
                        AbstractMedicine medicine = new Pill();
                        buildEntity(medicine, reader);
                        medicins.add(medicine);
                    }

                    if (name.equals(SIRUP_TAG)) {
                        AbstractMedicine medicine = new Sirup();
                        buildEntity(medicine, reader);
                        medicins.add(medicine);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new MedicineException("An error occurred during file reading: ", e);
        }
    }


    private void buildEntity(AbstractMedicine medicine, XMLStreamReader reader) throws XMLStreamException, MedicineException {
        String idAttribute = MedicineXmlAttribute.ID.toString();
        String pharmWebsiteAttribute = MedicineXmlAttribute.PHARM_WEBSITE.toString();
        String medicineId = reader.getAttributeValue(null, idAttribute);
        String pharmWebsite = reader.getAttributeValue(null, pharmWebsiteAttribute);

        medicine.setId(medicineId);

        if (pharmWebsite != null) {
            medicine.setPharmWebsite(pharmWebsite);
        }

        String name;

        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    String constantName = toConstantName(name);
                    MedicineXmlTag tag = MedicineXmlTag.valueOf(constantName);
                    initializeField(reader, tag, medicine);
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();

                    if (name.equals(PILL_TAG) || name.equals(SIRUP_TAG)) {
                        return;
                    }
                }
            }
        }

        throw new MedicineException("Unable to build Device object");
    }

    private void initializeField(XMLStreamReader reader, MedicineXmlTag tag, AbstractMedicine medicine)
            throws XMLStreamException, MedicineException {

        String data = getTextContent(reader)
                .orElseThrow(() -> new MedicineException("Unable to get text content"));

        switch (tag) {
            case NAME -> medicine.setName(data);
            case PHARM -> medicine.setPharm(data);
            case GROUP -> medicine.setGroup(MedicineGroup.valueOf(data));
            case ANALOG -> medicine.setAnalog(data);
            case CERTIFICATE -> {
                Certificate certificate = new Certificate();
                medicine.setCertificate(certificate);
            }
            case CERTIFICATE_NUMBER -> medicine.getCertificate().setCertificateNumber(data);
            case DATE_OF_ISSUE -> medicine.getCertificate().setDateOfIssue(YearMonth.parse(data));
            case DATE_OF_EXPIRATION -> medicine.getCertificate().setDateOfExpiration(YearMonth.parse(data));
            case REGISTERING_ORGANIZATION -> medicine.getCertificate().setRegisteringOrganization(data);
            case PRICE -> medicine.setPrice(Integer.parseInt(data));
            case AMOUNT -> {
                Pill pill = (Pill) medicine;
                pill.setAmount(Integer.parseInt(data));
            }
            case DOSAGE -> {
                Pill pill = (Pill) medicine;
                pill.setDosage(Integer.parseInt(data));
            }
            case VOLUME -> {
                Sirup sirup = (Sirup) medicine;
                sirup.setVolume(Integer.parseInt(data));
            }
            default -> throw new EnumConstantNotPresentException(
                    tag.getDeclaringClass(), tag.name());
        }
    }

    private String toConstantName(String string) {
        return string.strip()
                .replace(HYPHEN, UNDERSCORE)
                .toUpperCase();
    }

    private Optional<String> getTextContent(XMLStreamReader reader) throws XMLStreamException {
        Optional<String> result = Optional.empty();

        if (reader.hasNext()) {
            reader.next();
            String text = reader.getText();
            result = Optional.of(text);
        }

        return result;
    }
}
