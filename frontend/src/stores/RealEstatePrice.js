import Request from '../helper/Request';

class RealEstatePriceStore {
    constructor() {
        this.realEstatePrices = [];
        this.listeners = [];
    }

    registerListener(listener) {
        this.listeners.push(listener);
    }

    launchListeners() {
        this.listeners.forEach(listener => listener(this.realEstatePrices));
    }

    getAll() {
        Request.getAllRealEstatePrice().then(realEstatePrices => {
            this.realEstatePrices = realEstatePrices;
            this.launchListeners();
        });
    }

    removeAll() {
        this.realEstatePrices = [];
        this.launchListeners();
    }
}

const realEstatePriceStore = new RealEstatePriceStore();

export default {
    registerListener: listener => realEstatePriceStore.registerListener(listener),
    getAll: () => realEstatePriceStore.getAll(),
    removeAll: () => realEstatePriceStore.removeAll()
};