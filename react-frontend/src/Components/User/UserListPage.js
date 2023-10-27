import React, { useState, useEffect } from 'react'
import axios from 'axios'

const UserListPage = () => {
  const [users, setUsers] = useState([])

  useEffect(() => {
    console.log(`List: ${localStorage.getItem('jwtToken')}`)
    // Fetch user data from the protected API endpoint
    axios
      .get('http://localhost:8080/home/users', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('jwtToken')}`,
        },
      })
      .then((response) => {
        setUsers(response.data)
      })
      .catch((error) => {
        console.error('Failed to fetch user data:', error)
        // Handle errors, e.g., token expiration
      })
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
