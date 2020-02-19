package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SampleController implements Initializable {
    int i = 0;
    ObservableList<String> observableList = FXCollections.observableArrayList();
    ObservableList<String> observableList2 = FXCollections.observableArrayList();
    ObservableList<String> observableList3 = FXCollections.observableArrayList();
    ObservableList<String> observableList4 = FXCollections.observableArrayList();


    private ObservableList<PieChart.Data> dataCharts;

    // Titol, Direcció i Sinopsi -- PANTALLA FILMS

    //FILM - SESSIO - getIdFilm()
    //SESSIO - CINEMA - getCineId()
    //SESSIO - CICLE - getCicleId()
    // Elegir cine, salen un monton de pelis de ese cine.

    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Sessions> sesions;
    private List<Film> cicle;
    @FXML
    ListView<String> listView;

    @FXML
    ListView<String> listView2_1;

    @FXML
    ListView<String> listView2;

    @FXML
    PieChart pieChart;

    @FXML
    BarChart<?,?> barChart;
    @FXML
    CategoryAxis x;
    @FXML
    NumberAxis y;

    @FXML
    Text textField;

    @FXML
    TabPane tabPane;

    @FXML
    ImageView img01;

    List<String> listaFilmsTitulo;
    ReaderXML readerXML;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadCinemas();
            loadFilms();
            dataCharts = FXCollections.observableArrayList();
            loadDataPieChart();
            pieChart.setData(dataCharts);
            loadDataBarChart();
            barChart.setTitle("Numero de cinemas");
//            Label label = new Label();
//
//            pieChart.getData().stream().forEach( data ->{
//                data.getNode().addEventFilter(MouseEvent.ANY, e ->{
//                    int value = (int) data.getPieValue();
//                    label.setText(String.valueOf(value));
//                });
//            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        i=0;

    }

    public void onClick(MouseEvent mouseEvent) throws IOException, JAXBException {
        String film= listView.getSelectionModel().getSelectedItem();
        textField.setText(String.valueOf(film));
        System.out.println();
        for (Film f: films) {
            System.out.println(f.getTitol());
            if (f.getTitol().equals(film)){
                img01.setImage(new Image("http://gencat.cat/llengua/cinema/"+f.getCartell()));
            }
        }

    }

    void loadDataPieChart(){
        Map<String, List<Film>> resul = films.stream()
                .collect(Collectors.groupingBy(Film::getAny));
        resul.forEach((k,v) -> dataCharts.addAll(new PieChart.Data(k, v.size())));

    }

    void loadDataBarChart(){
        XYChart.Series set1 = new XYChart.Series<>();

        Map<String, List<Cinema>> resul = cinemas.stream()
                .collect(Collectors.groupingBy(Cinema::getProvincia));
        resul.forEach((k,v) -> set1.getData().add(new XYChart.Data(k,v.size())));

        barChart.getData().addAll(set1);
        set1.setName("Provincies");
    }

    void loadFilms() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listFilms();
        films = readerXML.getFilms();

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

        listView2.setItems(observableList2);
        listaFilmsTitulo = cinemas.stream().map(cinema -> cinema.getCinenom()).collect(Collectors.toList());

        for (String titulosCinema: listaFilmsTitulo) {
            observableList2.addAll(String.valueOf(titulosCinema));
        }
    }

    public void onClickFilms(MouseEvent mouseEvent) {

    }
    // http://gencat.cat/llengua/cinema/cinemes.xml
    //http://www.gencat.cat/llengua/cinema/film_sessions.xml


}
