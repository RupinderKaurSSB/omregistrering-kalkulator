# Omregistrering kalkulator - Backend

Du skal lage en backend for omregistrering kalkulator.

## Oppgave 1

Lag en metode som beregner korrekt omregistreringsavgift gitt kjøretøydata (tekniske spesifikasjoner).
Se [dokumentasjon](#dokumentasjon) for satser.

## Oppgave 2

### Del 1

Lag en metode som beregner korrekt omregistreringsavgift gitt kjennemerke, med tilhørende endepunkt (Se klassen
KjoeretoeyRegister.java)

### Del 2

Utvid metoden for beregning av omregistreringsavgift til å hensynta fritak fra omregistreringsavgiften gitt at
kjøretøyet ble førstegangsregistrert for mer enn 30 år siden (veterankjøretøy).

## Dokumentasjon

### Fritak fra omregistreringsavgiften

Det kan gis fritak for betaling av omregistreringsavgiften for kjøretøy som har vært registrert i mer enn 30 år.

### Omregistreringsavgift 2022

| Kjøretøytype | Egenvekt         | Kjoeretoey_enums      | Førstegangsregistreringsår | Registreringsår | Registreringsår |
|--------------|------------------|----------------|----------------------------|-----------------|-----------------|
|              |                  |                | 2019 og nyere              | 2011 - 2018     | 2010 og eldre   |
| Personbiler  |                  |                |                            |                 |                 |
|              | t.o.m. 1200 kg   | BENSIN, DIESEL | 4 378 kr                   | 2 880 kr        | 1 729 kr        |
|              | Over 1200 kg     | BENSIN, DIESEL | 6 681 kr                   | 4 034 kr        | 1 729 kr        |
|              | Alle vektklasser | ELEKTRISITET   | 1 670 kr                   | 1 009 kr        | 432 kr          | 
| Varebiler    |                  |                |                            |                 |                 |
|              | Alle vektklasser | BENSIN, DIESEL | 2 189 kr                   | 1 383 kr        | 1 154 kr        |
|              | Alle vektklasser | ELEKTRISITET   | 1 670 kr                   | 1 009 kr        | 432 kr          |

Ved interesse kan du finne ut mer om [omregistrering (eierskifte) her](https://www.skatteetaten.no/person/avgifter/bil/eierskifte/), men det er ikke nødvendig
for å løse oppgaven. Både satser og regler for fritak er blit forenklet i denne implementasjonen av
omregistrering-kalkulator.

### `mvn clean install`

Bygges med kommandoen *mvn clean install*.

### `mvn spring-boot:run`

Serveren kan startes med kommandoen *mvn spring-boot:run*. Serveren kan nås
på [http://localhost:8080](http://localhost:8080).