import React, { useEffect, useState } from "react"
import { retrieveTasks, deleteTaskApi, updateTaskApi } from "../api/TasksApiService"
import { useAuth } from "../security/AuthContext"
import {useNavigate } from "react-router-dom"

const ListTodos = (props) => {

    const authContext = useAuth()
    const username = authContext.username
    const navigate = useNavigate()

    const [tasks, setTasks] = useState([]);

    useEffect( () => {
        refreshTasks(username)
    }, [tasks, username])
    
    const refreshTasks = async (username) =>{
        try {
            const res = await retrieveTasks(username)
            setTasks(res.data)
        } catch (error) {
            console.log(error)
        }
    }

    const deleteTask = (username, id) => {
        try {
            deleteTaskApi(username, id)
        } catch (error) {
            console.log(error)
        }
    }

    const updateTask = (id) => {
        navigate(`/todo/${id}`)
    }

    const toggleIsDone = (data) => {
        data.done = !data.done
        updateTaskApi(data)
    }
    return (
        <div className="container">
            <h1>Things you want to do</h1>
            <table className="table">
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Description</td>
                        <td>Done</td>
                        <td>Date</td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>

                <tbody>
                    {tasks.map(t => 
                        <tr key={t.id}>
                            <td>{t.id}</td>
                            <td>{t.description}</td>
                            <td><button
                                onClick={() => toggleIsDone({username: t.username, description: t.description, date: t.date, done: t.done, id:t.id})}
                            className={t.done ? "btn btn-success" : "btn btn-danger"}>{t.done.toString()}</button></td>
                            <td>{t.date}</td>
                            <td><button className="btn btn-success" onClick={() =>  updateTask(t.id)}> UPDATE </button></td>
                            <td><button className="btn btn-danger" onClick={() => deleteTask(t.username.username, t.id)}> DELETE </button></td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

export default ListTodos