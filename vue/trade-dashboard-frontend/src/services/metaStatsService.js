import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/v1/'

class metaStatsService{
    getStats(accountKey){
       return axios.get(BASE_URL + "metastats/getMetrics/" + accountKey)
    }
}


export default new metaStatsService()