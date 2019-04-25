package edu.bsu.cs445.archdemo;

import edu.bsu.cs445.archdemo.model.ArtifactRecord;
import edu.bsu.cs445.archdemo.model.JaxbArtifactRecord;
import edu.bsu.cs445.archdemo.model.JaxbArtifactRecordCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class JaxbArtifactRecordCollectionTest {

    @ParameterizedTest
    @MethodSource("createArgumentsForCountRecordsByTitleQueryTest")
    void testCountRecordsByTitleQuery(JaxbArtifactRecordCollection collection, String query, int expected) {
        int count = collection.countRecordsByTitleQuery(query);
        Assertions.assertEquals(expected, count);
    }

    private static Stream<Arguments> createArgumentsForCountRecordsByTitleQueryTest() {
        JaxbArtifactRecordCollection collectionContainingOnlyFoo = JaxbArtifactRecordCollection.of(JaxbArtifactRecord.withTitle("Foo"));
        return Stream.of(
                arguments(JaxbArtifactRecordCollection.createEmpty(), "", 0),
                arguments(collectionContainingOnlyFoo, "Foo", 1),
                arguments(collectionContainingOnlyFoo, "Bar", 0),
                arguments(
                        JaxbArtifactRecordCollection.of(
                                JaxbArtifactRecord.withTitle("Foo"),
                                JaxbArtifactRecord.withTitle("Fool")),
                        "Foo",
                        2
                )
        );
    }

    @Test
    void testSearchTitles() {
        final String testTitle = "Foo";
        JaxbArtifactRecordCollection collection = JaxbArtifactRecordCollection.of(
                JaxbArtifactRecord.withTitle(testTitle)
        );
        List<ArtifactRecord> result = collection.searchArtifact(testTitle);
        Assertions.assertEquals(testTitle, result.get(0).getTitle());
    }
}
