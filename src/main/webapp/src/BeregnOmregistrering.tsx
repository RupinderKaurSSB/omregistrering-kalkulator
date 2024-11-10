import React, { useState, useEffect } from 'react';

interface BeregneOmregistrering {
    avgift: number;
    kjennemerke: string;
}

interface Kjoeretoey {
    kjennemerke: string;
    kjoeretoeytype: string;
    Drivstoff: string;
}

export const BeregnOmregistrering = () => {
    const [kjennemerke, setKjennemerke] = useState('');
    const [kjoeretoey, setKjoeretoey] = useState<Kjoeretoey[]>([]);
    const [resultat, setResultat] = useState<BeregneOmregistrering  | null>(null);

    useEffect(() => {
        henteAlleKjoeretoey();
    }, []);

    const henteAlleKjoeretoey = async () => {
        const response = await fetch('/api/kjoeretoey/hentAlle');
        const data = await response.json();
        setKjoeretoey(data);
    };
    const beregning = async () => {
        try {
            const response = await fetch(`/api/omregistrering/${kjennemerke}/beregnAvgift`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                }
            });
            const data = await response.json();
            setResultat(data);
        } catch (error) {
            console.error('Feil ved beregning:', error);
        }
    };

    return (
        <div className="beregning">
            <h3>Beregn omregistreringsavgift</h3>
            <div>
                <input
                    type="text"
                    value={kjennemerke}
                    onChange={(e) => setKjennemerke(e.target.value.toUpperCase())}
                    placeholder="Skriv inn kjennemerke"
                />
                <button onClick={beregning}>Beregn avgift</button>
            </div>
            <h2>Eller</h2>
            <div>
                <select
                    value={kjennemerke}
                    onChange={(e) => setKjennemerke(e.target.value)}
                >
                    <option value="">Velg kjøretøy</option>
                    {kjoeretoey.map((vehicle) => (
                        <option key={vehicle.kjennemerke} value={vehicle.kjennemerke}>
                            {vehicle.kjennemerke} - {vehicle.kjoeretoeytype} {vehicle.Drivstoff}
                        </option>
                    ))}
                </select>
                <button onClick={beregning}>Beregn avgift</button>
            </div>
            {resultat && (
                <div className="result">
                    <p>Omregistreringsavgift for {resultat.kjennemerke}:</p>
                    <p>Beløp: {resultat.avgift.toFixed(2)} kr</p>
                </div>
            )}
        </div>
    );
};
