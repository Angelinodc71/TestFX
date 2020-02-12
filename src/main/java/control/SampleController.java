package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SampleController implements Initializable {
    int i = 0;
    ObservableList<String> observableList = FXCollections.observableArrayList();

    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Sessions> sesions;
    private List<Film> cicle;
    @FXML
    ListView<String> listView;

    @FXML
    Text textField;

    @FXML
    TabPane tabPane;

    List<String> listaFilmsTitulo;
    ReaderXML readerXML;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadFilms();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        i=0;

    }

    public void onClick(MouseEvent mouseEvent) throws IOException, JAXBException {
        ObservableList<String> film= listView.getSelectionModel().getSelectedItems();
        textField.setText(String.valueOf(film));

    }



    void loadFilms() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listFilms();
        films = readerXML.getFilms();
        System.out.println(readerXML.getFilms());
        listView.setItems(observableList);
        listaFilmsTitulo = films.stream().map(films -> films.getTitol()).collect(Collectors.toList());

        for (String titulosFilm: listaFilmsTitulo) {
            observableList.addAll(String.valueOf(titulosFilm));
        }
    }


    void loadCinemas() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listCinemas();
        cinemas = readerXML.getCinemas();
        System.out.println(readerXML.getCinemas());
        listView.setItems(observableList);
        listaFilmsTitulo = cinemas.stream().map(cinema -> cinema.getCinenom()).collect(Collectors.toList());

        for (String titulosCinema: listaFilmsTitulo) {
            observableList.addAll(String.valueOf(titulosCinema));
        }
    }
    // http://gencat.cat/llengua/cinema/cinemes.xml
    //http://www.gencat.cat/llengua/cinema/film_sessions.xml


}
