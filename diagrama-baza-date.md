# Diagrama Bazei de Date - Kyra Cosmetics

```mermaid
erDiagram
    UTILIZATOR ||--o{ COMANDA : "plasează"
    UTILIZATOR ||--|| COS : "are"
    CATEGORIE ||--o{ PRODUS : "conține"
    COS ||--o{ ELEMENT_COS : "conține"
    COMANDA ||--o{ ELEMENT_COMANDA : "conține"
    PRODUS ||--o{ ELEMENT_COS : "adăugat în"
    PRODUS ||--o{ ELEMENT_COMANDA : "inclus în"

    UTILIZATOR {
        id PK
        nume
        email UK
        parola
        rol
    }

    CATEGORIE {
        id PK
        nume
    }

    PRODUS {
        id PK
        nume
        descriere
        pret
        stoc
        categorie_id FK
    }

    COS {
        id PK
        utilizator_id FK
    }

    ELEMENT_COS {
        id PK
        cos_id FK
        produs_id FK
        cantitate
    }

    COMANDA {
        id PK
        utilizator_id FK
        pret_total
        status
        data_creare
    }

    ELEMENT_COMANDA {
        id PK
        comanda_id FK
        produs_id FK
        cantitate
        pret_la_cumparare
    }
```

---

**Legenda:**
- **PK** = Primary Key (Cheie Primară)
- **FK** = Foreign Key (Cheie Externă)
- **UK** = Unique Key (Cheie Unică)


