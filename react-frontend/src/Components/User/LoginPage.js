import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom' // Import useNavigate
import './login.css'

const LoginPage = (props) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const navigate = useNavigate() // Use useNavigate

  const handleLogin = (e) => {
    e.preventDefault()
    axios
      .post('http://localhost:8080/auth/login', { email, password })
      .then((response) => {
        const token = response.data.jwtToken
        localStorage.setItem('jwtToken', token)
        console.log('Token Generated : ' + token)
        props.setIsAuthenticated(true)
        navigate('/home')
      })
      .catch((error) => {
        console.error('Login failed:', error)
      })
  }

  return (
    <div className='main-container'>
      <div className='content'>
        <h2 className='mb-4'>Login Page</h2>
        <form onSubmit={handleLogin}>
          <div className='form-group'>
            <label htmlFor='email'>Email:</label>
            <input
              type='email'
              className='form-control'
              id='email'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className='form-group'>
            <label htmlFor='password'>Password:</label>
            <input
              type='password'
              className='form-control'
              id='password'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type='submit' className='btn btn-primary'>
            Login
          </button>
        </form>
      </div>
    </div>
  )
}

export default LoginPage
