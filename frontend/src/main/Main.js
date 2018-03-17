import React from 'react';
import {Row, Col} from 'react-materialize';
import {Map, TileLayer} from 'react-leaflet';

import Filter from './Filter';
import Request from '../helper/Request';
import BusStop from '../mapElements/BusStop';

const position = [49.711622, 6.131935];

export default class Main extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            selectedLatitude: null,
            selectedLongitude: null
        };

        this.onMapClick = this.onMapClick.bind(this);
    }

    componentDidMount() {
        Request.getAllCity().then(cities => this.setState({cities}));
    }

    onMapClick(e) {
        this.setState({
            selectedLatitude: e.latlng.lat,
            selectedLongitude: e.latlng.lng
        });
    }

    render() {
        return (
            <main>
                <Row>
                    <Col s={3}>
                        <Filter selectedLatitude={this.state.selectedLatitude} selectedLongitude={this.state.selectedLongitude} />
                    </Col>
                    <Col s={9}>
                        <Map center={position} zoom={10} style={{height: 800}} onClick={this.onMapClick}>
                            <TileLayer
                                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                                attribution='<a href="http://osm.org">OpenStreetMap</a>' />

                            <BusStop />
                        </Map>
                    </Col>
                </Row>
            </main>
        );
    }
}
    