import Request from '../helper/Request';

class BusStopStore {
    constructor() {
        this.busStops = [];
        this.listeners = [];
    }

    registerListener(listener) {
        this.listeners.push(listener);
    }

    launchListeners() {
        this.listeners.forEach(listener => listener(this.busStops));
    }

    getAll() {
        Request.getAllBusStop().then(busStops => {
            this.busStops = busStops;
            this.launchListeners();
        });
    }

    getFiltered(latitude, longitude, radius) {
        Request.getFilteredBusStop(latitude, longitude, radius).then(busStops => {
            this.busStops = busStops;
            this.launchListeners();
        })
    }

    removeAll() {
        this.busStops = [];
        this.launchListeners();
    }
}

const busStopStore = new BusStopStore();

export default {
    registerListener: listener => busStopStore.registerListener(listener),
    getAll: () => busStopStore.getAll(),
    removeAll: () => busStopStore.removeAll(),
    getFiltered: (latitude, longitude, radius) => busStopStore.getFiltered(latitude, longitude, radius)
};