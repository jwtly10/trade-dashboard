import axios from 'axios'

const endpoint = '/overview'

class metaStatsService{
    getOverview(accountKey){
        return axios.get(import.meta.env.VITE_DASHBOARD_API_URL + endpoint + "/getOverview")
    }
}

export default new metaStatsService()