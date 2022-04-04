// Kehittämisopas--------------------------------------------------
/*

TYÖN AIHE:

- Kirjanpito-ohjelma lautapeliturnauksia varten. Ohjelmalla tallennetaan turnauksen tiedot tiedostoon.


TYÖN OLIOLUOKAN KUVAUS:

- Työn olioluokka TurnauksenMetodit.java toteuttaa metodeja, joilla tietoa tallennetaan tiedostoihin
    - toString, jotta tiedot ovat yhtenäisen näköiset ja luettavat.
    - Kirjaa(Object o) luo olioita tiedostoon ja tallentaa ne IDE:n määrittelemään polkuun.
    - Lue (String s) käyttää luokan toString-metodia ja palauttaa oliolistan, joka voidaan lukea
    graafisessa luokassa.
- Työn toinen olioluokka on PeliKirjaaja, joka toteuttaa graafisen ympäristön, joka ottaa käyttäjältä syötettä.
    - Käyttäjää informoidaan pop-upeilla ja erillisellä info-sivulla.

OHJELMAN RAKENTEEN KUVAUS JATKOKEHITTÄJÄLLE:

- Työssä on käyttöliittymä, joka ottaa vastaan syötettä lautapelitietokantaa varten.
- Käyttöliittymässä on erilliset välilehdet tietojen syöttämiselle ja niiden lukemiselle.
- Tässä vaiheessa siihen voi syöttää tietoja turnaukselle, jossa on seuraavat attribuutit:
    - TurnausID(kokonaisluku), TurnausNimi(merkkijono), StartDate(päivämäärä) ja EndDate(päivämäärä).
- Syötetyt tiedot tallennetaan .dat-tiedostoon ja ne voidaan lukea ja tulostaa näytölle käyttäjän niin halutessa.
- Yhden taulun toteutus tehdään yhdellä luokalla, jota sitten implementoidaan graafisessa ohjelmassa
    - PeliKirjaaja.java on graafisen ohjelman luokan nimi
    - KirjaajanMetodit.java pitää sisällään koodin syötteen käsittelyä varten
        - Kirjaa(Object o) luo olioita tiedostoon ja tallentaa ne IDE:n määrittelemään polkuun.
        - Lue (String s) käyttää luokan toString-metodia ja palauttaa oliolistan, joka voidaan lukea
        graafisessa luokassa.
    - Jatkossa ohjelmaa voisi laajentaa vaikkapa PelaajanMetodit -luokalla.
        - Siihen määriteltäisiin ensin tarvittavat tekijät rajapinnalla Pelaaja, ja tehtäisiin tallennusmetodit
        omaan luokkaansa. Tiedostojen nimeämiseen otettaisiin vaikka p+pelaajaid, niin saataisiin uniikin nimet.

KÄYTTÖ- JA ASENNUSOHJE:

- laita zip-paketistä löytyvän OHJELMAKOODI-tiedoston Java-luokat samaan kansioon oikeilla nimillään
    - Pelikirjaaja.java, KirjaajanMetodit.java, Turnaus.java
    - varmista, että olet IDE:ssäsi määritelyt Pelikirjaaja.javan JavaFX-projektiksi
    - varmista, että olet määritellyt Turnaus.javan interfaceksi, jos niin vaaditaan.
- poista package com..... -merkintä kaikista .java -tiedostoista tai korvaa omalla projektitunnuksella, jos sinulla on sellainen
- navigoi PeliKirjaaja.javan main-osion päälle ja suorita se tavalla, joka on määritelty kehitysympäristössäsi (tyypillisesti Run tms.)
- Jos talletat tietoja, mene sen nimiselle välilehdelle, laita turnauksen nimi merkkijonona, turnaukden id kokonaislukuna ja valitse halutessasi päivämäärät.
- Kun luet tiedostoja, valitse ensin tiedosto käyttämällä "Valitse tiedosto"-näppäintä.
    - Ohjelma näyttää tiedostopolun alapuolisessa tekstilaatikossa.
    - Klikkaa sitten "Lue tiedoston tiedot" -näppäintä ja ohjelma näyttää turnauksen tiedot pop-upissa.

REFLEKTIOTA ohjelmasta:

- Hyvät puolet:
    - Selkeys ja käyttäjäystävällisyys.
    - Kentät ja toiminnot ovat ilmeisiä.
    - Käytetty modernia tapaa valita päivämäärä --> eliminoi user erroria.
    - Käyttäjää neuvotaan ja selitetään, mitä tapahtuu.

- Huonot puolet:
    - Varsin karsittu ulkomuoto, ei krumeluureja.
    - Ei tue useamman turnauksen syöttämistä yhtä aikaa.
    - Voi brute force -ylikirjoittaa tiedostoja, jos käyttäjä ei ole tarkkana.

- Kehittämiskohteet:
    - Elegantimpi tapa hoitaa tiedoston nimeämistä.
    - Useampien asioiden kirjaaminen kerralla.
    - Monen tiedoston tietojen tuominen näyttöön kerralla.
    - Hienompi ulkoasu ohjelmalle.

*/

