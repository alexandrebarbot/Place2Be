import React from 'react';
import {Marker, Popup} from 'react-leaflet';

import {BlueIcon} from '../helper/LeafletIcons';
import BusStopStore from '../stores/BusStop';

export default class BusStop extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            busStops: []
        };

        BusStopStore.registerListener(busStops => this.setState({busStops}));
    }

    render() {
        return (
            this.state.busStops.map((busStop, index) =>
                <Marker position={[busStop.longitude, busStop.latitude]} key={index} icon={BlueIcon}>
                    <Popup>
                        <span>
                            Name : {busStop.stop_name} <br />
                            Latitude : {busStop.latitude} <br />
                            Longitude : {busStop.longitude}
                        </span>
                    </Popup>
                </Marker>
            )
        );
    }
}