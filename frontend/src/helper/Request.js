const baseUrl = 'http://159.89.4.245:8080';

const Request = {
    getAllCity: () => fetch(baseUrl + '/city/all').then(result => result.json()),
    
    getAllBusStop: () => fetch(baseUrl + '/bus_stop/all').then(result => result.json()),

    getFilteredBusStop: (latitude, longitude, radius) => 
        fetch(baseUrl + `/bus_stop/around?latitude=${latitude}&longitude=${longitude}&radius=${radius}`)
        .then(result => result.json())
};

export default Request;