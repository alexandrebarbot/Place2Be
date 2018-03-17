import React from 'react';
import {Collapsible, CollapsibleItem, Row, Col, Input} from 'react-materialize';

import BusStopStore from '../stores/BusStop';
 
export default class Filter extends React.Component {
    constructor(pros) {
        super(pros);

        this.state = {
            showBusStops: false,
            distance: ''
        };

        this.onChangeBusStop = this.onChangeBusStop.bind(this);
        this.onChangeDistance = this.onChangeDistance.bind(this);
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
                BusStopStore.getAll();
            }
            else {
                BusStopStore.removeAll();
            }
        });
    }

    onChangeDistance(e) {
        this.setState({distance: e.target.value});
    }

    getBusStop() {
        if(this.checkFieldUsability(this.props.selectedLatitude) && this.checkFieldUsability(this.props.selectedLongitude) && 
            this.checkFieldUsability(this.state.distance)) {
                BusStopStore.getFiltered(this.props.selectedLatitude, this.props.selectedLongitude, this.state.distance);
        }
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
            </React.Fragment>
        );
    }
}

//<Collapsible>
//    <CollapsibleItem header='First' icon='filter_drama'>
//        <p>p</p>
//    </CollapsibleItem>
//
//    <CollapsibleItem header='Second' icon='place'>
//        <p>p</p>
//    </CollapsibleItem>
//
//    <CollapsibleItem header='Third' icon='whatshot'>
//        <p>p</p>
//    </CollapsibleItem>
//</Collapsible>