import axios from 'axios'

const endpoint = '/auth/authenticate'

class AuthenticationService {
    authenticate(username, password) {
        return axios.post(
            import.meta.env.VITE_DASHBOARD_API_URL + endpoint,
            {
                email: username,
                password: password
            }
        ).catch(function (error) {
            return error.toJSON()
        })
    }
}

export default new AuthenticationService()
