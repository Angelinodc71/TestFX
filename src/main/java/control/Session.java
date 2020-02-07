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
    public List<Session> sesions;

    @Override
    public String toString() {
        return "Session{" +
                "sesions=" + sesions +
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
}
