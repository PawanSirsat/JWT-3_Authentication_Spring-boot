import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom'

const UserListPage = () => {
  const [users, setUsers] = useState([])
  const navigate = useNavigate()

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = localStorage.getItem('jwtToken')
        if (token) {
          const response = await axios.get('http://localhost:8080/home/users', {
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`,
            },
          })
          if (response.status === 200) {
            setUsers(response.data)
          } else {
            console.log('200')
          }
        } else {
          console.log('no tokken')
          navigate('/login')
        }
      } catch (error) {
        console.error('Failed to fetch user data:', error.toJSON().message)
        // Handle errors, e.g., token expiration
      }
    }

    fetchUserData()
  }, [])

  return (
    <div>
      <h2>User List</h2>
      <ul>
        {users.map((user) => (
          <li key={user.id}>{user.email}</li>
        ))}
      </ul>
    </div>
  )
}

export default UserListPage
