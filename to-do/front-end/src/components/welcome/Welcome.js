import { Link } from "react-router-dom"
import { useAuth } from "../security/AuthContext";
import { useState } from "react";
import { postTaskApi } from "../api/TasksApiService";

const Welcome = (props) => {

    const authContext = useAuth()
    const userCredentials = authContext.username

    const [description, setDescription] = useState('')
    const [date, setDate] = useState('')
    const [message, setMessage] = useState('')

    const onSubmit = async (e) => {
        try{
            e.preventDefault();
            const post = await postTaskApi({username: userCredentials, description: description, date: date, done: false})

            setMessage("Sua tarefa foi adicionada à lista de tarefas")
            

            return post

        }catch(err){
            const errors = err.response.data.errors
            setMessage("Erro ao adicionar tarefa: \n" + errors.map((err, i) => i<errors.length - 1 ? `${err};\n ` : err))
        }
    }

    return(
        <div className="WelcomeComponent">
            <h1>
            Welcome {userCredentials.username}</h1>
            Manage your todos - <Link to="/todos">Go here</Link>

            
            <div className="my-5">
                <h1>Create a new todo</h1>
                <form onSubmit={onSubmit}>
                    <fieldset className="form-group">
                        <label>Description</label>
                        <input onChange={(e) => setDescription(e.target.value)} className="form-control" type="text" value={description}/>
                    </fieldset>

                    <fieldset className="form-group">
                        <label>Date</label>
                        <input onChange={(e) => setDate(e.target.value)} className="form-control" type="date" value={date}/>
                    </fieldset>
                    <button className="btn btn-success m-5" type="submit">
                        Submit
                    </button>
                </form>
                {message}
            </div>
        </div>
    )
}

export default Welcome