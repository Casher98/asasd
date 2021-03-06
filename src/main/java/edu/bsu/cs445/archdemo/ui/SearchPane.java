package edu.bsu.cs445.archdemo.ui;

import com.google.common.base.Preconditions;
import edu.bsu.cs445.archdemo.model.ArtifactRecord;
import edu.bsu.cs445.archdemo.model.ArtifactRecordCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SearchPane extends VBox {




    @FXML
    @SuppressWarnings("unused") // This field is used by FXML, so suppress the warning
    private TextField searchField;

    @FXML
    @SuppressWarnings("unused") // This field is used by FXML, so suppress the warning
    private Button searchButton;

    @FXML
    @SuppressWarnings("unused") // This field is used by FXML, so suppress the warning
    private Label resultCount;

    @FXML
    @SuppressWarnings("unused") // This field is used by FXML, so suppress the warning
    private HBox searchHBox;

    @FXML
    @SuppressWarnings("unused") // This field is used by FXML, so suppress the warning
    private VBox resultBox;

    private final ArtifactRecordCollection collection;

    SearchPane(ArtifactRecordCollection collection) {
        this.collection = Preconditions.checkNotNull(collection);
        URL fxmlUrl = getClass().getResource("searchPane.fxml");
        Preconditions.checkNotNull(fxmlUrl, "Fxml asset location is not specified correctly.");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unused") // This method is actually used via searchPane.fxml.
    @FXML
    public void search() {
        Preconditions.checkNotNull(collection, "The collection should already be in memory");
        disableInter(true);
        String searchTerm = searchField.getText();
        List<ArtifactRecord> records = collection.searchArtifact(searchTerm);
        resultBox.getChildren().clear();

        if (records.size() == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Results or Invalid Search");
            alert.setHeaderText("Results for " + searchTerm + " were not found");
            alert.showAndWait();
            disableInter(false);
        }
        else  {
            for (int i = 0; i < records.size() && i < 10; i++) {
                ArtifactRecord record = records.get(i);
                resultBox.getChildren().add(new ArtifactView(record));
            }
            resultCount.setText(String.valueOf(records.size()));
           disableInter(false);
        }
    }

    private void disableInter(boolean choice){
        searchHBox.setDisable(choice);
        searchField.setDisable(choice);
    }
}
