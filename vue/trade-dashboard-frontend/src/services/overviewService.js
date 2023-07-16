import axios from 'axios'

const endpoint = '/overview'

class overviewService {
    getOverview(accountKey) {
        let token = getCookie('Token')
        let config = {
            headers: {'Authorization': `Bearer ${token}`}
        };
        return axios.get(import.meta.env.VITE_DASHBOARD_API_URL + endpoint + "/getOverview", config)

    }
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

export default new overviewService()