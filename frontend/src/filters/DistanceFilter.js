import React from 'react';
import {Row, Button, Input} from 'react-materialize';

import BusStopStore from '../stores/BusStop';
import SchoolStore from '../stores/School';
import ParkingRelaisStore from '../stores/ParkingRelais';

export default class DistanceFilter extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showBusStops: false,
            showSchools: false,
            showParkingRelais: false,
            distance: ''
        };

        this.onChangeBusStop = this.onChangeBusStop.bind(this);
        this.onChangeSchool = this.onChangeSchool.bind(this);
        this.onChangeParkingRelais = this.onChangeParkingRelais.bind(this);
        this.onChangeDistance = this.onChangeDistance.bind(this);
        this.onFilter = this.onFilter.bind(this);
    }

    onChangeBusStop() {
        this.setState({showBusStops: !this.state.showBusStops});
    }

    onChangeSchool() {
        this.setState({showSchools: !this.state.showSchools});
    }

    onChangeParkingRelais() {
        this.setState({showParkingRelais: !this.state.showParkingRelais});
    }

    onChangeDistance(e) {
        this.setState({distance: e.target.value});
    }

    transformPosition(position) {
        if(!this.isUsableField(position)) {
            return '';
        }

        if(typeof position === 'string') {
            position = parseFloat(position);
        }

        return position.toFixed(3);
    }

    isUsableField(field) {
        return field !== undefined && field !== null && field !== '';
    }

    onFilter() {
        if(!this.checkCanFilter()) {
            return;
        }

        BusStopStore.removeAll();
        ParkingRelaisStore.removeAll();
        SchoolStore.removeAll();

        if(this.state.showSchools) {
            SchoolStore.getFiltered(this.props.selectedLatitude.toFixed(3), this.props.selectedLongitude.toFixed(3), this.state.distance);
        }

        if(this.state.showBusStops) {
            BusStopStore.getFiltered(this.props.selectedLatitude.toFixed(3), this.props.selectedLongitude.toFixed(3), this.state.distance);
        }

        if(this.state.showParkingRelais) {
            ParkingRelaisStore.getFiltered(this.props.selectedLatitude.toFixed(3), this.props.selectedLongitude.toFixed(3), this.state.distance);
        }
    }

    checkCanFilter() {
        return this.isUsableField(this.state.distance) && 
            this.isUsableField(this.props.selectedLatitude) && 
            this.isUsableField(this.props.selectedLongitude);
    }

    render() {
        return (
            <React.Fragment>
                <Row>
                    Selected latitude : {this.transformPosition(this.props.selectedLatitude)} <br />
                    Selected longitude : {this.transformPosition(this.props.selectedLongitude)}
                </Row>
                <Row>
                    <Input type="number" label="Distance" min="0.01" step="0.01" onChange={this.onChangeDistance} value={this.state.distance} />
                </Row>
                <Row>
                    <Input type="checkbox" label="Show schools" checked={this.state.showSchools} onChange={this.onChangeSchool} />
                </Row>
                <Row>
                    <Input type="checkbox" label="Show bus stops" checked={this.state.showBusStops} onChange={this.onChangeBusStop} />
                </Row>
                <Row>
                    <Input type="checkbox" label="Show parking relais" checked={this.state.showParkingRelais} onChange={this.onChangeParkingRelais} />
                </Row>
                <Row>
                    <Button waves='light' disabled={!this.checkCanFilter()} onClick={this.onFilter}>Filter</Button>
                </Row>
            </React.Fragment>
        );
    }
}