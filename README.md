
# PROJET MY_MARVIN

## Détails de Configuration

### Configuration Globale

- **Message système** : L'instance doit être configurée avec un message système disant "Bienvenue sur l'instance ChocolatinePowered Marvin Jenkins."

### Utilisateurs

- **Hugo**
  - ID : `chocolateen`
  - Mot de passe : défini par la variable d'environnement `USER_CHOCOLATEEN_PASSWORD`
  
- **Garance**
  - ID : `vaugie_g`
  - Mot de passe : défini par la variable d'environnement `USER_VAUGIE_G_PASSWORD`
  
- **Jeremy**
  - ID : `i_dont_know`
  - Mot de passe : défini par la variable d'environnement `USER_I_DONT_KNOW_PASSWORD`
  
- **Nassim**
  - ID : `nasso`
  - Mot de passe : défini par la variable d'environnement `USER_NASSO_PASSWORD`

### Stratégie d'Autorisation

- **Rôle Admin**
  - Description : "Maître Marvin"
  - Permissions : Toutes les permissions
  - Assigné à : Hugo

- **Rôle Ape**
  - Description : "Membre de l'équipe pédagogique"
  - Permissions : Construire des jobs, voir les espaces de travail
  - Assigné à : Jeremy

- **Rôle Gorilla**
  - Description : "Groupe de Recherche Obsessionnelle sur l'Innovation Liée à l'Apprentissage et l'Accomplissement"
  - Permissions : Même que Ape, plus créer, configurer, supprimer et déplacer des jobs ; annuler des builds
  - Assigné à : Garance

- **Rôle Assist**
  - Description : "Assistant"
  - Permissions : Voir les jobs et les espaces de travail
  - Assigné à : Nassim

### Dossier

- **Tools**
  - Emplacement : Racine
  - Description : "Dossier pour outils divers."

### Jobs

- **clone-repository**
  - Emplacement : Dossier Tools
  - Paramètres : `GIT_REPOSITORY_URL` (chaîne de caractères, sans valeur par défaut)
  - Actions : 
    - Clone le dépôt spécifié en utilisant une seule commande shell
    - Nettoyage de l'espace de travail avant la construction
    - Exécution manuelle uniquement

- **SEED**
  - Emplacement : Dossier Tools
  - Paramètres : 
    - `GITHUB_NAME` (chaîne de caractères, sans valeur par défaut)
    - `DISPLAY_NAME` (chaîne de caractères, sans valeur par défaut)
  - Actions : 
    - Crée un job avec les spécifications listées ci-dessous en utilisant un script d'exécution unique `job_dsl.groovy`
    - Exécution manuelle uniquement

- **Jobs créés par le job SEED**
  - Emplacement : Racine
  - Nom : Selon la valeur du paramètre `DISPLAY_NAME`
  - Propriété du projet GitHub : Pointe vers le dépôt spécifié par `GITHUB_NAME`
  - Pas de paramètres
  - Déclencheurs : SCM poll chaque minute ou déclenchement manuel
  - Système Git SCM : Obtient automatiquement le dépôt GitHub donné par `GITHUB_NAME`
  - Actions : 
    - Nettoyage de l'espace de travail avant la construction
    - Exécute `make fclean`, `make`, `make tests_run`, et `make clean` dans des étapes de script shell séparées

