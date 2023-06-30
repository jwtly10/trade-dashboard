import axios from 'axios'

const endpoint = 'metastats'

class metaStatsService{
    getStats(accountKey){
        return axios.get(BASE_URL + "metastats/getMetrics/" + accountKey)
    }
}


export default new metaStatsService()