//Projektikertomus-------------------------------------------------
/*

PÄIVÄKIRJA

2.3.2022 (aikaa käytetty n. 3 tuntia):
- aloitin projektin rakenteen suunnittelun keksimäni konseptin pohjalta
- pohdintaa aiheesta interface ja sen toimivuus tässä kontekstissa
    - jokainen lautapelitietokannan taulu olisikin interface ja niitä implementoitaisiin luokassa KirjaajanMetodit?
- dokumentaation rakenteen valmistelua ja kehittämisoppaan kirjoittamista
- opittua: JavaDocin käyttö oikeassa hieman pidemmässä työssä.

7.3.2022 (aikaa käytetty n. 1 tunti)
- kipeänä, mutta ajatustyötä toteutukseen kuumehöyryissä

8.3.2022 (aikaa käytetty n. 2,5 tuntia)
- koronassa viimeiset neljä päivää, en saanut kirjoitettua sillä välin, mutta nyt pientä aktivoitumista
- rajapinnan, metodiluokan ja sovellusluokan alkumäärittelyjä
- JavaDoc -dokumentaatiota ja tätä dokumenttia
- Opittua: rajapinnan speksaaminen

10.3.2022 (aikaa käytetty n. 3,5 tuntia)
- graafisen ohjelman laatimista:
    - välilehdet, tekstikentät, päivämäärävalitsin, nappuloita jne.
- dokumentaatiota
- opitut: GridPanen järkevämpi käyttö, nappien toiminta fiksusti

14.3.2022 (aikaa käytetty n. 8 tuntia)
- metodien kirjoittamista
    - tiedostot kirjoittuvat hyvin
- debuggausta
    - file chooser toimii, mutta en saa objekteja luettua graafisesta liittymästä
        - pääluokan toString toimii, testasin sitä siellä Mainissa
        - onko tässä joku, että pitäisi vääntää tuolta mainin sisältä, vai miten...
        - vaihtoehdot tällä hetkellä Stack Overflow tai ClassCastException
- dokumentointia
- opittua: kannattaa mielummin ottaa etäisyyttä projektiin kuin hakata päätä seinään tuntitolkulla

15.3.2022 (aikaa käytetty n. 5 tuntia)
- metodien viimeistelyä, kosmeettisia asioita, pop-uppeja
- debuggausta raskaalla kädellä, nyt kaikki toimii
    - opin, että kannattaa siivota koodia toisinaan ja esim. poistella turhia tiedostoja
- dokumentaatiota
- opittua: viestilaatikot JavaFX:ssä, poikkeuksenkäsittelyn määrittelyn tärkeys

16.3.2022 (aikaa käytetty n. 3 tuntia)
- koodin siistimistä, kommenttien yhtenäistämistä, muutama käyttäjän neuvominen
- dokumentaatiota, etenkin kehittämisoppaan kirjoittamista
- reflektiota
- zip-paketin tekeminen
- opittua: koodin kommentointi selkeyttää debuggaamista ja luo itselleenkin paremman kuvan projektista


*/