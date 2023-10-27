// Signup.js
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom' // Import useNavigate

const Signup = () => {
  const navigate = useNavigate() // Use useNavigate

  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
  })

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    })
  }

  const handleSubmit = (e) => {
    e.preventDefault()

    // Send a POST request to the API
    fetch('http://localhost:8080/auth/create-user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok')
        }
        return response.json()
      })
      .then((data) => {
        console.error('SignUp Successfully', data)
        navigate('/login')
      })
      .catch((error) => {
        console.error('Fetch error:', error)
      })
  }

  return (
    <div>
      <h2>Signup</h2>
      <form onSubmit={handleSubmit}>
        <div className='form-group'>
          <label>Name:</label>
          <input
            type='text'
            name='name'
            value={formData.name}
            onChange={handleChange}
            className='form-control'
            required
          />
        </div>
        <div className='form-group'>
          <label>Email:</label>
          <input
            type='email'
            name='email'
            value={formData.email}
            onChange={handleChange}
            className='form-control'
            required
          />
        </div>
        <div className='form-group'>
          <label>Password:</label>
          <input
            type='password'
            name='password'
            value={formData.password}
            onChange={handleChange}
            className='form-control'
            required
          />
        </div>
        <button type='submit' className='btn btn-primary'>
          Sign Up
        </button>
      </form>
    </div>
  )
}

export default Signup
