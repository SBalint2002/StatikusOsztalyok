package hu.petrik.statikusosztalyok;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class Veletlen {
    private Veletlen() {
    }

    private static final Random rnd = new Random();
    private static List<String> vezNevek = feltolt("files/veznev.txt");
    private static List<String> ferfiKerNevek = feltolt("files/ferfikernev.txt");
    private static List<String> noiKerNevek = feltolt("files/ferfikernev.txt");
    private static int index = 0;

    private static List<String> feltolt(String fajlNev) {
        List<String> lista = new ArrayList<>();
        try {
            Scanner file = new Scanner(new File(fajlNev));
            while (file.hasNext()) {
                String sor = file.nextLine();
                lista.add(sor);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static int velEgesz(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    public static char velKarakter(char min, char max) {
        return (char) velEgesz(min, max);
    }

    public static String velVezetekNev(){
        return vezNevek.get(rnd.nextInt(vezNevek.size()));
    }

    /**
     * Véletlen magyar keresztnév generálása
     * @param nem A generált keresztnév neme. Férfi esetén true, Nő esetén false.
     * return A generált keresztnév
     */

    public static String velKeresztnev(boolean nem){
        String keresztNev;
        if (nem){
            keresztNev = velFerfiKeresztNev();
        }else {
            keresztNev = velNoiKeresztNev();
        }
        return keresztNev;
    }

    public static String velFerfiKeresztNev(){
        return ferfiKerNevek.get(rnd.nextInt(ferfiKerNevek.size()));
    }

    public static String velNoiKeresztNev(){
        return noiKerNevek.get(rnd.nextInt(noiKerNevek.size()));
    }

    public static  String velTeljesNev(boolean nem){
        return velVezetekNev() + " " + velKeresztnev(nem);
    }

    public static String velDatum(int ev1, int ev2) {
        int min = (int) LocalDate.of(ev1, 1, 1).toEpochDay();
        int max = (int) LocalDate.of(ev2, 1, 1).toEpochDay();
        long randomNap = min + rnd.nextInt(max - min);
        LocalDate datum = LocalDate.ofEpochDay(randomNap);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formazott = datum.format(f);
        return formazott;
    }

    public static String velEmail(String nev){
        nev = nev.replaceAll("^\\p{ASCII}]","");
        String[] asd = nev.toLowerCase().split(" ");
        index++;
        return  asd[0]+asd[1]+index+"@gmail.com";
    }
}
