package edu.bsu.cs445.archdemo.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name="metadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbArtifactRecordCollection implements ArtifactRecordCollection {

    public static JaxbArtifactRecordCollection of(JaxbArtifactRecord... records) {
        Preconditions.checkNotNull(records, "Parameter may not be null");
        JaxbArtifactRecordCollection collection = new JaxbArtifactRecordCollection();
        collection.items.addAll(Arrays.asList(records));
        return collection;
    }

    public static JaxbArtifactRecordCollection createEmpty() {
        return new JaxbArtifactRecordCollection();
    }

    // This item is used by the JAXB parsing but not used in custom code.
    @SuppressWarnings({"unused","MismatchedQueryAndUpdateOfCollection"})
    @XmlElement(name="record")
    private List<JaxbArtifactRecord> items = Lists.newArrayList();

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public int countRecordsByTitleQuery(String query) {
        List<ArtifactRecord> result = searchArtifact(query);
        return result.size();
    }

    @Override
    public List<ArtifactRecord> searchArtifact(String query) {
        List<ArtifactRecord> result = items.stream()
                .filter(artifactRecord -> {
                    if (artifactRecord.getTitle() != null) {
                        if (artifactRecord.getTitle().contains(query)) {
                            return true;
                        }
                    }
                    if (artifactRecord.getDateMade() != null) {
                        if (artifactRecord.getDateMade().contains(query)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
        return ImmutableList.copyOf(result);
    }
}
