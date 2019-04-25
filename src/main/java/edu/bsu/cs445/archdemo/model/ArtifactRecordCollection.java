package edu.bsu.cs445.archdemo.model;

import java.util.List;

public interface ArtifactRecordCollection {

    int size();
    int countRecordsByTitleQuery(String query);
    List<ArtifactRecord> searchArtifact(String query);
}
