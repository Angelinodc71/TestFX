package control;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)

class Sessions {
    @XmlElement(name = "SESSIONS")
    public List<Session> sessions;

    @Override
    public String toString() {
        return "Sessions{" +
                "sesion=" + sessions +
                '}';
    }
}

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "IDFILM")
    String idfilm;
    @XmlElement(name = "ses_id")
    String ses_id;
    @XmlElement(name = "CINEID")
    String cineid;
    @XmlElement(name = "TITOL")
    String titol;
    @XmlElement(name = "ses_data")
    String ses_data;
    @XmlElement(name = "CINENOM")
    String cinenom;
    @XmlElement(name = "LOCALITAT")
    String localitat;
    @XmlElement(name = "COMARCA")
    String comarca;
    @XmlElement(name = "CICLEID")
    String cicleid;
    @XmlElement(name = "ver")
    String ver;
    @XmlElement(name = "preu")
    String preu;
    @XmlElement(name = "ORDRESESSIO")
    String ordresessio;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIdfilm() {
        return idfilm;
    }

    public String getSes_id() {
        return ses_id;
    }

    public String getCineid() {
        return cineid;
    }

    public String getTitol() {
        return titol;
    }

    public String getSes_data() {
        return ses_data;
    }

    public String getCinenom() {
        return cinenom;
    }

    public String getLocalitat() {
        return localitat;
    }

    public String getComarca() {
        return comarca;
    }

    public String getCicleid() {
        return cicleid;
    }

    public String getVer() {
        return ver;
    }

    public String getPreu() {
        return preu;
    }

    public String getOrdresessio() {
        return ordresessio;
    }

    @Override
    public String toString() {
        return "Session{" +
                "idfilm='" + idfilm + '\'' +
                ", ses_id='" + ses_id + '\'' +
                ", cineid='" + cineid + '\'' +
                ", titol='" + titol + '\'' +
                ", ses_data='" + ses_data + '\'' +
                ", cinenom='" + cinenom + '\'' +
                ", localitat='" + localitat + '\'' +
                ", comarca='" + comarca + '\'' +
                ", cicleid='" + cicleid + '\'' +
                ", ver='" + ver + '\'' +
                ", preu='" + preu + '\'' +
                ", ordresessio='" + ordresessio + '\'' +
                '}';
    }
}
