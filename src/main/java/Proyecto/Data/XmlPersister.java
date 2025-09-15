package Proyecto.Data;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XmlPersister {
    private String path;
    private static XmlPersister theInstance;
    public static XmlPersister instance() {
        if (theInstance == null) {
            theInstance = new XmlPersister("src/main/resources/data.xml");
        }
        return theInstance;
    }

    public XmlPersister(String path) {
        this.path = path;
    }

    public data cargarDatos() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(data.class);
        FileInputStream fis = new FileInputStream(path);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        data d = (data) jaxbUnmarshaller.unmarshal(fis);
        fis.close();
        return d;
    }

    public void guardarDatos(data data) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(data.class);
        FileOutputStream fos = new FileOutputStream(path);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(data, fos);
        fos.flush();
        fos.close();
    }
}
