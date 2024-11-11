import React, { useEffect, useState } from 'react';
import {Drivstoff} from "./enums/Drivstoff";
import {Kjoeretoeytype} from "./enums/Kjoeretoeytype";
import {
    TableBody,
    TableCell,
    TableRow,
    Table,
    TableHeader,
    TableHeaderCell,
    TableCellLayout,
    Avatar, Button,
} from "@fluentui/react-components";

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

    const sletteKjoeretoey = async (kjennemerke: string) => {
        try {
            await fetch(`/api/kjoeretoey/${kjennemerke}/fjern`, {
                method: 'DELETE',
            });
            henteAlleKjoeretoey();
        } catch (error) {
            console.error('Feil ved sletting av kjøretøy:', error);
        }
    };

    const redigereKjoeretoey = async (kjoeretoey: Kjoeretoey) => {
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
    const [nyttKjoeretoey, setNyttKjoeretoey] = useState<Kjoeretoey>({
        kjennemerke: '',
        kjoeretoeytype: Kjoeretoeytype.PERSONBIL,
        egenvekt: '',
        totalvekt: '',
        drivstoff: Drivstoff.BENSIN,
        foerstegangsregistreringsdato: ''
    });

    // Add handleCreate function
    const handleOppretteKjoeretoey = async () => {
        try {
            await fetch(`/api/kjoeretoey/${nyttKjoeretoey.kjennemerke}/opprett`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(nyttKjoeretoey),
            });
            setNyttKjoeretoey({
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
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHeaderCell>Kjennemerke</TableHeaderCell>
                    <TableHeaderCell>Kjøretøytype</TableHeaderCell>
                    <TableHeaderCell>Egen vekt</TableHeaderCell>
                    <TableHeaderCell>Total Vekt</TableHeaderCell>
                    <TableHeaderCell>Drivstoff</TableHeaderCell>
                    <TableHeaderCell>Førstegangsregistreringsdato</TableHeaderCell>
                    <TableHeaderCell>Actions</TableHeaderCell>
                </TableRow>
            </TableHeader>
            <TableBody>
                {kjoeretoeyListe.map((kjoeretoey) => (
                    <TableRow key={kjoeretoey.kjennemerke}>
                        <TableCell>
                            <TableCellLayout>
                                {kjoeretoey.kjennemerke}
                            </TableCellLayout>
                        </TableCell>
                        <TableCell>
                            <TableCellLayout>
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
                            </TableCellLayout>
                        </TableCell>
                        <TableCell>
                            <TableCellLayout>
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
                            </TableCellLayout>
                        </TableCell>
                        <TableCell>
                            <TableCellLayout>
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
                            </TableCellLayout>
                        </TableCell>
                        <TableCell>
                            <TableCellLayout>
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
                            </TableCellLayout>
                        </TableCell>
                        <TableCell>
                            <TableCellLayout>
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
                            </TableCellLayout>
                        </TableCell>
                        <TableCell>
                            <TableCellLayout>
                                <Button
                                    onClick={() => redigereKjoeretoey(kjoeretoey)}
                                    appearance="primary"
                                >
                                    {redigeringsId === kjoeretoey.kjennemerke ? 'Save' : 'Edit'}
                                </Button>
                                <Button
                                    onClick={() => sletteKjoeretoey(kjoeretoey.kjennemerke)}
                                    appearance="secondary"
                                >
                                    Slette
                                </Button>
                            </TableCellLayout>
                        </TableCell>
                    </TableRow>
                ))}
                <TableRow>
                    <TableCell>
                        <TableCellLayout>
                            <input
                                value={nyttKjoeretoey.kjennemerke}
                                onChange={(e) => setNyttKjoeretoey({...nyttKjoeretoey, kjennemerke: e.target.value})}
                                placeholder="Kjennemerke"
                            />
                        </TableCellLayout>
                    </TableCell>
                    <TableCell>
                        <TableCellLayout>
                            <select
                                value={nyttKjoeretoey.kjoeretoeytype}
                                onChange={(e) => setNyttKjoeretoey({...nyttKjoeretoey, kjoeretoeytype: e.target.value})}
                            >
                                {Object.values(Kjoeretoeytype).map((type) => (
                                    <option key={type} value={type}>{type}</option>
                                ))}
                            </select>
                        </TableCellLayout>
                    </TableCell>
                    <TableCell>
                        <TableCellLayout>
                            <select
                                value={nyttKjoeretoey.drivstoff}
                                onChange={(e) => setNyttKjoeretoey({...nyttKjoeretoey, drivstoff: e.target.value})}
                            >
                                {Object.values(Drivstoff).map((fuel) => (
                                    <option key={fuel} value={fuel}>{fuel}</option>
                                ))}
                            </select>
                        </TableCellLayout>
                    </TableCell>
                    <TableCell>
                        <TableCellLayout>
                            <input
                                value={nyttKjoeretoey.egenvekt}
                                onChange={(e) => setNyttKjoeretoey({...nyttKjoeretoey, egenvekt: e.target.value})}
                                placeholder="Egenvekt"
                            />
                        </TableCellLayout>
                    </TableCell>
                    <TableCell>
                        <TableCellLayout>
                            <input
                                value={nyttKjoeretoey.totalvekt}
                                onChange={(e) => setNyttKjoeretoey({...nyttKjoeretoey, totalvekt: e.target.value})}
                                placeholder="Totalvekt"
                            />
                        </TableCellLayout>
                    </TableCell>
                    <TableCell>
                        <TableCellLayout>
                            <input
                                type="date"
                                value={nyttKjoeretoey.foerstegangsregistreringsdato}
                                onChange={(e) => setNyttKjoeretoey({
                                    ...nyttKjoeretoey,
                                    foerstegangsregistreringsdato: e.target.value
                                })}
                            />
                        </TableCellLayout>
                    </TableCell><TableCell>
                    <TableCellLayout>
                        <button onClick={handleOppretteKjoeretoey}>Legge til kjøretøy</button>

                    </TableCellLayout>
                </TableCell>


                </TableRow>
            </TableBody>
        </Table>
    );

};
