import Request from '../helper/Request';

class PopulationStore {
    constructor() {
        this.populations = [];
        this.listeners = [];
    }

    registerListener(listener) {
        this.listeners.push(listener);
    }

    launchListeners() {
        this.listeners.forEach(listener => listener(this.populations));
    }

    getAll() {
        Request.getAllPopulation().then(populations => {
            this.populations = populations;
            this.launchListeners();
        });
    }

    removeAll() {
        this.populations = [];
        this.launchListeners();
    }
}

const populationStore = new PopulationStore();

export default {
    registerListener: listener => populationStore.registerListener(listener),
    getAll: () => populationStore.getAll(),
    removeAll: () => populationStore.removeAll()
};