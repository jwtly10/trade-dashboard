import axios from 'axios'

const endpoint = '/accounts'

class AccountService{
    getAccounts(){
        return axios.get( import.meta.env.VUE_APP_DASHBOARD_API_URL + endpoint + '/getAccounts')
    }
}

export default new AccountService()
