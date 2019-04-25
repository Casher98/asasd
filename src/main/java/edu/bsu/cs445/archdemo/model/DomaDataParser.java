package edu.bsu.cs445.archdemo.model;

import java.io.InputStream;

public interface DomaDataParser {
    ArtifactRecordCollection parse(InputStream input);
}