# Cinemapp - Applicazione Web per la Gestione di Cinema e Prenotazioni




## Descrizione del Progetto

Cinemapp è un'applicazione web completa progettata per la gestione efficiente di cinema e del processo di prenotazione dei biglietti.  Nata come progetto personale per mettere in pratica e approfondire diverse tecnologie moderne, Cinemapp offre una soluzione intuitiva e funzionale sia per gli utenti che desiderano prenotare biglietti, sia per gli amministratori che gestiscono le programmazioni dei cinema.

L'applicazione permette di gestire diversi cinema, le loro programmazioni di film, e offre agli utenti la possibilità di prenotare biglietti per le proiezioni disponibili.  Particolare attenzione è stata posta all'utilizzo di tecnologie performanti e moderne.

## Funzionalità Principali

  * **Gestione CRUD base:**  Permette agli amministratori di creare, modificare, eliminare cinema, sale, proiezioni, film etc.
  * **Visualizzare Programmazioni:**  Consente di visualizzare le programmazioni dei film per ciascun cinema e dei cinema per ciascun film, con orari e date delle proiezioni.
  * **Prenotazione Biglietti Utente:** Gli utenti possono consultare le programmazioni disponibili, selezionare un cinema, un film e una proiezione, e prenotare il numero desiderato di biglietti, con controlli sulle disponibilità.
  * **Query Dinamiche:** Implementazione di potenti query dinamiche per il filtraggio e la ricerca efficiente di film e cinema, permettendo agli utenti di trovare rapidamente le proiezioni desiderate in base a diversi criteri.
    

https://github.com/user-attachments/assets/ab53ee4f-5f08-47f7-b0b9-bd7927b45101


  * **Autenticazione e Autorizzazione:**  Sistema di autenticazione basato su JWT con differenziazione dei ruoli utente.  Sono presenti due ruoli principali:
      * **Utente Normale:** Può consultare le programmazioni e prenotare biglietti in base alle disponibilità.
      * **Amministratore:**  Ha accesso completo alla gestione di cinema, sale, film etc.
  * **Dati Annidati Ottimizzati per il Frontend:**  Utilizzo di query in jOOQ per recuperare e strutturare i dati in formati annidati, ottimizzati per essere facilmente consumati e visualizzati dal frontend Vue.js, migliorando le performance e la reattività dell'interfaccia utente. Ad esempio:
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

## Tecnologie Utilizzate

Il progetto Cinemapp è stato sviluppato utilizzando le seguenti tecnologie chiave:

  * **Backend:**

      * **[Micronaut](https://micronaut.io/)**: Framework Java moderno e leggero per la costruzione di microservizi e applicazioni cloud-native. Scelto per le sue performance, la velocità di startup e la facilità di sviluppo.
      * **[jOOQ](https://www.jooq.org/)**: Libreria Java per la mappatura object-relational (ORM) che permette di scrivere query SQL in modo typesafe e fluent, facilitando l'interazione con il database PostgreSQL.
      * **[PostgreSQL](https://www.postgresql.org/)**: Database relazionale open-source potente e affidabile, scelto per la sua robustezza, le funzionalità avanzate e la compatibilità con jOOQ.

  * **Frontend:**

      * **[Vue.js](https://vuejs.org/)**: Framework JavaScript progressivo per la costruzione di interfacce utente interattive e reattive. Scelto per la sua semplicità, flessibilità e performance nella creazione di applicazioni web single-page application (SPA).
