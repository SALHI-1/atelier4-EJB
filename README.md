# 🎓 Atelier EJB: Application de Gestion Scolaire (Jakarta EE / EJB)

Ce projet est une application web d'entreprise, modulaire et didactique, construite sur la plateforme **Jakarta EE** (anciennement Java EE). Elle illustre l'utilisation d'**Enterprise JavaBeans (EJB)** pour implémenter la couche métier, découplée de la couche de présentation (Servlets/JSP).

---

## ✨ Fonctionnalités de l'Application

L'application permet de gérer un système de scolarité à travers trois interfaces principales, gérées par des Servlets distinctes :

1.  **Gestion des Étudiants (`/etudiants`)**
    * Permet d'ajouter de nouveaux étudiants et d'afficher la liste des inscrits.
    * **Entité `Etudiant`** : `id_etudiant`, `nom`, `prenom`, `cne` (unique), `adresse`, `niveau`.
    * Interface utilisateur :
    

2.  **Gestion des Modules (`/modules`)**
    * Permet d'ajouter de nouveaux modules et de lister les modules disponibles.
    * **Entité `Module`** : `id_module`, `nom_module`, `semestre`.
    * Interface utilisateur :
    

3.  **Gestion des Notes (`/suivies`)**
    * Permet d'enregistrer une `Suivie` (note) pour un étudiant et un module spécifiques, et d'afficher l'historique des notes.
    * **Entité `Suivie`** : `id_suivie`, `note`, `date_notation`, et relations **Many-to-One** vers `Etudiant` et `Module`.
    * Interface utilisateur :
    

---

## 🛠️ Technologies Clés

| Composant | Technologie | Version / Détails |
| :--- | :--- | :--- |
| **Plateforme** | **Jakarta EE** | Version 10+ (API 11.0.0 dans la gestion des dépendances) |
| **Langage** | **Java** | JDK 21 |
| **Build Tool** | **Maven** | Projet multi-module (`pom.xml` principal) |
| **Couche Métier** | **EJB** | **Session Bean Stateless** (`@Stateless`) pour la logique métier (`GestionScolariteBean`) |
| **Accès aux Données** | **JPA** (Jakarta Persistence) | Utilisant `EntityManager` et configuré pour le dialecte MySQL |
| **Base de Données** | **MySQL** | Déclarée via la source de données JTA `java:jboss/datasources/mysql` |
| **Couche Présentation**| **Servlets & JSP** | Les Servlets injectent l'EJB via l'annotation `@EJB` |
| **Dépendances** | **JSTL Core** | Utilisé dans les JSP pour les boucles et l'affichage des données |
| **Utilitaires** | **Lombok** | Simplification des entités JPA (`@Getter`, `@Setter`, etc.) |

---

## 🏗️ Structure Modulaire

Ce projet est un projet Maven multi-module organisé comme suit :

1.  ### `projet-ejb` (Module JAR/EJB)
    * **Rôle** : Couche métier et persistance. Contient les Entités JPA et le Session Bean.
    * **Fichiers clés** :
        * `entities/` : `Etudiant.java`, `Module.java`, `Suivie.java`.
        * `ejb/IGestionScolariteRemote.java` : Interface distante (`@Remote`).
        * `ejb/GestionScolariteBean.java` : Implémentation (`@Stateless`).

2.  ### `projet-web` (Module WAR)
    * **Rôle** : Couche de présentation. Dépend du module `projet-ejb`.
    * **Fichiers clés** :
        * `projetweb/` : Les Servlets (`EtudiantServlet.java`, `ModuleServlet.java`, `SuivieServlet.java`).
        * `webapp/` : Les vues JSP (`etudiants.jsp`, `modules.jsp`, `suivies.jsp`).

---

## 🚀 Installation et Déploiement

### Prérequis

* **JDK 21** ou version ultérieure.
* **Maven 3.x** ou version ultérieure.
* Un **Serveur d'Applications Jakarta EE** (ex: WildFly, GlassFish, Open Liberty) prenant en charge EJB 3.2 et Servlet 6.0.
* Un serveur **MySQL** configuré.

### Configuration de la Persistance

1.  **DataSource JTA** : Sur votre serveur d'applications (par exemple, WildFly), configurez une source de données JTA (Transactional Data Source) avec le nom JNDI exact :
    ```
    java:jboss/datasources/mysql
    ```
2.  **Configuration JPA** : Le fichier `persistence.xml` est configuré pour l'unité de persistance `cnx` en mode `transaction-type="JTA"` et utilise le dialecte MySQL.
3.  **Mise à jour automatique de la BDD** : La propriété `hibernate.hbm2ddl.auto` est définie sur `update` pour créer ou mettre à jour les tables automatiquement au déploiement de l'EJB.

### Build du Projet

À partir du répertoire racine du projet, exécutez la commande Maven pour construire l'application :

```bash
mvn clean install
