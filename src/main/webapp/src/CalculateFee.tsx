import React, { useState, useEffect } from 'react';

interface CalculationResult {
    avgift: number;
    kjennemerke: string;
}

interface Kjoeretoey {
    kjennemerke: string;
    kjoeretoeytype: string;
    Drivstoff: string;
}

export const CalculateFee = () => {
    const [regNr, setRegNr] = useState('');
    const [vehicles, setVehicles] = useState<Kjoeretoey[]>([]);
    const [result, setResult] = useState<CalculationResult | null>(null);

    useEffect(() => {
        fetchVehicles();
    }, []);

    const fetchVehicles = async () => {
        const response = await fetch('/api/kjoeretoey/hentAlle');
        const data = await response.json();
        setVehicles(data);
    };
    const calculateFee = async () => {
        try {
            const response = await fetch(`/api/omregistrering/${regNr}/beregnAvgift`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                }
            });
            const data = await response.json();
            setResult(data);
        } catch (error) {
            console.error('Error calculating fee:', error);
        }
    };

    return (
        <div className="calculate-fee">
            <h3>Beregn omregistreringsavgift</h3>
            <div>
                <input
                    type="text"
                    value={regNr}
                    onChange={(e) => setRegNr(e.target.value.toUpperCase())}
                    placeholder="Skriv inn kjennemerke"
                />
                <button onClick={calculateFee}>Beregn avgift</button>
            </div>
            <h2>Eller</h2>
            <div>
                <select
                    value={regNr}
                    onChange={(e) => setRegNr(e.target.value)}
                >
                    <option value="">Velg kjøretøy</option>
                    {vehicles.map((vehicle) => (
                        <option key={vehicle.kjennemerke} value={vehicle.kjennemerke}>
                            {vehicle.kjennemerke} - {vehicle.kjoeretoeytype} {vehicle.Drivstoff}
                        </option>
                    ))}
                </select>
                <button onClick={calculateFee}>Beregn avgift</button>
            </div>
            {result && (
                <div className="result">
                    <p>Omregistreringsavgift for {result.kjennemerke}:</p>
                    <p>Beløp: {result.avgift.toFixed(2)} kr</p>
                </div>
            )}
        </div>
    );
};
