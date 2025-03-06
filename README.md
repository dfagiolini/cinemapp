# Cinemapp - Web Application for Cinema and Booking Management

## Project Description

Cinemapp is a comprehensive web application designed for the efficient management of cinemas and the ticket booking process. Born as a personal project to put into practice and deepen various modern technologies, implementing this small application has been fun and instructive.

The application allows for the management of different cinemas, their film schedules, and offers users the ability to book tickets for available screenings. Particular attention has been paid to the use of high-performance and modern technologies.

## Main Functionalities

* **Basic CRUD Management:** Allows administrators to create, modify, and delete cinemas, halls, screenings, films, etc., with controls, for example, on the possible overlap between screenings.
* **View Schedules:** Allows viewing film schedules for each cinema and cinemas for each film, with screening times and dates.
* **User Ticket Booking:** Users can consult available schedules, select a cinema, a film, and a screening, and book the desired number of tickets, with availability checks.
* **Dynamic Queries:** Implementation of powerful dynamic queries for efficient filtering and searching of films and cinemas, allowing users to quickly find desired screenings based on various criteria.

https://github.com/user-attachments/assets/ab53ee4f-5f08-47f7-b0b9-bd7927b45101

* **Authentication and Authorization:** JWT-based authentication system with user role differentiation. There are two main roles:
    * **Regular User:** Can consult schedules and book tickets based on availability.
    * **Administrator:** Has full access to the management of cinemas, halls, films, etc.
* **Nested Data Optimized for Frontend:** Use of jOOQ queries to retrieve and structure data in nested formats, optimized to be easily consumed and displayed by the Vue.js frontend, improving the performance and responsiveness of the user interface. For example:
    ```
    [
    {
        "cinema": {
            "id": 4,
            "indirizzo": "Via Firenze, 8",
            "nome": "Cinema Firenze",
            "telefono": "345678901"
        },
        "proiezioni": [
            {
                "id": 8,
                "dataOraFine": "2025-02-08T22:00:00",
                "dataOraInizio": "2025-02-08T20:00:00",
                "prezzo": 10,
                "filmId": 1,
                "salaId": 7
            }
        ]
    },
    {
        "cinema": {
            "id": 1,
            "indirizzo": "Via Roma, 1",
            "nome": "Cinema Roma",
            "telefono": "123456789"
        },
        "proiezioni": [
            {
                "id": 2,
                "dataOraFine": "2025-02-08T22:00:00",
                "dataOraInizio": "2025-02-08T20:00:00",
                "prezzo": 10.5,
                "filmId": 1,
                "salaId": 1
            },
            {
                "id": 1,
                "dataOraFine": "2025-02-08T20:00:00",
                "dataOraInizio": "2025-02-08T18:00:00",
                "prezzo": 10.5,
                "filmId": 1,
                "salaId": 1
            }
        ]
    }
    ]
    ```

## Technologies Used

The Cinemapp project was developed using the following key technologies:

* **Backend:**

    * **[Micronaut](https://micronaut.io/)**: Modern and lightweight Java framework for building microservices and cloud-native applications. Chosen for its performance, startup speed, and ease of development.
    * **[jOOQ](https://www.jooq.org/)**: Java library for object-relational mapping (ORM) that allows writing SQL queries in a typesafe and fluent manner, facilitating interaction with the PostgreSQL database.
    * **[PostgreSQL](https://www.postgresql.org/)**: Powerful and reliable open-source relational database, chosen for its robustness, advanced features, and compatibility with jOOQ.

* **Frontend:**

    * **[Vue.js](https://vuejs.org/)**: Progressive JavaScript framework for building interactive and reactive user interfaces. Chosen for its simplicity, flexibility, and performance in creating single-page application (SPA) web applications.
