# Omregistrering kalkulator - Frontend

Du skal lage en frontend for omregistrering kalkulator. Oppgavene forutsetter at du har bygd og startet backend
serveren, se dokumentasjon i `src/main/java/README.md`.

## Oppgave 1

List ut alle kjøretøyene som er lagret i registeret.

## Oppgave 2

Legg til støtte for å legge til og endre kjøretøy i registeret. Se endepunkt i `KjoeretoeyController`.

```http request
GET     /api/kjoeretoey/hentAlle
GET     /api/kjoeretoey/{{kjennemerke}}/hent
POST    /api/kjoeretoey/{{kjennemerke}}/opprett
DELETE  /api/kjoeretoey/{{kjennemerke}}/fjern
PUT     /api/kjoeretoey/{{kjennemerke}}/oppdater
```

Vi ønsker her at du setter opp en tabell hvor du kan velge et kjøretøy du ønsker å endre opplysninger på, og eventuelt
slette.

## Oppgave 3

Legg til støtte for å beregne omregistreringsavgift for et gitt kjøretøy (kjennemerke). Du skal gjøre et kall mot
backend basert på input fra brukeren (tekstfelt).

### Endepunkt

Dersom du har løst backend *oppgave 2*, skal du kalle endepunktet du lagde her.

Dersom du kun løser for frontend, skal du kalle endepunktet som benyttes på Skatteetatens sine sider.

```http request
POST https://apper.skatteetaten.no/omregistreringsavgift/beregn?language=no
```

med følgende eksempel på payload (NB. Skatteetatens sitt endepunkt krever at du sender inn en gyldig kjennemerke /
registreringsnummer på kjøretøy)

```json
{
  "regNr": "EC73019"
}
```

eksempel respons fra Skatteetaten sitt API:

```json
{
  "regnrBrukt": true,
  "regnr": "EC73019",
  "resultatTekst1": "Omregistreringsavgiften er 1 670,00 kroner.",
  "belop": 1670.0,
  "success": true,
  "version": "v2018",
  "language": "no"
}
```

## Dokumentasjon

### Skatteetatens designsystem

`npm install @skatteetaten/frontend-components`

Du kan benytte deg av [Skatteetaten sitt designsystem](https://skatteetaten.github.io/frontend-components/
) dersom du ønsker, men det er ikke et krav.

### `npm start`

Starter applikasjonen i utviklings-modus.
Åpne [http://localhost:3000](http://localhost:3000) for å se den i nettleseren.

Siden vil lastes inn på nytt dersom du gjør endringer / Du vil også kunne se lint feil i konsollen.

### `npm test`

Starter test-kjører i interaktiv observasjons-modus. Se avsnitt
under ["running tests"](https://facebook.github.io/create-react-app/docs/running-tests) for mer informasjon.