package control;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name ="dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)
class Cinemes {

    @XmlElement(name = "CINEMES")
    public List<Cinema> cinemes;

    @Override
    public String toString() {
        return "Cinemes{" +
                "cinemes=" + cinemes +
                '}';
    }
}

public class Cinema implements Serializable {
    public static final long serialVersionUID = 1L;
    @XmlElement(name = "CINEID")
    String cineid;

    @XmlElement(name = "CINENOM")
    String cinenom;

    @XmlElement(name = "CINEADRECA")
    String cineadreca;

    @XmlElement(name = "LOCALITAT")
    String localitat;

    @XmlElement(name = "COMARCA")
    String comarca;

    @XmlElement(name = "PROVINCIA")
    String provincia;

    public String getCineid() {
        return cineid;
    }

    public String getCinenom() {
        return cinenom;
    }

    public String getCineadreca() {
        return cineadreca;
    }

    public String getLocalitat() {
        return localitat;
    }

    public String getComarca() {
        return comarca;
    }

    public String getProvincia() {
        return provincia;
    }

    public Cinema(){
        super();
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cineid='" + cineid + '\'' +
                ", cinenom='" + cinenom + '\'' +
                ", cineadreca='" + cineadreca + '\'' +
                ", localitat='" + localitat + '\'' +
                ", comarca='" + comarca + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}

