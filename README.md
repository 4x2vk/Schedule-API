# 🗓️ Schedule API

> A lightweight RESTful service for managing schedules — create, view, update, and delete events securely with password-based verification.

---

## Features

- Create new schedules with title, contents, editor and password
- Retrieve all existing schedules
- Update a schedule by ID (password required)
- Delete a schedule by ID (password required)
- Auto-generated timestamps for creation and last update

## 🔐 Security & Authorization

- No user login required
- Password-based protection for updating and deleting schedules
- Passwords are never exposed in API responses

---

## API Reference

Full documentation now lives:

🔗 [Check API Docs from this link](https://documenter.getpostman.com/view/47183182/2sB3BANDXa)

---

## Entity Relationship Diagram

Visual representation of the database schema:

🔗 [View ERD from this link (Sign in will be required)](https://lucid.app/lucidchart/5b34a8d6-1abe-4a5a-980f-daa03deddaa7/edit?viewport_loc=289%2C-1261%2C1233%2C579%2C0_0&invitationId=inv_d24734b7-fbe6-46bb-8650-1124f6c28951)

---

## Example Request
``` http
POST /schedule
Content-Type: application/json

{
  "title": "Zoom Session",
  "contents": "Discuss API and ERD",
  "name": "HongGilDong",
  "password": "secretpass"
}
```

## Tech Stack

- **JDK 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL / H2** (configurable)
- **Lombok**
- **RESTful API**

---


## 📁Directory Structure
```
src/
├── controller/    # REST controllers
├── service/       # Business logic                
├── repository/    # JPA repositories
├── entity/        # JPA entities
├── dto/           # Request/Response DTO
└── ScheduleApiApplication.java
```
