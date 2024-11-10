# Omregistrering-kalkulator

Omregistreringskalkulatoren er bygd opp av en backend-del og en frontend-del.
Til den stillingen du har søkt på, er det obligatorisk at du løser backend-delen, og dersom du ønsker å bli vurdert som
en fullstack-utvikler, ber vi om at du også løser frontend-delen.

Java-applikasjonen ligger i *src/main/java/*, er skrevet i Java 11 og bygges med Maven.
Bygges med kommandoen *mvn clean install*.
Serveren kan startes med kommandoen *mvn spring-boot:run*.

Javascript-applikasjonen ligger i *src/main/webapp/* og bygges med Node/npm.
Den er konfigurert til å støtte Typescript, men det er mulig å benytte vanlig Javascript.
En utviklingsserver kan startes med kommandoen *npm start*.

## Oppgaver

Frontend-oppgave er spesifisert i:

- `src/main/webapp/README.md`

Backend-oppgave er spesifisert i:

- `src/main/java/README.md`

## Dokumentasjon

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

### Fluent UI

Det benyttes [Fluent UI](https://developer.microsoft.com/en-us/fluentui#/get-started) i frontend.

### Annet

- [Omregistrering (eierskifte)](https://www.skatteetaten.no/person/avgifter/bil/eierskifte/)
- [Forskrift om omregistreringsavgift](https://lovdata.no/dokument/SF/forskrift/1986-07-02-1430)
- [Skatteetaten sitt designsystem](https://skatteetaten.github.io/frontend-components/
  )
