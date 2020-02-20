package control;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)
class Cicles {
    @XmlElement(name = "CICLE")
    public List<Cicle> cicles;

    @Override
    public String toString() {
        return "Cicles{" +
                "cicles=" + cicles +
                '}';
    }
}

public class Cicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "CICLEID")
    String cicleid;
    @XmlElement(name = "CICLENOM")
    String ciclenom;
    @XmlElement(name = "CICLEINFO")
    String cicleinfo;
    @XmlElement(name = "IMGCICLE")
    String imgcicle;

    @Override
    public String toString() {
        return "Cicle{" +
                "cicleid='" + cicleid + '\'' +
                ", ciclenom='" + ciclenom + '\'' +
                ", cicleinfo='" + cicleinfo + '\'' +
                ", imgcicle='" + imgcicle + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCicleid() {
        return cicleid;
    }

    public String getCiclenom() {
        return ciclenom;
    }

    public String getCicleinfo() {
        return cicleinfo;
    }

    public String getImgcicle() {
        return imgcicle;
    }
}
