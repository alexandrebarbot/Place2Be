import React from 'react';
import {Row, Col, Input} from 'react-materialize';

import BusStopStore from '../stores/BusStop';
import ParkingRelaisStore from '../stores/ParkingRelais';
 
export default class Filter extends React.Component {
    constructor(pros) {
        super(pros);

        this.state = {
            showBusStops: false,
            showParkingrelais: false,
            distance: ''
        };

        this.onChangeBusStop = this.onChangeBusStop.bind(this);
        this.onChangeDistance = this.onChangeDistance.bind(this);
        this.onChangeParkingRelais = this.onChangeParkingRelais.bind(this);
    }

    onChangeDistance(e) {
        this.setState({distance: e.target.value});
    }

    showPosition(position) {
        if(!this.checkFieldUsability(position)) {
            return 'NULL';
        }

        if(typeof position === 'string') {
            position = parseFloat(position);
        }

        return position.toFixed(3);
    }

    onChangeBusStop() {
        let showBusStops = !this.state.showBusStops;

        this.setState({showBusStops}, () => {
            if(showBusStops) {
                this.getBusStop();
            }
            else {
                BusStopStore.removeAll();
            }
        });
    }

    getBusStop() {
        if(this.checkCanUseLocationFilters()) {
            BusStopStore.getFiltered(this.props.selectedLatitude, this.props.selectedLongitude, this.state.distance);
        }
        else {
            BusStopStore.getAll();
        }
    }

    onChangeParkingRelais() {
        let showParkingrelais = !this.state.showParkingrelais;

        this.setState({showParkingrelais}, () => {
            if(showParkingrelais) {
                ParkingRelaisStore.getAll();
            }
            else {
                ParkingRelaisStore.removeAll();
            }
        });
    }

    checkCanUseLocationFilters() {
        return this.checkFieldUsability(this.props.selectedLatitude) && 
            this.checkFieldUsability(this.props.selectedLongitude) && 
            this.checkFieldUsability(this.state.distance);
    }

    checkFieldUsability(field) {
        return field !== undefined && field !== null && field !== false;
    }

    render() {
        return (
            <React.Fragment>
                <Row>
                    <Col s={6}>
                        Selected position : <br />
                        Latitude : {this.showPosition(this.props.selectedLatitude)} <br />
                        Longitude : {this.showPosition(this.props.selectedLongitude)}
                    </Col>
                    <Col s={6}>
                        <Input type="number" label="Distance" min="1" step="1" value={this.state.distance} onChange={this.onChangeDistance} />
                    </Col>
                </Row>

                <Row>
                    <Input type="checkbox" label="Show bus stops" checked={this.state.showBusStops} onChange={this.onChangeBusStop} />
                </Row>

                <Row>
                    <Input type="checkbox" label="Show parking relais" checked={this.state.showParkingrelais} onChange={this.onChangeParkingRelais} />
                </Row>
            </React.Fragment>
        );
    }
}