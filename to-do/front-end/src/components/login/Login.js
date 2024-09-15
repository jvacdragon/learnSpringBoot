/* eslint-disable no-template-curly-in-string */
import {  useRef, useState } from "react"
import { useNavigate } from "react-router-dom";
import classes from "./Login.module.css"
import {  useAuth } from "../security/AuthContext";

const Login = (props) => {
    const username = useRef();
    const password = useRef();

    const [message, SetMesssage] = useState("");
    const navigate = useNavigate()
    const authContext = useAuth()

    const submitForm = async (e)=> {
        e.preventDefault();
        
        if(await authContext.login(username.current.value, password.current.value)){
            navigate(`/welcome`)
        }
        else{
            SetMesssage("Usuário não encontrado");
        }
    }

    return (
        <div className={classes.form}>
            <form onSubmit={submitForm} className={classes.loginForm}>
                <div>
                    <label>Username: </label> <input type="text" ref={username} />
                </div>
                <div>
                    <label>Password: </label> <input type="password" ref={password} />
                </div>


                {message}
            <button type="submit" className={classes.loginBtn}>Login</button>
            </form>
        </div>
    )
}

export default Login