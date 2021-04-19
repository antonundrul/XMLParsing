package by.undrul.xmlTask.builder;

import by.undrul.xmlTask.entity.*;
import by.undrul.xmlTask.exception.MedicineException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;

public class MedicineDomBuilder extends AbstractMedicineBuilder{
    private DocumentBuilder documentBuilder;

    public MedicineDomBuilder() throws MedicineException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new MedicineException("Unable to configure parser", e);
        }
    }

    @Override
    public void buildMedicins(String xmlPath) throws MedicineException {

        Document document;

        try {
            document=documentBuilder.parse(xmlPath);
            Element root = document.getDocumentElement();

            String pillTag = MedicineXmlTag.PILL.toString();
            String sirupTag = MedicineXmlTag.SIRUP.toString();

            NodeList pills = document.getElementsByTagName(pillTag);
            NodeList sirups = document.getElementsByTagName(sirupTag);

            for (int i = 0; i < pills.getLength(); i++) {
                Element element = (Element) pills.item(i);
                AbstractMedicine medicine = new Pill();
                buildEntity(element, medicine);
                medicins.add(medicine);
            }

            for (int i = 0; i < sirups.getLength(); i++) {
                Element element = (Element) sirups.item(i);
                AbstractMedicine medicine = new Sirup();
                buildEntity(element, medicine);
                medicins.add(medicine);
            }
        } catch (SAXException e) {
           throw new MedicineException("Unable to parse XML file (" + xmlPath + ")", e);
        } catch (IOException e) {
            throw new MedicineException("Unable to parse XML file (" + xmlPath + ")", e);
        }

    }

    private void buildEntity(Element element, AbstractMedicine medicine) {
        // initializing abstract device
        String idAttribute = MedicineXmlAttribute.ID.toString();
        String pharmWebsiteAttribute = MedicineXmlAttribute.PHARM_WEBSITE.toString();
        String nameTag = MedicineXmlTag.NAME.toString();
        String pharmTag = MedicineXmlTag.PHARM.toString();
        String groupTag = MedicineXmlTag.GROUP.toString();
        String analogTag = MedicineXmlTag.ANALOG.toString();
        String certificateNumberTag = MedicineXmlTag.CERTIFICATE_NUMBER.toString();
        String dateOfIssueTag = MedicineXmlTag.DATE_OF_ISSUE.toString();
        String dateOfExpirationTag = MedicineXmlTag.DATE_OF_EXPIRATION.toString();
        String registeringOrganizationTag = MedicineXmlTag.REGISTERING_ORGANIZATION.toString();
        String priceTag = MedicineXmlTag.PRICE.toString();

        String id = element.getAttribute(idAttribute);
        String pharmWebsite = element.getAttribute(pharmWebsiteAttribute);
        String name = getElementTextContent(element, nameTag);
        String pharm = getElementTextContent(element, pharmTag);
        MedicineGroup group = MedicineGroup.valueOf(getElementTextContent(element, groupTag));
        String analog = getElementTextContent(element, analogTag);
        String certificateNumber = getElementTextContent(element, certificateNumberTag);
        YearMonth dateOfIssue = YearMonth.parse(getElementTextContent(element, dateOfIssueTag));
        YearMonth dateOfExpiration = YearMonth.parse(getElementTextContent(element, dateOfExpirationTag));
        String registeringOrganization = getElementTextContent(element, registeringOrganizationTag);
        int price = Integer.parseInt(getElementTextContent(element, priceTag));

        if (!pharmWebsite.isBlank()) {
            medicine.setPharmWebsite(pharmWebsite);
        }


        medicine.setId(id);
        medicine.setPharmWebsite(pharmWebsite);
        medicine.setName(name);
        medicine.setPharm(pharm);
        medicine.setGroup(group);
        medicine.setAnalog(analog);
        medicine.setCertificate(new Certificate(certificateNumber, dateOfIssue,dateOfExpiration,registeringOrganization));
        medicine.setPrice(price);

        // initializing concrete instances
        if (medicine.getClass() == Pill.class) {
            Pill pill = (Pill) medicine;
            String amountTag = MedicineXmlTag.AMOUNT.toString();
            String dosageTag = MedicineXmlTag.DOSAGE.toString();
            int amount = Integer.parseInt(getElementTextContent(element, amountTag));
            int dosage = Integer.parseInt(getElementTextContent(element, dosageTag));
            pill.setAmount(amount);
            pill.setDosage(dosage);
        } else {
            Sirup sirup = (Sirup) medicine;
            String volumeTag = MedicineXmlTag.VOLUME.toString();
            int volume = Integer.parseInt(getElementTextContent(element, volumeTag));
            sirup.setVolume(volume);
        }
    }

    private String getElementTextContent(Element element, String name) {
        NodeList nodeList = element.getElementsByTagName(name);
        Node node = nodeList.item(0);

        return node.getTextContent();
    }
}
