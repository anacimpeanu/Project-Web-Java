# Diagrama Bazei de Date - Kyra Cosmetics

## Schema Completă

> **Notă:** Pentru a vizualiza diagrama grafică, deschide acest fișier pe GitHub sau folosește extensia Mermaid în VS Code.

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

## Descriere Entități

### 7 Entități:

1. **UTILIZATOR** - Conturi utilizatori (clienți și administratori)
2. **CATEGORIE** - Categorii de produse cosmetice
3. **PRODUS** - Produse cosmetice disponibile
4. **COS** - Coșul de cumpărături al utilizatorului
5. **ELEMENT_COS** - Produse individuale din coș
6. **COMANDA** - Comenzi plasate de clienți
7. **ELEMENT_COMANDA** - Produse individuale din comandă

## Relații între Entități

### 7 Relații:

1. **UTILIZATOR ↔ COS** (One-to-One)
   - Un utilizator are un coș de cumpărături
   
2. **UTILIZATOR ↔ COMANDA** (One-to-Many)
   - Un utilizator poate plasa mai multe comenzi
   
3. **CATEGORIE ↔ PRODUS** (One-to-Many)
   - O categorie conține mai multe produse
   
4. **COS ↔ ELEMENT_COS** (One-to-Many)
   - Un coș conține mai multe produse
   
5. **COMANDA ↔ ELEMENT_COMANDA** (One-to-Many)
   - O comandă conține mai multe produse
   
6. **PRODUS ↔ ELEMENT_COS** (One-to-Many)
   - Un produs poate fi în mai multe coșuri
   
7. **PRODUS ↔ ELEMENT_COMANDA** (One-to-Many)
   - Un produs poate apărea în mai multe comenzi

## Legenda Cheilor

- **PK** - Primary Key (Cheie Primară)
- **FK** - Foreign Key (Cheie Externă)
- **UK** - Unique Key (Cheie Unică)

---

## Reprezentare Text (pentru vizualizare rapidă)

```
┌─────────────────┐
│   UTILIZATOR    │
├─────────────────┤
│ id (PK)         │
│ nume            │
│ email (UK)      │
│ parola          │
│ rol             │
└────────┬────────┘
         │ 1:1
         ↓
   ┌─────────┐         ┌──────────────┐
   │   COS   │ 1:N     │ ELEMENT_COS  │
   ├─────────┤←────────┤──────────────┤
   │ id (PK) │         │ id (PK)      │
   │ user_id │         │ cos_id (FK)  │
   └─────────┘         │ produs_id (FK)│
                       │ cantitate    │
                       └──────┬───────┘
                              │
         ┌─────────────────┐  │
         │   UTILIZATOR    │  │
         └────────┬────────┘  │
                  │ 1:N       │
                  ↓           │
         ┌──────────────┐    │
         │   COMANDA    │    │
         ├──────────────┤    │
         │ id (PK)      │    │
         │ user_id (FK) │    │
         │ pret_total   │    │
         │ status       │    │
         │ data_creare  │    │
         └──────┬───────┘    │
                │ 1:N        │
                ↓            │
      ┌──────────────────┐  │
      │ ELEMENT_COMANDA  │  │
      ├──────────────────┤  │
      │ id (PK)          │  │
      │ comanda_id (FK)  │  │
      │ produs_id (FK)   │←─┘
      │ cantitate        │
      │ pret_la_cumparare│
      └────────┬─────────┘
               ↓
         ┌─────────────┐       ┌──────────────┐
         │   PRODUS    │ N:1   │  CATEGORIE   │
         ├─────────────┤───────→├──────────────┤
         │ id (PK)     │       │ id (PK)      │
         │ nume        │       │ nume         │
         │ descriere   │       └──────────────┘
         │ pret        │
         │ stoc        │
         │ categorie_id│
         └─────────────┘
```

**Relații:**
- UTILIZATOR → COS (1:1)
- UTILIZATOR → COMANDA (1:N)
- COS → ELEMENT_COS (1:N)
- COMANDA → ELEMENT_COMANDA (1:N)
- PRODUS → ELEMENT_COS (N:1)
- PRODUS → ELEMENT_COMANDA (N:1)
- CATEGORIE → PRODUS (1:N)

