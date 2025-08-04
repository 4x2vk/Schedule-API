# 🗓️ Schedule API

> A lightweight RESTful service for managing schedules — create, view, update, and delete events securely with password-based verification.

---

## Built with

![JDK 17](https://img.shields.io/badge/JDK-17-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-ED1C24?logo=java&logoColor=white)

---
## Features

- Create new schedules with title, contents, editor and password
- Retrieve all existing schedules
- Update a schedule by ID (password required)
- Delete a schedule by ID (password required)
- Auto-generated timestamps for creation and last update

## Security & Authorization

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

## 📁Directory Structure
```
src/
├── controller/    # REST controllers
├── service/       # Business logic                
├── repository/    # JPA repositories
├── entity/        # JPA entities
├── dto/           # Request/Response DTOs
└── ScheduleApiApplication.java
```
