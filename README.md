Pelikirjaaja - yksinkertainen työpöytäsovellus tietojen tallentamiseen

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
