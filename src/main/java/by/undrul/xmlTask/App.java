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
    static Logger logger = LogManager.getLogger();

    public static void main( String[] args ) throws MedicineException {


        String path="./src/main/resources/data/medicins.xml";
       /* MedicineDomBuilder medicineDomBuilder = (MedicineDomBuilder) MedicineBuilderFactory.createMedicineBuilder("DOM");
        medicineDomBuilder.buildMedicins(path);
        Set<AbstractMedicine> medicins = medicineDomBuilder.getMedicins();t
        medicins.forEach(System.out::println);*/

        MedicineSaxBuilder medicineSaxBuilder = (MedicineSaxBuilder) MedicineBuilderFactory.createMedicineBuilder("SAX");
        medicineSaxBuilder.buildMedicins(path);
        Set<AbstractMedicine> medicins1 = medicineSaxBuilder.getMedicineHandler().getMedicins();
        medicins1.forEach(System.out::println);

        /*MedicineStaxBuilder medicineStaxBuilder = (MedicineStaxBuilder) MedicineBuilderFactory.createMedicineBuilder("STAX");
        medicineStaxBuilder.buildMedicins(path);
        Set<AbstractMedicine> medicins2 = medicineStaxBuilder.getMedicins();
        medicins2.forEach(System.out::println);*/


    }
}
