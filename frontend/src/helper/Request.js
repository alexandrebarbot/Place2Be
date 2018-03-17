const baseUrl = 'http://159.89.4.245:8080';

const Request = {
    getAllCity: () => fetch(baseUrl + '/city/all').then(result => result.json()),
    
    getAllBusStop: () => fetch(baseUrl + '/bus_stop/all').then(result => result.json()),

    getFilteredBusStop: (latitude, longitude, radius) => 
        fetch(baseUrl + `/bus_stop/around?latitude=${latitude}&longitude=${longitude}&radius=${radius * 1000}`)
        .then(result => result.json()),

    getAllParkingRelais: () => fetch(baseUrl + '/parking_relais/all').then(result => result.json()),

    getAllPopulation: () => fetch(baseUrl + '/population/all').then(result => result.json()),

    getAllRealEstatePrice: () => fetch(baseUrl + '/real_estate_price/all').then(result => result.json()),

    getAllSchool: () => fetch(baseUrl + '/school/all').then(result => result.json())
};

export default Request;