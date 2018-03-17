import React from 'react';
import {Marker, Popup} from 'react-leaflet';

import {GreyIcon} from '../helper/LeafletIcons';
import SchoolStore from '../stores/School';

export default class School extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            schools: []
        };

        SchoolStore.registerListener(schools => this.setState({schools}));
    }

    render() {
        return (
            this.state.schools.map((school, index) =>
                <Marker position={[school.longitude, school.latitude]} key={index} icon={GreyIcon}>
                    <Popup>
                        <span>
                            Name : {school.name} <br />
                            Latitude : {school.latitude} <br />
                            Longitude : {school.longitude}
                        </span>
                    </Popup>
                </Marker>
            )
        );
    }
}