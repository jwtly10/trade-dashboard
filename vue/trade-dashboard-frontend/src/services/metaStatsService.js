import axios from 'axios'

const endpoint = '/metastats'

class metaStatsService {
    getStats(accountKey) {
        return axios.get(import.meta.env.VITE_DASHBOARD_API_URL + endpoint + '/getMetrics/' + accountKey)
    }
}


export default new metaStatsService()