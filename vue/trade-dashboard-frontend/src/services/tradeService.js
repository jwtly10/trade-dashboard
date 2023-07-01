import axios from 'axios'

const endpoint = '/trade/'

class TradeService {
    getTrades(accountID) {
        return axios.get(import.meta.env.VITE_DASHBOARD_API_URL + endpoint + accountID)
    }
}

export default new TradeService()