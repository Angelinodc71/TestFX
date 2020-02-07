package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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
    @FXML
    ListView<String> listView;
    List<String> listaFilmsTitulo;
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            metodo();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        i=0;

    }

    public void onClick(MouseEvent mouseEvent) throws IOException, JAXBException {
//        for (int j = 0; j < films.size() ; j++) {
//
//        }

    }

    void loadFilms(){

        listView.setItems(observableList);
        listaFilmsTitulo = films.stream().map(films -> films.getTitol()).collect(Collectors.toList());

        for (String titulosFilm: listaFilmsTitulo) {
            observableList.addAll(String.valueOf(titulosFilm));
        }

    }

    private List<Film> films;
    private List<Film> cinemas;
    private List<Sessions> sesions;
    private List<Film> cicle;
    // http://gencat.cat/llengua/cinema/cinemes.xml
    //http://www.gencat.cat/llengua/cinema/film_sessions.xml

    void metodo () throws IOException, JAXBException {
        URL url = new URL("http://gencat.cat/llengua/cinema/provacin.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.films = ((Films)jaxbUnmarshaller.unmarshal(is)).films;
        loadFilms();
    }
}
