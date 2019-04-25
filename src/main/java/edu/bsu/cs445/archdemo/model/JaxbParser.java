package edu.bsu.cs445.archdemo.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class JaxbParser implements DomaDataParser {
    public static JaxbParser create() {
        return new JaxbParser();
    }

    public JaxbArtifactRecordCollection parse(InputStream input) {
        try {
            JAXBContext context = JAXBContext.newInstance(JaxbArtifactRecordCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (JaxbArtifactRecordCollection)unmarshaller.unmarshal(input);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
