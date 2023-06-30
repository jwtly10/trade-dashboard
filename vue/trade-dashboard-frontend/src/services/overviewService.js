import axios from 'axios'

const endpoint = '/overview'

class metaStatsService{
    getStats(accountKey){
        return axios.get(import.meta.env.VITE_DASHBOARD_API_URL + endpoint + "/getOverview" + accountKey)
    }
}

export default new metaStatsService()