import { BrowserRouter, Navigate, Route, Routes} from "react-router-dom"
import Login from "../login/Login"
import Welcome from "../welcome/Welcome"
import ListTodos from "../listTodos/ListTodos";
import Header from "../header/Header"
import { useAuth } from "../security/AuthContext";
import TodoUpdate from "../todoUpdate/TodoUpdate";
const TodoApp = () => {

    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated
    
    const AuthenticatedRoute = ({children}) => {
        if(isAuthenticated) return children;

        return <Navigate to="/login"/>
    }

    return (
        <div className="container">
                <BrowserRouter>
                    <Header/>
                        <Routes>
                            <Route path="/" element={<Login/>}/>
                            <Route path="/login" element={<Login/>}/>
                            <Route path="/welcome" element={
                                <AuthenticatedRoute>
                                    <Welcome />
                                </AuthenticatedRoute>
                            }/>

                            <Route path="/todo/:id" element={
                                <AuthenticatedRoute>
                                    <TodoUpdate />
                                </AuthenticatedRoute>
                            } />

                            <Route path="/todos" element={
                                <AuthenticatedRoute>
                                    <ListTodos/>
                                </AuthenticatedRoute>
                            }/>

                            <Route path="*" element={<div><h1>PÁGINA NÃO ENCONTRADA</h1><p>Erro 404 Página não encontrada</p></div>}/>
                        </Routes>
                </BrowserRouter>
        </div>
    )
}

export default TodoApp