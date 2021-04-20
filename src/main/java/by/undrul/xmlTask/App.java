package by.undrul.xmlTask;

import by.undrul.xmlTask.builder.MedicineBuilderFactory;
import by.undrul.xmlTask.builder.MedicineDomBuilder;
import by.undrul.xmlTask.builder.MedicineSaxBuilder;
import by.undrul.xmlTask.builder.MedicineStaxBuilder;
import by.undrul.xmlTask.entity.AbstractMedicine;
import by.undrul.xmlTask.exception.MedicineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws MedicineException {

        String path="./src/main/resources/data/medicins.xml";

        MedicineDomBuilder medicineDomBuilder = (MedicineDomBuilder) MedicineBuilderFactory.createMedicineBuilder("DOM");
        medicineDomBuilder.buildMedicins(path);
        Set<AbstractMedicine> medicinsDOM = medicineDomBuilder.getMedicins();
        medicinsDOM.forEach(System.out::println);

        MedicineSaxBuilder medicineSaxBuilder = (MedicineSaxBuilder) MedicineBuilderFactory.createMedicineBuilder("SAX");
        medicineSaxBuilder.buildMedicins(path);
        Set<AbstractMedicine> medicinsSAX = medicineSaxBuilder.getMedicins();
        medicinsSAX.forEach(System.out::println);


        MedicineStaxBuilder medicineStaxBuilder = (MedicineStaxBuilder) MedicineBuilderFactory.createMedicineBuilder("STAX");
        medicineStaxBuilder.buildMedicins(path);
        Set<AbstractMedicine> medicinsSTAX = medicineStaxBuilder.getMedicins();
        medicinsSTAX.forEach(System.out::println);


    }
}
