import axios from 'axios'

const USER_API_URL = 'http://localhost:9091/api/v1'

class PostDataService {

    retrieveAllTasks() {

        return axios.get(`${USER_API_URL}/tasks`);
    }

    retrieveTask(id) {

        return axios.get(`${USER_API_URL}/tasks/${id}`);
    }

    deleteTask(id) {

        return axios.delete(`${USER_API_URL}/tasks/${id}`);
    }

    updateTask(id, task) {

        return axios.put(`${USER_API_URL}/tasks/${id}`, task);
    }

    createTask(task) {

        return axios.post(`${USER_API_URL}/save_task`, task);
    }  
    
    findBySearchData(search_data) {

        return axios.get(`${USER_API_URL}/search_tasks?data=${search_data}`);
    }

    downloadTask(task) {

        return axios.get(`${USER_API_URL}/exportCSV/`,task);
    }

    uploadTask(file) {

        return axios.post(`${USER_API_URL}/uploadCSV/`,file);
    }
}

export default new PostDataService()