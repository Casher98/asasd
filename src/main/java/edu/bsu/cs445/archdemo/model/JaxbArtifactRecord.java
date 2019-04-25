package edu.bsu.cs445.archdemo.model;

import com.google.common.base.Preconditions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="record")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbArtifactRecord implements ArtifactRecord {

    public static JaxbArtifactRecord withTitle(String title) {
        JaxbArtifactRecord record = new JaxbArtifactRecord();
        record.title = Preconditions.checkNotNull(title);
        return record;
    }

    @SuppressWarnings("unused") // Not set in source code, but set through JAXB.
    @XmlElement(name="Title")
    private String title;

    @SuppressWarnings("unused") // Not set in source code, but set through JAXB.
    @XmlElement(name="CONTENTdm_File_Name")
    private String fileName;

    @SuppressWarnings("unused") // not set in source code, but set through JAXB.
    @XmlElement(name="Date_Made")
    private String dateMade;

    @SuppressWarnings("unused") // not set in source code, but set through JAXB.
    @XmlElement(name="Artist")
    private String artist;

    public String getTitle() {
        return title;
    }

    public String getFileName() { return fileName; }

    public String getDateMade() { return dateMade;}

    public String getArtist() { return artist;}
}
