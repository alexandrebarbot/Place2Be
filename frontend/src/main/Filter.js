import React from 'react';
import {Tabs, Tab} from 'react-materialize';

import GlobalFilter from '../filters/GlobalFilters';
import BusStopStore from '../stores/BusStop';
import ParkingRelaisStore from '../stores/ParkingRelais';
import SchoolStore from '../stores/School';
import PopulationStore from '../stores/Population';
import RealEstatePrice from '../stores/RealEstatePrice';

export default class Filter extends React.Component {
    constructor(props) {
        super(props);

        this.state = {};

        this.onChangeTabs = this.onChangeTabs.bind(this);
    }

    onChangeTabs(e) {
        BusStopStore.removeAll();
        ParkingRelaisStore.removeAll();
        SchoolStore.removeAll();
        PopulationStore.removeAll();
        RealEstatePrice.removeAll();
    }

    render() {
        return (
            <Tabs className='tab-demo z-depth-1' onChange={this.onChangeTabs}>
                <Tab title="Global view"><GlobalFilter /></Tab>
                <Tab title="Filter view" active><p>1</p></Tab>
            </Tabs>
        );
    }
}