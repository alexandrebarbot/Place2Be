import React from 'react';
import {Collapsible, CollapsibleItem} from 'react-materialize';

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
            <Collapsible accordion onSelect={this.onChangeTabs}>
                <CollapsibleItem header='General view' icon='filter_drama'>
                    Lorem ipsum dolor sit amet.
                </CollapsibleItem>
                <CollapsibleItem header='Filter view' icon='place'>
                    <GlobalFilter />
                </CollapsibleItem>
            </Collapsible>
        );
    }
}