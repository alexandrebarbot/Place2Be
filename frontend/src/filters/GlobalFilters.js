import React from 'react';
import {Row, Input} from 'react-materialize';

import BusStopStore from '../stores/BusStop';
import ParkingRelaisStore from '../stores/ParkingRelais';
import SchoolStore from '../stores/School';
import PopulationStore from '../stores/Population';
import RealEstatePrice from '../stores/RealEstatePrice';
 
export default class Filter extends React.Component {
    constructor(pros) {
        super(pros);

        this.state = {
            showBusStops: false,
            showParkingrelais: false,
            showSchools: false,
            showPopulations: false,
            showRealEstatePrice: false
        };

        this.onChangeBusStop = this.onChangeBusStop.bind(this);
        this.onChangeParkingRelais = this.onChangeParkingRelais.bind(this);
        this.onChangeSchool = this.onChangeSchool.bind(this);
        this.onChangePopulation = this.onChangePopulation.bind(this);
        this.onChangeRealEstatePrice = this.onChangeRealEstatePrice.bind(this);
    }

    onChangeBusStop() {
        let showBusStops = !this.state.showBusStops;

        this.setState({showBusStops}, () => {
            if(showBusStops) {
                BusStopStore.getAll();
            }
            else {
                BusStopStore.removeAll();
            }
        });
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

    onChangeSchool() {
        let showSchools = !this.state.showSchools;

        this.setState({showSchools}, () => {
            if(showSchools) {
                SchoolStore.getAll();
            }
            else {
                SchoolStore.removeAll();
            }
        });
    }

    onChangePopulation() {
        let showPopulations = !this.state.showPopulations;

        this.setState({showPopulations}, () => {
            if(showPopulations) {
                PopulationStore.getAll();
            }
            else {
                PopulationStore.removeAll();
            }
        });
    }

    onChangeRealEstatePrice() {
        let showRealEstatePrice = !this.state.showRealEstatePrice;

        this.setState({showRealEstatePrice}, () => {
            if(showRealEstatePrice) {
                RealEstatePrice.getAll();
            }
            else {
                RealEstatePrice.removeAll();
            }
        })
    }

    render() {
        return (
            <React.Fragment>
                <Row>
                    <Input type="checkbox" label="Show bus stops" checked={this.state.showBusStops} onChange={this.onChangeBusStop} />
                </Row>

                <Row>
                    <Input type="checkbox" label="Show parking relais" checked={this.state.showParkingrelais} onChange={this.onChangeParkingRelais} />
                </Row>

                <Row>
                    <Input type="checkbox" label="Show schools" checked={this.state.showSchools} onChange={this.onChangeSchool} />
                </Row>

                <Row>
                    <Input type="checkbox" label="Show populations" checked={this.state.showPopulations} onChange={this.onChangePopulation} />
                </Row>

                <Row>
                    <Input type="checkbox" label="Show real estate prices" checked={this.state.showRealEstatePrice} onChange={this.onChangeRealEstatePrice} />
                </Row>
            </React.Fragment>
        );
    }
}