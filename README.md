# swaggyjava

Logiciel en JEE permettant la gestion d'une base de donnée simple constitués d'ordinateurs et de leurs fabriquants.




Fonctionnalités basiques :


- Liste des ordinateurs à partir de la BDD 

On peut consulter une liste contenant le nom de l'ordinateur, les dates de mise en service / hors service, le fabriquant.
- Structure du code en en MVC avec des DAO, Services et Controllers

Nous nous sommes efforcé de respecter les bonnes pratiques afin de rendre un code le plus propre possible
- Programme qui fonctionne autant avec JDBC et Hibernate

// TODO : Expliquer comment on switch entre les deux
- Tags JSTL

// TODO : Expliquer




Fonctionnalités avancées :


- Interface ergonomique et épurée qui reste esthétique
- Requêtes en AJAX qui permette de lier la BDD et le logiciel. //TODO : Expliquer plus ?
- Pagination (flexible) de la liste, qui commence à 20 et peut varier entre 20, 50 et 100
- Ajout d'un ordinateur dans la BDD

On peut ajouter un ordinateur via un bouton qui ouvre un fenêtre modale
- Supprimer un ordinateur de la BDD

Dans la liste permettant de visualiser la liste des ordinateurs, une colonne dédiée à la suppression est présente, la suppression étant représentée par une petite poubelle
