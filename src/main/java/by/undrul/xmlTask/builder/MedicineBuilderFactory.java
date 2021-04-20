package by.undrul.xmlTask.builder;

import by.undrul.xmlTask.exception.MedicineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicineBuilderFactory {
    private static Logger logger = LogManager.getLogger();

    private enum ParserType {
        DOM, SAX, STAX
    }

    private MedicineBuilderFactory() {
    }

    public static AbstractMedicineBuilder createMedicineBuilder(String parserType) throws MedicineException {
        logger.info("Method to create Medicins Builder called with parser: " + parserType);
        try {
            ParserType type = ParserType.valueOf(parserType.toUpperCase());
            return switch (type) {
                case DOM -> new MedicineDomBuilder();
                case SAX -> new MedicineSaxBuilder();
                case STAX -> new MedicineStaxBuilder();
            };
        } catch (IllegalArgumentException e) {
            logger.error("Parser with name '" + parserType + "' not found");
            throw new MedicineException("Parser with name '" + parserType + "' not found", e);
        }
    }
}
