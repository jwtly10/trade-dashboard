import axios from 'axios'

const endpoint = '/overview'
const token = getCookie('Token')
const config = {
    headers: {'Authorization': `Bearer ${token}`}
};

class overviewService {
    getOverview(accountKey) {
        return axios.get(import.meta.env.VITE_DASHBOARD_API_URL + endpoint + "/getOverview", config)

    }
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

export default new overviewService()