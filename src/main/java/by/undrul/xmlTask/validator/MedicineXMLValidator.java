package by.undrul.xmlTask.validator;

import by.undrul.xmlTask.exception.MedicineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class MedicineXMLValidator {
    private static Logger logger = LogManager.getLogger();
    private static final String SCHEMA_NAME = "medicins.xsd";
    private static final URL SCHEMA_URL;

    static {
        ClassLoader loader = MedicineXMLValidator.class.getClassLoader();
        SCHEMA_URL = loader.getResource(SCHEMA_NAME);
    }

    public static boolean validateXMLFile(String xmlPath) throws MedicineException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);

        try {
            Schema schema = factory.newSchema(SCHEMA_URL);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
        } catch (IOException e) {
            throw new MedicineException("Cannot open file: " + xmlPath, e);
        } catch (SAXException e) {
            logger.warn("File " + xmlPath + " is not valid: ", e);
            return false;
        }

        return true;
    }
}
