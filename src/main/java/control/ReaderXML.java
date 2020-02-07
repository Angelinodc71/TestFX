package control;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ReaderXML {
    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Sessions> sesions;
    private List<Film> cicle;

    void listFilms () throws IOException, JAXBException {
        URL url = new URL("http://gencat.cat/llengua/cinema/provacin.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.films = ((Films)jaxbUnmarshaller.unmarshal(is)).films;
    }

    void listCinemas () throws IOException, JAXBException {
        URL url = new URL("http://gencat.cat/llengua/cinema/cinemes.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Cinemas.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.cinemas = ((Cinemas)jaxbUnmarshaller.unmarshal(is)).cinemas;
    }


    public List<Film> getFilms() {
        return films;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public List<Sessions> getSesions() {
        return sesions;
    }

    public List<Film> getCicle() {
        return cicle;
    }
}
