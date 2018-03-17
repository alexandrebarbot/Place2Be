import Request from '../helper/Request';

class SchoolStore {
    constructor() {
        this.schools = [];
        this.listeners = [];
    }

    registerListener(listener) {
        this.listeners.push(listener);
    }

    launchListeners() {
        this.listeners.forEach(listener => listener(this.schools));
    }

    getAll() {
        Request.getAllSchool().then(schools => {
            this.schools = schools;
            this.launchListeners();
        });
    }

    removeAll() {
        this.schools = [];
        this.launchListeners();
    }
}

const schoolStore = new SchoolStore();

export default {
    registerListener: listener => schoolStore.registerListener(listener),
    getAll: () => schoolStore.getAll(),
    removeAll: () => schoolStore.removeAll()
};