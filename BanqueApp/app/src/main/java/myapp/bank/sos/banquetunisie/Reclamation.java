package myapp.bank.sos.banquetunisie;


public class Reclamation {

    int idDab;
    String name;
    String etat;


    public Reclamation(int idDab, String name, String etat) {
        this.idDab=idDab;
        this.name= name;
        this.etat= etat;    }
    public Reclamation() {

    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "idDab=" + idDab +
                ", name='" + name + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdDab() {
        return idDab;
    }

    public void setIdDab(int idDab) {
        this.idDab = idDab;
    }
}
