package fr.nohas.horairetrain;

//classe
public class HoraireDeTrain {
    private String villeDepart;
    private String villeArrivee;
    private String horaire;

    public static final HoraireDeTrain[] horaireDeTrain = {
            new HoraireDeTrain("Montpellier","Paris","8:05"),
            new HoraireDeTrain("Montpellier","Lyon","8:05"),
            new HoraireDeTrain("Montpellier","Marseille","8:05"),
            new HoraireDeTrain("Montpellier","Avignon","8:05"),
            new HoraireDeTrain("Montpellier","Séte","8:05"),
    };

    public HoraireDeTrain(String vDep, String vArriv, String h) {
        this.villeDepart = vDep;
        this.villeArrivee = vArriv;
        this.horaire=h;
    }
    public String getVilleDepart(){
        return this.villeDepart;
    }
    public String getVilleArrivee(){
        return this.villeArrivee;
    }
    public String getHoraire(){
        return this.horaire;
    }
    public String toString(){
        return this.villeDepart+":"+this.villeArrivee+":"+this.horaire;
    }

}
