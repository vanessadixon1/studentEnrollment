import axios from 'axios';

const getAuthConfig = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`
    }
})

export const getStudents = async() => {
    try {
       return await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/students`,
            getAuthConfig()
           )
    } catch (e) {
        throw e;
    }
}

export const addStudent = async(student) => {
    try {
        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/add`,student)
    }catch(err) {
        throw err
    }
}

export const deleteStudent = async({id}) => {
    try {
        return await axios.delete(`${import.meta.env.VITE_API_BASE_URL}/api/v1/delete/${id}`,
            getAuthConfig()
            )
    }catch(err) {
        throw err
    }
}

export const updateStudent = async({id}, student) => {
    try {
        return await axios.put(`${import.meta.env.VITE_API_BASE_URL}/api/v1/update/${id}`,
            student,
            getAuthConfig()
            )
    }catch(err) {
        throw err
    }
}

export const login = async(usernamePassword) => {
    try {
        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/auth/login`,
            usernamePassword
        )
    }catch(err) {
        throw err
    }
}
