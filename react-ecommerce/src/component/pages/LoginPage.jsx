import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../service/ApiService";
import '../../style/register.css'


const LoginPage = () => {

    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    const [message, setMessage] = useState(null);
    const navigate = useNavigate();


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await ApiService.loginUser(formData);
            if (response.status === 200) {
                setMessage("User Successfully Loged in");
                //localStorage에 로그인 정보를 저장하는 코드. localStorage는 브라우저 안에 데이터를 저장할 수 있는 공간. 패이지가 새로고침돼도 사라지지 않고 유지. 키=값 형태로 저장함
                localStorage.setItem('token', response.token);
                localStorage.setItem('role', response.role);
                setTimeout(() => {
                    navigate("/profile")
                }, 4000)
            }
        } catch (error) {
            setMessage(error.response?.data.message || error.message || "unable to Login a user");
        }
    }

    return (
        <div className="register-page">
            <h2>Login</h2>
            {message && <p className="message">{message}</p>}
            <form onSubmit={handleSubmit}>
                <label>Email: </label>
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required />
                    
                <label>Password: </label>
                <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required />

                    <button type="submit">Login</button>
                    
                    <p className="register-link">
                        Don't have an account? <a href="/register">Register</a>
                    </p>
            </form>
        </div>
    )
}

export default LoginPage;