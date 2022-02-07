import axios from 'axios'

const USER_API_URL = 'http://localhost:9091/api/v1'

class UserDataService {

    retrieveAllUsers() {

        return axios.get(`${USER_API_URL}/users`);
    }

    retrieveUser(id) {

        return axios.get(`${USER_API_URL}/users/${id}`);
    }

    deleteUser(id) {

        return axios.delete(`${USER_API_URL}/user_delete/${id}`);
    }

    updateUser(id, user) {

        return axios.put(`${USER_API_URL}/users/${id}`, user);
    }

    createUser(user) {

        return axios.post(`${USER_API_URL}/save_user`, user);
    }  
    
    login(user) {

        return axios.post(`${USER_API_URL}/login`, user);
    }

    findBySearchData(name,email) {
        // return axios.get(`${USER_API_URL}/search_users?data=${name,email}`);

         return axios
                .get(`${USER_API_URL}/search_users`, {
                    params: {
                        'name': name,
                        'email': email,
                    }
                })
    }
}

export default new UserDataService()