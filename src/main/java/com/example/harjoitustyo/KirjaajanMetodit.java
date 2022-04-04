/**Pääluokka metodeille
 * Tämä luokka vastaa siitä, että graafiseen käyttöliittymään syötetyt tiedot tulee käsiteltyä oikealla tavalla
 * Totetutus käyttää rajapintaa siten, että jos joku haluaisi laajentaa työtä, voitaisiin jokaiselle eri osalle tehdä
 * oma rajapintansa, joka sitten asettaa reunaehdot työlle.
 *
 * HUOM: korvaa package com..... oman kehitysympäristösi projektitunnuksella tai poista kokonaan
 *
 * @autor AnttiHurme*/

package com.example.harjoitustyo;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

public class KirjaajanMetodit implements Turnaus, Serializable {

    /**
     * Tämä on turnauksen lopetuspäivä. Formaatti päivämäärille varattu LocalDate.*/
     LocalDate EndDate;
     /**
      * Turnauksen aloituspäivä.Formaatti päivämäärille varattu LocalDate.*/
     LocalDate StartDate;
     /**
      * Turnauksen nimi merkkijonona.*/
     String TurnauksenNimi;
     /**
      * Turnauksen ID eli tunniste kokonaislukuna.*/
     int TurnauksenID;


     /**
      * Alustaja, joka saa parametrikseen kaikki  käyttämämme muuttujat. Tätä käytetään yhdessä toStringin kanssa
      * olioiden kirjoittamiseen tiedostoon.*/

    public KirjaajanMetodit(LocalDate endDate, LocalDate startDate, String turnauksenNimi, int turnauksenID) {
        EndDate = endDate;
        StartDate = startDate;
        TurnauksenNimi = turnauksenNimi;
        TurnauksenID = turnauksenID;
    }

    public KirjaajanMetodit(File readObject) {
    }

    public KirjaajanMetodit() {
    }


    /**
     * Aseta turnauksen lopetuspäivämäärä.*/
    @Override
    public void setEndDatetime(LocalDate d2) {
        this.EndDate = d2;
    }

    /**
     * Lue asetettu lopetuspäivämääärä.*/
    @Override
    public LocalDate getEndDatetime() {
        return EndDate;
    }

    /**
     * Aseta turnauksen aloituspäivämäärä*/
    @Override
    public void setStartDatetime(LocalDate d1) {
        this.StartDate = d1;
    }

    /**
     * Lue asetettu aloituspäivämäärä.*/
    @Override
    public LocalDate getStartDatetime() {
        return StartDate;
    }

    /**
     * Aseta turnauksen nimi.*/
    @Override
    public void setTurnauksenNimi(String TurnauksenNimi) {
        this.TurnauksenNimi = TurnauksenNimi;
    }

    /**
     * Lue asetettu nimi.*/
    @Override
    public String getTurnauksenNimi() {
        return TurnauksenNimi;
    }

    /**
     * Aseta turnauksen tunniste, eli ID.*/
    @Override
    public void setTurnauksenID(int id) {
        this.TurnauksenID = id;
    }

    /**
     * Lue asetettu tunniste, eli ID.*/
    @Override
    public int getTurnauksenID() {
        return TurnauksenID;
    }

    /**
     * Tämä on ohjelman päämetodi, joka huolehtii tietojen kirjaamisesta tiedostoon. Se saa parametrikseen kaikki
     * neljä muuttujaa, jotka alustajassa määriteltiin ja kirjaa ne sitten tiedostoon.
     * @param o {Object} on luokkaa olio ja sitä käytetään tämän luokan konstruktorin avulla kirjoittamaan turnauksen tiedot. */
    public void kirjaa (Object o)
    {
        try (ObjectOutputStream oliotiedosto = new ObjectOutputStream(new FileOutputStream((getTurnauksenID() + ".dat")))){
            oliotiedosto.writeObject(o);
        }
        catch (FileNotFoundException e) {
            System.err.println("Tiedostoa ei löytynyt");
        }
        catch (IOException e){
            System.err.println("Jotain meni pieleen.");
        }
    }

    /**
     * Tämä metodi lukee tiedostoja.
     * @param s {String}  on on siis merkkijono, joka saadaan tuolta graafisesta käyttöliittymästä. Se määrittelee tiedoston nimen.
     * @return palauttaa tässä listan, jonka sisältönä on olio. Se napataan käyttöön graafisessa luokassa*/

    public static String lue (String s) {

        KirjaajanMetodit o = null;
        try (ObjectInputStream oliotiedosto2 = new ObjectInputStream(new FileInputStream(s))) {
            o = (KirjaajanMetodit) oliotiedosto2.readObject();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Tiedostoa ei löytynyt.");
        } catch (EOFException EOFEx) {
            System.err.println("Tiedostosta yritettiin lukea liikaa.");
        } catch (IOException IOEx) {
            System.err.println("Tiedosto taisi löytyä, mutta jotain meni pieleen.");
        } catch (ClassNotFoundException CnFE) {
            System.err.println("Serialisoitua luokkaa ei löytynyt.");
        }
        return Arrays.toString(new KirjaajanMetodit[]{o});
    }


    /**
     * Määritellään tässä toString, jota käytän tuossa kirjaa-metodissa.
     * Tällä siis tehdään käyttäjän antamasta turnaus-ID -kokonaisluvusta merkkijono, joka annetaan tiedostin nimeksi.
     * @return Palauttaa muotoillun version turnauksen tunnisteesta, nimestä ja päivämääristä.*/
    @Override
    public String toString() {
        return "Turnauksen numero: " + TurnauksenID + "\nTurnauksen nimi: " + TurnauksenNimi
                +"\nAlkoi: " + StartDate + "\nLoppui: " + EndDate;
    }



}

