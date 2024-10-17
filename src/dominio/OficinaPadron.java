package dominio;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OficinaPadron {
    private final ArrayList<Habitante> habitantesPadron = new ArrayList<>();

    public OficinaPadron(){
        cargarHabitantes();
    }

    public void annadir(Habitante habitante) {
        habitantesPadron.add(habitante);
        volcarContactos();
    }

    public ArrayList<Habitante> getHabitantesPadron() {
        return habitantesPadron;
    }

    public int calcularNumeroHabitantes() {
        return habitantesPadron.size();
    }

    public static void main(String[] args) {
        Habitante habitante1 = new Habitante("Juan", "Torres", "Sanz");
        Habitante habitante2 = new Habitante("Marta", "Caballero", "Ramos");

        OficinaPadron padron = new OficinaPadron();

        padron.annadir(habitante1);
        padron.annadir(habitante2);

        ArrayList<Habitante> habitantes = padron.getHabitantesPadron();
        for (Habitante habitante : habitantes) {
            System.out.println(habitante.getNombre() + " " +
                    habitante.getApellido1() + " " +
                    habitante.getApellido2());
        }
        System.out.println("Número total de habitantes: " + padron.calcularNumeroHabitantes());
    }
    private void cargarHabitantes(){
        try{
            File fichero = new File("padron.csv");

            if (!fichero.exists()) {
                System.out.println("El archivo padron.csv no existe.");
                return;
            }
            Scanner sc = new Scanner(fichero);
            sc.useDelimiter("[,\n]");
            while(sc.hasNext()){
                Habitante habitante = new Habitante(sc.next(),
                        sc.next(),
                        sc.next());
                habitantesPadron.add(habitante);
            }
            sc.close();
        }catch(IOException ex){
            System.out.println("No hay habitantes inscritos");
        }
    }
    public void volcarContactos(){
        try{
            FileWriter fw = new FileWriter("padron.csv");
            for(Habitante habitante : habitantesPadron){
                fw.write(habitante.getNombre() + "," +habitante.getApellido1() + "," +habitante.getApellido2()+"\n");
            }
            fw.close();
        }catch(IOException ex){
            System.err.println(ex);
        }
    }
}



