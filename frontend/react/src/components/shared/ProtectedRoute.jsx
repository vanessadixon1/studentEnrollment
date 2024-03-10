import {useEffect} from "react";
import {useNavigate} from "react-router-dom";
import {useAuth} from "../context/AuthContext.jsx";

const ProtectedRoute = ({children}) => {
    const navigate = useNavigate()
    const {isStudentAuthenticated} = useAuth();
    useEffect(() => {

        if (!isStudentAuthenticated()) {
            navigate("/")
        }
    })
    return isStudentAuthenticated() ? children : "";
}

export default ProtectedRoute;