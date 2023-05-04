import java.time.LocalDate;

public class Main {
    private String cilos;
    private int kilometros;
    private LocalDate datumos;


    @Override
    public String toString() {
        return "," + cilos + "," + kilometros +
                "," + datumos + "\n";
    }


    public String getCilos() {
        return cilos;
    }



    public void setCilos(String cilos) {
        this.cilos = cilos;
    }


    public int getKilometros() {
        return kilometros;
    }



    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }



    public LocalDate getDatumos() {
        return datumos;
    }



    public void setDatumos(LocalDate datumos) {
        this.datumos = datumos;
    }



    public Main(String cil, int pocetKm, LocalDate datum) {
        this.cilos = cil;
        this.kilometros = pocetKm;
        this.datumos = datum;
    }

}