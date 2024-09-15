import { useNavigate, useParams } from "react-router-dom"
import { useAuth } from "../security/AuthContext"
import { retrieveTaskById, updateTaskApi } from "../api/TasksApiService"
import { useEffect, useState } from "react"

const TodoUpdate = () => {

    const {id} = useParams()
    const auth = useAuth()
    const username = auth.username
    const navigate = useNavigate()

    const [task, setTask] = useState()
    const [description, setDescription] = useState('')
    const [date, setDate] = useState('')


    useEffect(() => {
        currentTask(username, id)
        
    }, [id])

    const [message,setMessage] = useState("")

    const currentTask = async (username, id) => {
        const res = await retrieveTaskById(username, id)
        setTask(res.data)
        setDate(res.data.date)
        setDescription(res.data.description)
    }

    const onSubmit = async (e) => {
        try{
            e.preventDefault();            
            await updateTaskApi({username: task.username, description: description, date: date, done: task.done, id:task.id})
            setMessage("")
            navigate(`/todos`)
        }catch(err){
            const errors = err.response.data.errors
            setMessage("Erro ao adicionar tarefa: \n" + errors.map((err, i) => i<errors.length - 1 ? `${err};\n ` : err))
        }
    }

    return (
        <div className="container">
            <h1>Enter Todo details</h1>
            
            <div>
                <form onSubmit={onSubmit}>
                    <fieldset className="form-group">
                        <label>Description</label>
                        <input onChange={(e) => setDescription(e.target.value)} className="form-control" type="text" value={description}/>
                    </fieldset>

                    <fieldset className="form-group">
                        <label>Date</label>
                        <input onChange={(e) => setDate(e.target.value)} className="form-control" type="date" value={date}/>
                    </fieldset>

                </form>
                {message}
            </div>
        </div>
    )
}

export default TodoUpdate