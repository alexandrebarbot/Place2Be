import React from 'react';
import {Marker, Popup} from 'react-leaflet';

import {OrangeIcon} from '../helper/LeafletIcons';
import PopulationStore from '../stores/Population';

export default class Population extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            populations: []
        };

        PopulationStore.registerListener(populations => this.setState({populations}));
    }

    render() {
        return (
            this.state.populations
                .filter(population => population.city !== null)
                .map((population, index) =>
                <Marker position={[population.city.longitude, population.city.latitude]} key={index} icon={OrangeIcon}>
                    <Popup>
                        <span>
                            City : {population.city.name} <br />
                            Count : {population.value} <br />
                            Year : {population.year} <br />
                            Latitude : {population.city.latitude} <br />
                            Longitude : {population.city.longitude}
                        </span>
                    </Popup>
                </Marker>
            )
        );
    }
}