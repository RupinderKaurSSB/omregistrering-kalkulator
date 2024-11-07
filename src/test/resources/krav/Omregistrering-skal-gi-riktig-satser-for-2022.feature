#language:no
Egenskap: Omregistrering - kjøretøy beregner omregistrering

  Scenario: Omregistrering av 6 år gammel personbil
    Gitt dagens dato er 01.12.2022
    Og kjøretøy med følgende tekniske data
      | kjennemerke                   | AA12345    |
      | egenvekt                      | 1337       |
      | totaltvekt                    | 1800       |
      | kjoeretoeytype                | PERSONBIL  |
      | drivstoff                     | BENSIN     |
      | foerstegangsregistreringsdato | 2016-11-01 |
    Når omregistreringsavgiften beregnes
    Så skal omregistreringsavgiften bli 4034
