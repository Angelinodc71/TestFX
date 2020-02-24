package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SampleController implements Initializable {
    ObservableList<String> observableList = FXCollections.observableArrayList();

    ObservableList<String> observableList2 = FXCollections.observableArrayList();

    ObservableList<String> observableList3 = FXCollections.observableArrayList();

    ObservableList<String> observableList4 = FXCollections.observableArrayList();

    private ObservableList<PieChart.Data> dataCharts;

    // RELACION ENTRE LOS DIFERENTES XML

    //FILM - SESSIO - getIdFilm()
    //SESSIO - CINEMA - getCineId()
    //SESSIO - CICLE - getCicleId()

    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Session> sessions;
    private List<Cicle> cicles;
    @FXML
    ListView<String> listView;

    @FXML
    ListView<String> listView2;

    @FXML
    ListView<String> listView2_1;

    @FXML
    ListView<String> listView3;

    @FXML
    ListView<String> listView3_1;

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
    WebView webView;


    @FXML
    ImageView img01;

    List<String> listaTitulos;
    List<String> listaFechasSession;
    List<String> listaTrailers;

    ReaderXML readerXML;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadCinemas();
            loadFilms();
            loadSessions();
            loadCicles();

            dataCharts = FXCollections.observableArrayList();
            loadDataPieChart();
            pieChart.setData(dataCharts);

            loadDataBarChart();
            // Le doy titulo al barChart
            barChart.setTitle("Numero de cinemas");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Aqui cargo todos los datos en el PieChart
    void loadDataPieChart(){
        // Guardo, agrupo y recorro el list de films y agrupo todos ellos
        // por los años para poder ver que año se han creado más peliculas
        Map<String, List<Film>> resul = films.stream()
                .collect(Collectors.groupingBy(Film::getAny));
        // Recorro el map y de los años guardados en el, en el valor hago un size y este es el tamaño del Chart
        resul.forEach((k,v) -> dataCharts.addAll(new PieChart.Data(k, v.size())));
    }

    // Aqui cargo los datos en el barChart
    void loadDataBarChart(){
        XYChart.Series set1 = new XYChart.Series<>();
        // Recorro el List de Cinemas, guardo y agrupo el nombre de las provincias
        //  y lo guardo en resul que es un map de un List y un String
        Map<String, List<Cinema>> resul = cinemas.stream()
                .collect(Collectors.groupingBy(Cinema::getProvincia));
        // Borro el primero porque siempre es null
        resul.remove("--");
        // Recorro el resul y agrupo por nombres de provincias y hago el size de ellas para ahorrarme un count
        resul.forEach((k,v) -> set1.getData().add(new XYChart.Data(k,v.size())));

        barChart.getData().addAll(set1);
        // Al set creado se le puede poner una leyenda
        set1.setName("Provincies");
    }

    // En esta clase cargo todas las Peliculas y guardo los trailers y los titulos en listaTitulos y listaTrailers,
    // este listaTitulos lo meto en una observableList y a partir de esta el ListView recibe los datos
    // (es el unico que meto en la observable porque es el unico que quiero que se vean los datos a más tiempo real posible)
    void loadFilms() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listFilms();
        films = readerXML.getFilms();

        listView.setItems(observableList);
        listaTitulos = films.stream().map(films -> films.getTitol()).collect(Collectors.toList());
        listaTrailers = films.stream().map(films -> films.getTrailer()).collect(Collectors.toList());

        for (String titulosFilm: listaTitulos) {
            observableList.addAll(String.valueOf(titulosFilm));
        }
    }

    // En esta clase cargo todas las Sessiones y guardo la fecha de sesion en listaFechasSession,
    // este listaFechasSession lo meto en una observableList y a partir de esta el ListView recibe los datos
    void loadSessions() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listSessions();
        sessions = readerXML.getSesions();

        listaFechasSession = sessions.stream().map(session -> session.getSes_data()).collect(Collectors.toList());

        for (String fechasSession: listaFechasSession) {
            observableList3.addAll(fechasSession);
        }
    }

    // En esta clase cargo todos los Cines y guardo su titulo en listTitulos,
    // este listTitulos lo meto en una observableList y a partir de esta el ListView recibe los datos
    void loadCinemas() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listCinemas();
        cinemas = readerXML.getCinemas();

        listView2.setItems(observableList2);
        listaTitulos = cinemas.stream().map(cinema -> cinema.getCinenom()).collect(Collectors.toList());

        for (String titulosCinema: listaTitulos) {
            observableList2.addAll(titulosCinema);
        }
    }

    // En esta clase cargo todos los ciclos y guardo su nombre en listTitulos,
    // este listTitulos lo meto en una observableList y a partir de esta el ListView recibe los datos
    void loadCicles() throws IOException, JAXBException {
        readerXML = new ReaderXML();
        readerXML.listCicles();
        cicles = readerXML.getCicle();

        listView3.setItems(observableList4);
        listaTitulos = cicles.stream().map(cicle -> cicle.getCiclenom()).collect(Collectors.toList());

        for (String titulosCicles: listaTitulos) {
            observableList4.addAll(titulosCicles);
        }
    }

    //TODO mvn compile assembly:single

    public void onClick(MouseEvent mouseEvent) throws IOException, JAXBException {
        //Aqui guardo en un string la pelicula que he hecho click
        String film= listView.getSelectionModel().getSelectedItem();
        for (Film f: films) {
            System.out.println(f.getTitol());
            // Y dentro de este forEach comparo toda la lista de peliculas con el string de la pelicula seleccionada
            if (f.getTitol().equals(film)){
                // Para poner la imagen de la peli segun el titulo seleccionado
                img01.setImage(new Image("http://gencat.cat/llengua/cinema/"+f.getCartell()));
                // Para cambiar el texto segun la pelicula a la que he hecho click
                textField.setText(film+"\n"+"DIRECCIO: "+f.getDireccio());
                // Para que salga el trailer de la pelicula que he hecho click
                webView.getEngine().load("https://www.youtube.com/embed/"+f.getTrailer()+"?autoplay=1");
            }
        }


    }

    public void onClickCinemas(MouseEvent mouseEvent) {
        //Lo declaro  aqui porque si lo declaro arriba se añaden y yo quiero que cada
        // observableList sea del respectivo Cine
        ObservableList<String> observableList3_1 = FXCollections.observableArrayList();
        List<String> listaTitulosSesData = new ArrayList<>();

        //Busco el titulo de ese cine al que he clicado
        String cinema = listView2.getSelectionModel().getSelectedItem();
        for (Cinema c: cinemas) {
            if (c.getCinenom().equals(cinema)){
                System.out.println(c.cineid);
                //Comparo el id del cine que he clicado con el de las sesiones
                for (Session s: sessions) {
                    if (s.getCineid().equals(c.cineid)) {
                        //Invoco a un metodo y lo junto con la fecha de la session en un list
                        listaTitulosSesData.add(searchIdFilm(s.getIdfilm())+" "+s.getSes_data());
                    }
                }
            }
        }
        //Aqui los añado al observable list
        for (String titulosFilmSesData: listaTitulosSesData) {
            observableList3_1.addAll(titulosFilmSesData);
        }
        listView2_1.setItems(observableList3_1);

        System.out.println(observableList3_1);
    }


    public void onClickCicles(MouseEvent mouseEvent) {
        ObservableList<String> observableList4_1 = FXCollections.observableArrayList();
        List<String> listaTitulos = new ArrayList<>();

        String cicle = listView3.getSelectionModel().getSelectedItem();
        for (Cicle e : cicles) {
            if (e.getCiclenom().equals(cicle)){
            System.out.println(e.getCicleid());
                for (Session s:sessions) {
                    if(s.getCicleid().equals(e.getCicleid())){
                        String valor = searchIdCinema(s.getCineid());
                        if (valor!=null)
                            listaTitulos.add(valor+" "+s.getSes_data());
                    }
                }
            }
        }
        System.out.println(listaTitulos);
        //Aqui los añado al observable list
        for (String titulosCines: listaTitulos) {
            observableList4_1.addAll(titulosCines);
        }
        listView3_1.setItems(observableList4_1);
    }

    public String searchIdFilm(String idFilm){
        // Busco por los id films que le paso y si coinciden
        // retorno el titulo de la pelicula
        for (Film f:films) {
            if (idFilm.equals((f.getIdfilm()))) {
                return f.getTitol();
            }
        }
        return null;
    }

    public String searchIdCinema(String idCinema){
        // Busco por los id films que le paso y si coinciden
        // retorno el titulo de la pelicula
        for (Cinema c:cinemas) {
            if (idCinema.equals((c.getCineid()))) {
                return c.getCinenom();
            }
        }
        return null;
    }

}
