
import { MapSize } from "../config/config";
import { points } from "../mocks/mapMock";
import { useEffect, useState } from "react";

export const useMap = () => {
    const [rovers, setAllRovers] = useState([]);
    const [cur, setCur] = useState([0, 0, 0]);

    const generateRovers = (data) => {
        const rover = {
            id: data.roverId,
            name: "rover",
            shape: "circle",
            coords: [Math.round(data.x) * 5, Math.round(data.y)* 5, 20],
            preFillColor: "rgba(255, 102, 0, 0.5)",
            fillColor: "rgba(255, 102, 0, 0.5)",
            strokeColor: "rgba(255, 102, 0, 0.5)",
            lineWidth: 1,
        };

        if (rovers.length === 0) {
            const newRovers = rovers;
            newRovers.push(rover);
            setRovers(newRovers);
        } else {
            let flag = false;
            for (let i = 0; i < rovers.length; i++) {
                if (rovers[i].id === rover.id) {
                    const newRovers = rovers;
                    newRovers[i].coords = rover.coords;
                    setRovers(newRovers);
                    flag = true;
                }
            }
            if (!flag) {
                const newRovers = rovers;
                newRovers.push(rover);
                setRovers(newRovers);
            }
        }

        setCur(rover.coords);
    }

    const URL = '/PIA24096.jpeg';
    const MAP = {
        name: 'Mars Map',
        areas: rovers,
        center: [1098, 123],
    };

    const [map, setMap] = useState(MAP);

    const handleShowDescription = (area) => {
        console.log(area);
    }

    return {
        URL,
        MAP,
        map,
        setMap,
        rovers,
        handleShowDescription,
        generateRovers,
        setAllRovers,
        cur
    }
}