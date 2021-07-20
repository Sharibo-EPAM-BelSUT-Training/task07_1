package gmail.alexejkrawez.medicine;

import org.xml.sax.SAXException;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class Main {

    public static void main(String[] args) {

        ObjectFactory objectFactory = new ObjectFactory();
        Medicine medicine = objectFactory.createMedicine();

        medicine.setName("Зовиракс");
        medicine.setPharm("Glaxo Wellcome");
        medicine.setGroup("Противовирусные (за исключением ВИЧ) средства");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Ацикловир");
        arrayList.add("Ацикловир Сандоз");
        medicine.setAnalogs(arrayList);
        medicine.setVersions(objectFactory.createMedicineVersions());
        medicine.versions.setVersion("Таблетки 200 мг");
        medicine.versions.setCertificate("№ П N015206/01, 2008-10-16 от ГлаксоСмитКляйн Трейдинг (Россия)");
        medicine.versions.setPackage("В блистере 5 шт.; в коробке 5 блистеров.");
        medicine.versions.setDosage("Взрослые: 1 табл. (200 мг) внутрь 5 раз в день, 5 дней.\n" +
                "\t\t\tДети старше 2 лет: 1 табл. (200 мг) внутрь 5 раз в день, 5 дней.\n" +
                "\t\t\tДети младше 2 лет: — 0,5 табл. (100 мг) внутрь 5 раз в день, 5 дней.");


        Medicine medicine2 = objectFactory.createMedicine();

        medicine2.setName("Ацикловир Сандоз");
        medicine2.setPharm("Salutas Pharma");
        medicine2.setGroup("Противовирусные (за исключением ВИЧ) средства");
        arrayList.clear();
        arrayList.add("Ацикловир");
        arrayList.add("Зовиракс");
        medicine2.setAnalogs(arrayList);
        medicine2.setVersions(objectFactory.createMedicineVersions());
        medicine2.versions.setVersion("Таблетки 200 мг");
        medicine2.versions.setCertificate("№ ЛП-001538, 2012-02-27 от Sandoz d.d. (Словения)");
        medicine2.versions.setPackage("В блистере 10 шт.; в коробке 5 блистеров.");
        medicine2.versions.setDosage("Взрослые: 1 табл. (200 мг) внутрь 5 раз в день, 5 дней.\n" +
                "\t\t\tДети старше 2 лет: 1 табл. (200 мг) внутрь 5 раз в день, 5 дней.\n" +
                "\t\t\tДети младше 2 лет: — 0,5 табл. (100 мг) внутрь 5 раз в день, 5 дней.");


        System.out.println("Marshalling:");
        try {
            File file = new File("src/gmail/alexejkrawez/File.xml");
            File file2 = new File("src/gmail/alexejkrawez/File2.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Medicine.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = schemaFactory.newSchema(new File(
                    "src/gmail/alexejkrawez/Medicine.xsd"));

            jaxbMarshaller.setSchema(schema);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(medicine, file);
            jaxbMarshaller.marshal(medicine, System.out);

            jaxbMarshaller.marshal(medicine2, file2);
            jaxbMarshaller.marshal(medicine2, System.out);
        } catch (SAXException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
        } catch (MarshalException me) {
            me.printStackTrace();
        } catch (JAXBException je) {
            je.printStackTrace();
        }

        System.out.println("\nUnmarshalling:");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Medicine.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = schemaFactory.newSchema(new File(
                    "src/gmail/alexejkrawez/Medicine.xsd"));
            jaxbUnmarshaller.setSchema(schema);

            Medicine medicine3 = (Medicine) jaxbUnmarshaller.unmarshal(new File(
                    "src/gmail/alexejkrawez/File.xml"));
            System.out.println(medicine3.toString());
        } catch (SAXException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
        } catch (UnmarshalException ue) {
            ue.printStackTrace();
        } catch (JAXBException je) {
            je.printStackTrace();
        }

    }
}
