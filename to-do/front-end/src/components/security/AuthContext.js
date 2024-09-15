import { createContext, useState, useContext } from "react";
import { executeBasicAuthService } from "../api/TasksApiService";
import { apiClient } from "../api/ApiClient";

export const AuthContext = createContext()

export const useAuth = ()=> useContext(AuthContext);


const AuthProvider = ({children}) => {   
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [username, setUsername] = useState()
    const [token, setToken] = useState()

        const login = async (username, password) =>{
            
            const basicToken = 'Basic ' + window.btoa((username+":"+password))

            try{
                const res = await executeBasicAuthService(basicToken)
                if(res.status===200){
                    setAuthenticated(true)
                    setToken(basicToken)
                    setUsername({username: username, password: password})

                    apiClient.interceptors.request.use(config => {
                        config.headers.Authorization=basicToken
                        return config
                    })
                    return true
                } else{
                    logout()
                    return false
                }
            }catch(e){
                logout()
                return false
            }
        }

    
    const logout = () => {
        setAuthenticated(false)
        setToken(null)
        setUsername(null)
    }

    return (
        <AuthContext.Provider value={{ isAuthenticated, setAuthenticated, login, logout, username, setUsername, token}}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;