package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javax.xml.bind.JAXBException;
import java.io.IOException;
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
    Text textTitulo;

    @FXML
    ImageView imageFilm;
    @FXML
    TabPane tabPane;

    List<String> listaFilmsTitulo;
    List<String> listaFilmsCartell;
    ReaderXML readerXML;

    Image image;
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

        textTitulo.setText(String.valueOf(film));
        imageFilm.setImage(new Image("http://www.gencat.cat/llengua/cinema/"+film));
    }



    void loadFilms() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listFilms();
        films = readerXML.getFilms();
        System.out.println(readerXML.getFilms());
        listView.setItems(observableList);
        listaFilmsTitulo = films.stream().map(films -> films.getTitol()).collect(Collectors.toList());
        listaFilmsCartell = films.stream().map(films -> films.getCartell()).collect(Collectors.toList());

        for (String titulosFilm: listaFilmsTitulo) {
            observableList.addAll(String.valueOf(titulosFilm));
        }
        for (String fotosFilm: listaFilmsCartell) {
            observableList.addAll(String.valueOf(fotosFilm));
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
