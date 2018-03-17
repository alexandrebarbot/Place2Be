import React from 'react';
import {Marker, Popup} from 'react-leaflet';

import ParkingRelaisStore from '../stores/ParkingRelais';

export default class ParkingRelais extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            parkingRelais: []
        };

        ParkingRelaisStore.registerListener(parkingRelais => this.setState({parkingRelais}));
    }

    render() {
        return (
            this.state.parkingRelais.map((parkingRelais, index) => 
                <Marker position={[parkingRelais.longitude, parkingRelais.latitude]} key={index}>
                    <Popup>
                        <span>
                            Localisation : {parkingRelais.localisation} <br />
                            Places : {parkingRelais.places} <br />
                            Latitude : {parkingRelais.latitude} <br />
                            Longitude : {parkingRelais.longitude}
                        </span>
                    </Popup>
                </Marker>
            )
        );
    }
}