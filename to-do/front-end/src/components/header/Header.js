import React from "react";
import { Link, useParams } from "react-router-dom";
import { useAuth } from "../security/AuthContext";

const Header = (props) => {

    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated

    const logout = () =>{
        authContext.logout()
    }


    return (
            <header className="border-bottom border-light border-5 mb-5 p-2">
                <div className="container">
                    <div className="row">
                        <nav className="navbar navbar-expand-lg">
                            <a className="navbar-brand ms-2 fs-2 fw-bold text-black" href="https://github.com/jvacdragon" target="blank">JvGithub</a>
                            <div className="collapse navbar-collapse">
                                <ul className="navbar-nav">
                                    <li className="nav-item">
                                        {isAuthenticated && <Link className="nav-link" to="/welcome">Home</Link>}
                                    </li>
                                    <li className="nav-item">
                                        {isAuthenticated && <Link className="nav-link" to="/todos">Todos</Link>}
                                    </li>
                                </ul>
                            </div>
                            <ul className="navbar-nav">
                                    <li className="nav-item">
                                        {isAuthenticated && <Link className="nav-link" onClick={logout} to={"/"}>Logout</Link>}
                                    </li>
                                </ul>
                        </nav>
                    </div>
                </div>
            </header>
    )
}

export default Header;