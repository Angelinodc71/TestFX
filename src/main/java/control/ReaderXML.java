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

// En esta clase tengo lo quue me devuelven
// llos 4 xml y lo que me devuelve cada variable con su respectivo xml
public class ReaderXML {
    private List<Film> films;
    private List<Cinema> cinemas;
    private List<Session> sessions;
    private List<Cicle> cicle;

    void listFilms () throws IOException, JAXBException {
        URL url = new URL("http://gencat.cat/llengua/cinema/provacin.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.films = ((Films)jaxbUnmarshaller.unmarshal(is)).films;
    }

    void listCicles () throws IOException, JAXBException {
        URL url = new URL("http://gencat.cat/llengua/cinema/cicles.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Cicles.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.cicle = ((Cicles)jaxbUnmarshaller.unmarshal(is)).cicles;
    }

    void listSessions() throws IOException, JAXBException {
        URL url = new URL("http://gencat.cat/llengua/cinema/film_sessions.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Sessions.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.sessions = ((Sessions)jaxbUnmarshaller.unmarshal(is)).sessions;
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

    public List<Session> getSesions() {
        return sessions;
    }

    public List<Cicle> getCicle() {
        return cicle;
    }
}
