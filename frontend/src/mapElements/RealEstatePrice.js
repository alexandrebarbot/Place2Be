import React from 'react';
import {Marker, Popup} from 'react-leaflet';

import {VioletIcon} from '../helper/LeafletIcons';
import RealEstatePriceStore from '../stores/RealEstatePrice';

export default class RealEstatePrice extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            realEstatePrices: []
        };

        RealEstatePriceStore.registerListener(realEstatePrices => this.setState({realEstatePrices}));
    }

    render() {
        return (
            this.state.realEstatePrices
                .filter(realEstatePrice => realEstatePrice.city !== null)
                .map((realEstatePrice, index) =>
                <Marker position={[realEstatePrice.city.longitude, realEstatePrice.city.latitude]} key={index} icon={VioletIcon}>
                    <Popup>
                        <span>
                            City : {realEstatePrice.city.name} <br />
                            Moyenne maison : {realEstatePrice.moy_price_house} € <br />
                            Moyenne maison m² : {realEstatePrice.moy_price_square_meter_house} € <br />
                            Moyenne appartement : {realEstatePrice.moy_price_flat} € <br />
                            Moyenne appartement m² : {realEstatePrice.moy_price_square_meter_flat} € <br />
                            Latitude : {realEstatePrice.city.latitude} <br />
                            Longitude : {realEstatePrice.city.longitude}
                        </span>
                    </Popup>
                </Marker>
            )
        );
    }
}