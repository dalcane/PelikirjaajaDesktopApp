/**
 * Luokka graafiselle käyttöliittymälle.
 * <p>
 * Tämä luokka piirtää ikkunan käyttäjälle, ottaa vastaan syötettä ja käyttää KirjaajanMetodit-luokkaa
 * kirjoittaakseen syötteet .dat -tiedostoon, joka nimetään ID:n perusteella.
 * <p>
 * HUOM: korvaa package com..... oman kehitysympäristösi projektitunnuksella tai poista kokonaan
 *
 * @autor AnttiHurme
 */

package com.example.harjoitustyo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import static java.lang.Integer.parseInt;


public class PeliKirjaaja extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        /*
        Luodaan sivupaneeli, joilla ohjelman toiminnot tapahtuvat
         */

        TabPane tabPane = new TabPane();

        /*
          Määritellään ensimmäinen välilehti ja sijoitetaan siihen GridPane, johon menee tekstikenttiä ja nappuloita.
         */
        Tab tab1 = new Tab("Talleta tietoja", new Label("Täällä voit tallentaa tietoja tiedostoihin."));
        GridPane grid = new GridPane();

        /*
        Tehdään hieman kosmeettisia asioita paneelille.
         */
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        /*
         Tässä askarrellaan tekstikentät, joihin määritellaan aputekstit, jotta käyttäjä osaa laittaa
         oikeita asioita.
         */
        final TextField nimi = new TextField();
        nimi.setPromptText("Anna turnauksen nimi.");
        nimi.setPrefColumnCount(10);
        nimi.getText();
        GridPane.setConstraints(nimi, 0, 0);
        grid.getChildren().add(nimi);


        //Tässä sitten kenttä turnauksen tunnisteelle
        final TextField id = new TextField();
        id.setPromptText("Anna turnauksen ID.");
        GridPane.setConstraints(id, 0, 1);
        grid.getChildren().add(id);

        /*
         Päivämäärien valinta tapahtuu tällä luokalla, erittäin näppärää.
         Loin näille myös tuollaiset nimilaput, koska niitä ei ollut kalenteriluokalle suoraan tarjolla.
         */
        DatePicker aloitusPaiva = new DatePicker();
        Label aloitus = new Label("Aloituspäivä:");
        GridPane.setConstraints(aloitus, 0, 2);
        grid.getChildren().add(aloitus);
        GridPane.setConstraints(aloitusPaiva, 0, 3);
        grid.getChildren().add(aloitusPaiva);


        //Sama juttu lopetuspäivälle
        DatePicker lopetusPaiva = new DatePicker();
        Label paattysmisPaiva = new Label("Päättymispäivä:");
        GridPane.setConstraints(paattysmisPaiva, 0, 4);
        grid.getChildren().add(paattysmisPaiva);
        GridPane.setConstraints(lopetusPaiva, 0, 5);
        grid.getChildren().add(lopetusPaiva);


        // Lisätään tietoruutu, jossa kerrotaan tiedoston lisäämisestä

        //tämä on varsinainen tietoruudun luokka
        Dialog<String> tietoRuutu1 = new Dialog<>();
        //nappula, josta poistutaan
        ButtonType poistu1 = new ButtonType("Poistu", ButtonBar.ButtonData.OK_DONE);
        //otsikko
        tietoRuutu1.setTitle("Tiedoksi.");
        //sijoitetaan poistu-nappi
        tietoRuutu1.getDialogPane().getButtonTypes().add(poistu1);

        /*
         * Määritellään talleta-näppäimen toiminta
         * */
        Button talleta = new Button("Talleta");
        GridPane.setConstraints(talleta, 1, 4);
        grid.getChildren().add(talleta);

        /*
         * Toiminnallisuus talleta-näppäimelle. Tässä tehdään paikalliset muuttujat kenttien pohjalta ja
         * ilmoitetaan käyttäjälle pop-upilla.
         * */
        talleta.setOnAction(e -> {
            LocalDate alku = aloitusPaiva.getValue();
            LocalDate loppu = lopetusPaiva.getValue();
            String tnimi = nimi.getText();

            /* tehdään tälläinen konsti, että saadaan tiedosto aina tehdyksi. Paikalliselle muuttujalle siis arvo 0.
             jos ohjelma havaitsee virheellisen merkkijonon kentästä, se informoi käyttäjää tästä.*/
            int tID = 0;
            try {
                tID = parseInt(id.getText());

            } catch (NumberFormatException ex) {
                tietoRuutu1.setContentText("Tapahtui tälläinen virhe: " + ex + "\nVarmista, että asetit turnauksen ID:ksi numeron, sillä muuten ohjelma " +
                        "tekee tiedoston nimellä " +
                        "\n'0.dat' tai ylikirjoittaa olemassa olevan." +
                        "\n Jos et ole varma, tee uusi tiedosto.");
                tietoRuutu1.showAndWait();
            }
            if (tID == 0) {
                tietoRuutu1.setContentText("Tiedostonimesi on nolla, lisäsithän turnaus-ID:n?");
                tietoRuutu1.showAndWait();
            }

            /*laitoin tähän vielä selvyyden vuoksi tiedon, minkä niminen tiedosto on luotu,
            jotta sen sisällön voi tarkistaa.*/
            tietoRuutu1.setContentText("Lisättiin tiedosto '" + tID + ".dat' onnistuneesti." +
                    "\n Tiedostot tallentuvat kehitysympäristösi määritelmien " +
                    "\nmukaan, yleensä projektin kansioon.");
            tietoRuutu1.showAndWait();
            KirjaajanMetodit kirjaajanMetodit = new KirjaajanMetodit(alku, loppu, tnimi, tID);
            kirjaajanMetodit.setTurnauksenID(tID);
            kirjaajanMetodit.setTurnauksenNimi(tnimi);
            kirjaajanMetodit.setStartDatetime(alku);
            kirjaajanMetodit.setEndDatetime(loppu);
            kirjaajanMetodit.kirjaa(kirjaajanMetodit);
        });


        //Tehdään tyhjennysnappi.
        Button clear = new Button("Tyhjennä");
        GridPane.setConstraints(clear, 1, 5);
        grid.getChildren().add(clear);

        //sille toiminnallisuus
        clear.setOnAction(e -> {
            nimi.clear();
            id.clear();
        });

        //Koko luotu gridpane sisällöksi ykköstäbille
        tab1.setContent(grid);

        // Kakkosvälilehti


        // Luodaan ja konfiguroidaan kakkosvälilehteä

        Tab tab2 = new Tab("Tarkastele tietoja", new Label("Klikkaa nappia valitaksesi tarkasteltavan tiedoston."));

        Button valitseNappi = new Button("Valitse tiedosto");

        // Yritetään helpottaa käyttäjän elämää ja tehdään tähän tiedostonvalitsin.
        FileChooser fileChooser = new FileChooser();

        /*Luodaan tiedostopolku, josta oletuksena etsitään ja tiedostonimi, jota käytetään.
         * Nämä kannattaa kommentoida pois omalla koneella*/
        fileChooser.setInitialDirectory(new File("C:\\users\\akthu\\ideaprojects\\"));
        fileChooser.setInitialFileName("1.dat");

        //Luodaan tekstikenttä, joka näyttää tiedostopolun ja tiedoston
        TextField tiedostoNimi = new TextField();


        //Tämä nappi antaa valita tiedoston explorerilla tms.
        valitseNappi.setOnAction(e -> {
            File valittuFilu = fileChooser.showOpenDialog(primaryStage);
            tiedostoNimi.setText(valittuFilu.getAbsolutePath());
        });


        //Tehdään tälläinen pop-up, joka näkyy kun tiedostoa lukee

        // tietoruutu

        Dialog<String> tietoRuutu = new Dialog<>();
        //nappula, josta poistutaan
        ButtonType poistu = new ButtonType("Poistu", ButtonBar.ButtonData.OK_DONE);
        //otsikko
        tietoRuutu.setTitle("Turnauksen tiedot.");
        //sijoitetaan poistu-nappi
        tietoRuutu.getDialogPane().getButtonTypes().add(poistu);

        //Tämä nappi lukee valitun tiedoston käyttämällä pääluokan metodia lue().
        Button lueNappi = new Button("Lue tiedoston tiedot.");
        lueNappi.setOnAction(e -> {
            String polku = (tiedostoNimi.getText());
            System.out.println(tiedostoNimi.getText());
            KirjaajanMetodit luetiedosto = new KirjaajanMetodit();
            tietoRuutu.setContentText(KirjaajanMetodit.lue(polku));
            tietoRuutu.showAndWait();
        });

        //Napit ja tekstikenttä laatikkoon ja laatikko täbiin
        VBox boksiFiluille = new VBox(valitseNappi, tiedostoNimi, lueNappi);
        boksiFiluille.setAlignment(Pos.CENTER);
        tab2.setContent(boksiFiluille);


        //Kolmosvälilehti

        Tab tab3 = new Tab("Ohje", new Label("- Jos talletat tietoja, laita turnauksen nimi merkkijonona, turnaukden id kokonaislukuna ja valitse halutessasi päivämäärät.\n" +
                "- Kun luet tiedostoja, valitse ensin tiedosto käyttämällä \"Valitse tiedosto\"-näppäintä.\n" +
                "    - Ohjelma näyttää tiedostopolun alapuolisessa tekstilaatikossa.\n" +
                "    - Klikkaa sitten \"Lue tiedoston tiedot\" -näppäintä ja ohjelma näyttää turnauksen tiedot pop-upissa."));


        //Kaikki välilehdet läjään
        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        //Tabit kiinni laatikkoon
        VBox vBox = new VBox(tabPane);

        //lisätään laatikko pääikkunaan ja näytetään

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pelikirjaaja");
        primaryStage.show();
    }
}
