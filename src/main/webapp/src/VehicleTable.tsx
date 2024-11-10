import React, { useEffect, useState } from 'react';
import {fdatasync} from "node:fs";
import {Drivstoff} from "./enums/Drivstoff";
import {Kjoeretoeytype} from "./enums/Kjoeretoeytype";

interface Vehicle {
    kjennemerke: string;
    kjoeretoeytype: string;
    drivstoff: string;
    egenvekt: string;
    totalvekt: string
    foerstegangsregistreringsdato: string;
}

export const VehicleTable = () => {
    const [vehicles, setVehicles] = useState<Vehicle[]>([]);
    const [editingId, setEditingId] = useState<string | null>(null);
    const [editForm, setEditForm] = useState<Vehicle | null>(null);

    useEffect(() => {
        fetchVehicles();
    }, []);

    const fetchVehicles = async () => {
        try {
            const response = await fetch('/api/kjoeretoey/hentAlle');
            const data = await response.json();
            setVehicles(data);
        } catch (error) {
            console.error('Error fetching vehicles:', error);
        }
    };

    const handleDelete = async (kjennemerke: string) => {
        try {
            await fetch(`/api/kjoeretoey/${kjennemerke}/fjern`, {
                method: 'DELETE',
            });
            fetchVehicles();
        } catch (error) {
            console.error('Error deleting vehicle:', error);
        }
    };

    const handleEdit = async (vehicle: Vehicle) => {
        if (editingId === vehicle.kjennemerke) {
            try {
                await fetch(`/api/kjoeretoey/${vehicle.kjennemerke}/oppdater`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(editForm),
                });
                setEditingId(null);
                setEditForm(null);
                fetchVehicles();
            } catch (error) {
                console.error('Error updating vehicle:', error);
            }
        } else {
            setEditingId(vehicle.kjennemerke);
            setEditForm(vehicle);
        }
    };

    // Add newVehicle state
    const [newVehicle, setNewVehicle] = useState<Vehicle>({
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
            await fetch(`/api/kjoeretoey/${newVehicle.kjennemerke}/opprett`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newVehicle),
            });
            setNewVehicle({
                kjennemerke: '',
                kjoeretoeytype: Kjoeretoeytype.PERSONBIL,
                egenvekt: '',
                totalvekt: '',
                drivstoff: Drivstoff.BENSIN,
                foerstegangsregistreringsdato: ''
            });
            fetchVehicles();
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
            {vehicles.map((vehicle) => (
                <tr key={vehicle.kjennemerke}>
                    <td>{vehicle.kjennemerke}</td>
                    <td>
                        {editingId === vehicle.kjennemerke ? (
                            <select
                                value={editForm?.kjoeretoeytype}
                                onChange={(e) =>
                                    setEditForm({...editForm!, kjoeretoeytype: e.target.value})
                                }
                            >
                                {Object.values(Kjoeretoeytype).map((type) => (
                                    <option key={type} value={type}>
                                        {type}
                                    </option>
                                ))}
                            </select>
                        ) : (
                            vehicle.kjoeretoeytype
                        )}
                    </td>
                    <td>
                        {editingId === vehicle.kjennemerke ? (
                            <input
                                value={editForm?.egenvekt}
                                onChange={(e) =>
                                    setEditForm({...editForm!, egenvekt: e.target.value})
                                }
                            />
                        ) : (
                            vehicle.egenvekt
                        )}
                    </td>
                    <td>
                        {editingId === vehicle.kjennemerke ? (
                            <input
                                value={editForm?.totalvekt}
                                onChange={(e) =>
                                    setEditForm({...editForm!, totalvekt: e.target.value})
                                }
                            />
                        ) : (
                            vehicle.totalvekt
                        )}
                    </td>
                    <td>
                        {editingId === vehicle.kjennemerke ? (
                            <select
                                value={editForm?.drivstoff}
                                onChange={(e) =>
                                    setEditForm({...editForm!, drivstoff: e.target.value})
                                }
                            >
                                {Object.values(Drivstoff).map((fuel) => (
                                    <option key={fuel} value={fuel}>
                                        {fuel}
                                    </option>
                                ))}
                            </select>
                        ) : (
                            vehicle.drivstoff
                        )}
                    </td>

                    <td>
                        {editingId === vehicle.kjennemerke ? (
                            <input
                                type="date"
                                value={editForm?.foerstegangsregistreringsdato}
                                onChange={(e) =>
                                    setEditForm({
                                        ...editForm!,
                                        foerstegangsregistreringsdato: e.target.value,
                                    })
                                }
                            />
                        ) : (
                            vehicle.foerstegangsregistreringsdato
                        )}
                    </td>
                    <td>
                        <button onClick={() => handleEdit(vehicle)}>
                            {editingId === vehicle.kjennemerke ? 'Save' : 'Edit'}
                        </button>
                        <button onClick={() => handleDelete(vehicle.kjennemerke)}>
                            Delete
                        </button>
                    </td>
                </tr>
            ))}
            <tr>
                <td>
                    <input
                        value={newVehicle.kjennemerke}
                        onChange={(e) => setNewVehicle({...newVehicle, kjennemerke: e.target.value})}
                        placeholder="Kjennemerke"
                    />
                </td>
                <td>
                    <select
                        value={newVehicle.kjoeretoeytype}
                        onChange={(e) => setNewVehicle({...newVehicle, kjoeretoeytype: e.target.value})}
                    >
                        {Object.values(Kjoeretoeytype).map((type) => (
                            <option key={type} value={type}>{type}</option>
                        ))}
                    </select>
                </td>
                <td>
                    <select
                        value={newVehicle.drivstoff}
                        onChange={(e) => setNewVehicle({...newVehicle, drivstoff: e.target.value})}
                    >
                        {Object.values(Drivstoff).map((fuel) => (
                            <option key={fuel} value={fuel}>{fuel}</option>
                        ))}
                    </select>
                </td>
                <td>
                    <input
                        value={newVehicle.egenvekt}
                        onChange={(e) => setNewVehicle({...newVehicle, egenvekt: e.target.value})}
                        placeholder="Egenvekt"
                    />
                </td>
                <td>
                    <input
                        value={newVehicle.totalvekt}
                        onChange={(e) => setNewVehicle({...newVehicle, totalvekt: e.target.value})}
                        placeholder="Totalvekt"
                    />
                </td>
                <td>
                    <input
                        type="date"
                        value={newVehicle.foerstegangsregistreringsdato}
                        onChange={(e) => setNewVehicle({...newVehicle, foerstegangsregistreringsdato: e.target.value})}
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
