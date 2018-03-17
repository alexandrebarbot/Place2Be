import React from 'react';
import {Collapsible, CollapsibleItem} from 'react-materialize';

import GlobalFilter from '../filters/GlobalFilters';
import DistanceFilter from '../filters/DistanceFilter';
import BusStopStore from '../stores/BusStop';
import ParkingRelaisStore from '../stores/ParkingRelais';
import SchoolStore from '../stores/School';
import PopulationStore from '../stores/Population';
import RealEstatePrice from '../stores/RealEstatePrice';

export default class Filter extends React.Component {
    constructor(props) {
        super(props);

        this.selectedTab = 0;

        this.onResetMarkers = this.onResetMarkers.bind(this);
    }

    onResetMarkers(e) {
        if(!Number.isInteger(e) || e === this.selectedTab) {
            return;
        }

        this.selectedTab = e;

        BusStopStore.removeAll();
        ParkingRelaisStore.removeAll();
        SchoolStore.removeAll();
        PopulationStore.removeAll();
        RealEstatePrice.removeAll();

        this.props.onResetMarkers();
    }

    render() {
        return (
            <Collapsible accordion defaultActiveKey={0} onSelect={this.onResetMarkers}>
                <CollapsibleItem header='General view' icon='filter_drama'>
                    <GlobalFilter />
                </CollapsibleItem>
                <CollapsibleItem header='Filter view' icon='place'>
                    <DistanceFilter selectedLatitude={this.props.selectedLatitude} selectedLongitude={this.props.selectedLongitude} />
                </CollapsibleItem>
            </Collapsible>
        );
    }
}