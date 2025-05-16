# Puzzle Solver API

API REST simplifiée permettant d'enregistrer, modifier et consulter des solutions générées par un algorithme de résolution mathématique (type casse-tête).
Les solutions générées écrasent toutes les solutions de la base de données, seules les solutions créées par l'utilisateur sont ajoutées à la liste des solutions.

---

## 📦 Stack technique

| Technologie      | Version   | Rôle                                                           |
|------------------|-----------|----------------------------------------------------------------|
| **Java**         | 17        | Langage                                                        |
| **Maven**        | ≥ 3.x     | Outil de build, gestion des dépendances                        |
| **Spring Boot**  | 2.7.5     | Framework principal                                            |
| **JPA ORM**      | 2.7.5     | ORM                                                            |
| **H2**           | 2.x       | Base de données embarquée (persistée en mode fichier)          |

---
## 🔗 EndPoints

| Méthode  | URL                    | Description                                               |
| -------- | ---------------------- | --------------------------------------------------------- |
| `GET`    | `/solutions`           | Récupère toutes les solutions                             |
| `GET`    | `/solutions/{id}`      | Récupère une solution spécifique                          |
| `POST`   | `/solutions/user`      | Crée une nouvelle solution valide                         |
| `POST`   | `/solutions/generated` | Crée une liste de solutions genérées                      |
| `PUT`    | `/solutions/{id}`      | Met à jour une solution par son ID                        |
| `DELETE` | `/solutions`           | Supprime toutes les solutions en base                     |
| `DELETE` | `/solutions/{id}`      | Supprime une solution par son ID                          |

---
## Exemples de payload

### ➕ POST /solutions

```bash
{
  "steps": "[{\"value\":1,\"operator\":\"+\"},{\"value\":13,\"operator\":\"*\"},...]",
  "result": 66,
  "isCorrect": true,
  "isUserSolution": false,
  "durationMs": 120,
  "createdAt": "2025-05-10T15:30:00Z"
}
```

## 📁 Lancement de l’application

### 1. Lancer le serveur

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

### 2. Accès à la console H2

- Accéder à l’URL suivante dans votre navigateur :
http://localhost:8080/h2-console

Paramètres de connexion :

    JDBC URL : jdbc:h2:file:./data/solutionsdb

    Username : sa

    Password : (laisser vide)