import Request from '../helper/Request';

class ParkingRelaisStore {
    constructor() {
        this.parkingRelais = [];
        this.listeners = [];
    }

    registerListener(listener) {
        this.listeners.push(listener);
    }

    launchListeners() {
        this.listeners.forEach(listener => listener(this.parkingRelais));
    }

    getAll() {
        Request.getAllParkingRelais().then(parkingRelais => {
            this.parkingRelais = parkingRelais;
            this.launchListeners();
        });
    }

    getFiltered(latitude, longitude, radius) {
        Request.getFilteredPakingRelais(latitude, longitude, radius).then(parkingRelais => {
            this.parkingRelais = parkingRelais;
            this.launchListeners();
        })
    }

    removeAll() {
        this.parkingRelais = [];
        this.launchListeners();
    }
}

const parkingrelaisStore = new ParkingRelaisStore();

export default {
    registerListener: listener => parkingrelaisStore.registerListener(listener),
    getAll: () => parkingrelaisStore.getAll(),
    removeAll: () => parkingrelaisStore.removeAll(),
    getFiltered: (latitude, longitude, radius) => parkingrelaisStore.getFiltered(latitude, longitude, radius)
};