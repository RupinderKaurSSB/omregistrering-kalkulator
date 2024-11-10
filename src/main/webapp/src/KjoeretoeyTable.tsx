import React, { useEffect, useState } from 'react';
import {fdatasync} from "node:fs";
import {Drivstoff} from "./enums/Drivstoff";
import {Kjoeretoeytype} from "./enums/Kjoeretoeytype";

interface Kjoeretoey {
    kjennemerke: string;
    kjoeretoeytype: string;
    drivstoff: string;
    egenvekt: string;
    totalvekt: string
    foerstegangsregistreringsdato: string;
}

export const KjoeretoeyTable = () => {
    const [kjoeretoeyListe, setKjoeretoeyListe] = useState<Kjoeretoey[]>([]);
    const [redigeringsId, setRedigeringsId] = useState<string | null>(null);
    const [redigeringsSkjema, setRedigeringsSkjema] = useState<Kjoeretoey | null>(null);

    useEffect(() => {
        henteAlleKjoeretoey();
    }, []);

    const henteAlleKjoeretoey = async () => {
        try {
            const response = await fetch('/api/kjoeretoey/hentAlle');
            const data = await response.json();
            setKjoeretoeyListe(data);
        } catch (error) {
            console.error('Feil under henting av kjøretøy:', error);
        }
    };

    const handleDelete = async (kjennemerke: string) => {
        try {
            await fetch(`/api/kjoeretoey/${kjennemerke}/fjern`, {
                method: 'DELETE',
            });
            henteAlleKjoeretoey();
        } catch (error) {
            console.error('Feil ved sletting av kjøretøy:', error);
        }
    };

    const håndtereRedigering = async (kjoeretoey: Kjoeretoey) => {
        if (redigeringsId === kjoeretoey.kjennemerke) {
            try {
                await fetch(`/api/kjoeretoey/${kjoeretoey.kjennemerke}/oppdater`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(redigeringsSkjema),
                });
                setRedigeringsId(null);
                setRedigeringsSkjema(null);
                henteAlleKjoeretoey();
            } catch (error) {
                console.error('Feil under oppdatering av kjøretøy:', error);
            }
        } else {
            setRedigeringsId(kjoeretoey.kjennemerke);
            setRedigeringsSkjema(kjoeretoey);
        }
    };

    // Add newVehicle state
    const [nyttKjøretøy, setNyttKjøretøy] = useState<Kjoeretoey>({
        kjennemerke: '',
        kjoeretoeytype: Kjoeretoeytype.PERSONBIL,
        egenvekt: '',
        totalvekt: '',
        drivstoff: Drivstoff.BENSIN,
        foerstegangsregistreringsdato: ''
    });

    // Add handleCreate function
    const handleCreate = async () => {
        try {
            await fetch(`/api/kjoeretoey/${nyttKjøretøy.kjennemerke}/opprett`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(nyttKjøretøy),
            });
            setNyttKjøretøy({
                kjennemerke: '',
                kjoeretoeytype: Kjoeretoeytype.PERSONBIL,
                egenvekt: '',
                totalvekt: '',
                drivstoff: Drivstoff.BENSIN,
                foerstegangsregistreringsdato: ''
            });
            henteAlleKjoeretoey();
        } catch (error) {
            console.error('Error creating vehicle:', error);
        }
    };


    return (
        <table>
            <thead>
            <tr>
                <th>Kjennemerke</th>
                <th>Kjoeretoeytype</th>
                <th>Egen vekt</th>
                <th>Total Vekt</th>
                <th>Drivstoff</th>
                <th>Førstegangsregistreringsdato</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {kjoeretoeyListe.map((kjoeretoey) => (
                <tr key={kjoeretoey.kjennemerke}>
                    <td>{kjoeretoey.kjennemerke}</td>
                    <td>
                        {redigeringsId === kjoeretoey.kjennemerke ? (
                            <select
                                value={redigeringsSkjema?.kjoeretoeytype}
                                onChange={(e) =>
                                    setRedigeringsSkjema({...redigeringsSkjema!, kjoeretoeytype: e.target.value})
                                }
                            >
                                {Object.values(Kjoeretoeytype).map((type) => (
                                    <option key={type} value={type}>
                                        {type}
                                    </option>
                                ))}
                            </select>
                        ) : (
                            kjoeretoey.kjoeretoeytype
                        )}
                    </td>
                    <td>
                        {redigeringsId === kjoeretoey.kjennemerke ? (
                            <input
                                value={redigeringsSkjema?.egenvekt}
                                onChange={(e) =>
                                    setRedigeringsSkjema({...redigeringsSkjema!, egenvekt: e.target.value})
                                }
                            />
                        ) : (
                            kjoeretoey.egenvekt
                        )}
                    </td>
                    <td>
                        {redigeringsId === kjoeretoey.kjennemerke ? (
                            <input
                                value={redigeringsSkjema?.totalvekt}
                                onChange={(e) =>
                                    setRedigeringsSkjema({...redigeringsSkjema!, totalvekt: e.target.value})
                                }
                            />
                        ) : (
                            kjoeretoey.totalvekt
                        )}
                    </td>
                    <td>
                        {redigeringsId === kjoeretoey.kjennemerke ? (
                            <select
                                value={redigeringsSkjema?.drivstoff}
                                onChange={(e) =>
                                    setRedigeringsSkjema({...redigeringsSkjema!, drivstoff: e.target.value})
                                }
                            >
                                {Object.values(Drivstoff).map((fuel) => (
                                    <option key={fuel} value={fuel}>
                                        {fuel}
                                    </option>
                                ))}
                            </select>
                        ) : (
                            kjoeretoey.drivstoff
                        )}
                    </td>

                    <td>
                        {redigeringsId === kjoeretoey.kjennemerke ? (
                            <input
                                type="date"
                                value={redigeringsSkjema?.foerstegangsregistreringsdato}
                                onChange={(e) =>
                                    setRedigeringsSkjema({
                                        ...redigeringsSkjema!,
                                        foerstegangsregistreringsdato: e.target.value,
                                    })
                                }
                            />
                        ) : (
                            kjoeretoey.foerstegangsregistreringsdato
                        )}
                    </td>
                    <td>
                        <button onClick={() => håndtereRedigering(kjoeretoey)}>
                            {redigeringsId === kjoeretoey.kjennemerke ? 'Save' : 'Edit'}
                        </button>
                        <button onClick={() => handleDelete(kjoeretoey.kjennemerke)}>
                            Delete
                        </button>
                    </td>
                </tr>
            ))}
            <tr>
                <td>
                    <input
                        value={nyttKjøretøy.kjennemerke}
                        onChange={(e) => setNyttKjøretøy({...nyttKjøretøy, kjennemerke: e.target.value})}
                        placeholder="Kjennemerke"
                    />
                </td>
                <td>
                    <select
                        value={nyttKjøretøy.kjoeretoeytype}
                        onChange={(e) => setNyttKjøretøy({...nyttKjøretøy, kjoeretoeytype: e.target.value})}
                    >
                        {Object.values(Kjoeretoeytype).map((type) => (
                            <option key={type} value={type}>{type}</option>
                        ))}
                    </select>
                </td>
                <td>
                    <select
                        value={nyttKjøretøy.drivstoff}
                        onChange={(e) => setNyttKjøretøy({...nyttKjøretøy, drivstoff: e.target.value})}
                    >
                        {Object.values(Drivstoff).map((fuel) => (
                            <option key={fuel} value={fuel}>{fuel}</option>
                        ))}
                    </select>
                </td>
                <td>
                    <input
                        value={nyttKjøretøy.egenvekt}
                        onChange={(e) => setNyttKjøretøy({...nyttKjøretøy, egenvekt: e.target.value})}
                        placeholder="Egenvekt"
                    />
                </td>
                <td>
                    <input
                        value={nyttKjøretøy.totalvekt}
                        onChange={(e) => setNyttKjøretøy({...nyttKjøretøy, totalvekt: e.target.value})}
                        placeholder="Totalvekt"
                    />
                </td>
                <td>
                    <input
                        type="date"
                        value={nyttKjøretøy.foerstegangsregistreringsdato}
                        onChange={(e) => setNyttKjøretøy({...nyttKjøretøy, foerstegangsregistreringsdato: e.target.value})}
                    />
                </td>

                <td>
                    <button onClick={handleCreate}>Add Vehicle</button>
                </td>
            </tr>
            </tbody>
        </table>
    );
};
