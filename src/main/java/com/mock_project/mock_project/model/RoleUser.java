// package com.mock_project.mock_project.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "Role_User")
// public class RoleUser {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "Role_id")
//     private Role role;

//     @ManyToOne
//     @JoinColumn(name = "User_id")
//     private User user;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Role getRole() {
//         return role;
//     }

//     public void setRole(Role role) {
//         this.role = role;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }
// }
