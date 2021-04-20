package by.undrul.xmlTask.builder;

import by.undrul.xmlTask.exception.MedicineException;
import by.undrul.xmlTask.handler.MedicineErrorHandler;
import by.undrul.xmlTask.handler.MedicineHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class MedicineSaxBuilder extends AbstractMedicineBuilder {
    private XMLReader xmlReader;
    private MedicineHandler medicineHandler;
    private static Logger logger = LogManager.getLogger();

    public MedicineSaxBuilder() throws MedicineException {
        medicineHandler = new MedicineHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            xmlReader = parser.getXMLReader();
            xmlReader.setErrorHandler(new MedicineErrorHandler());
        } catch (SAXException | ParserConfigurationException e) {
            throw new MedicineException("Unable to configure SAX parser", e);
        }
    }

    public MedicineHandler getMedicineHandler() {
        return medicineHandler;
    }

    @Override
    public void buildMedicins(String xmlPath) throws MedicineException {
        logger.info("Method to build medicins from " + MedicineSaxBuilder.class + " called");
        try {
            xmlReader.parse(xmlPath);
        } catch (IOException e) {
            throw new MedicineException("Unable to open XML file (" + xmlPath + ")", e);
        } catch (SAXException e) {
            throw new MedicineException("Unable to open XML file (" + xmlPath + ")", e);
        }

        medicins = medicineHandler.getMedicins();
    }
}
