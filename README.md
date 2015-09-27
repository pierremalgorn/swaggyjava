# swaggyjava

Logiciel en JEE permettant la gestion d'une base de donnée simple constitués d'ordinateurs et de leurs fabriquants.

### Fonctionnalités basiques :

- <b>Liste des ordinateurs à partir de la BDD :</b> On peut consulter une liste contenant le nom de l'ordinateur, les dates de mise en service / hors service, ainsi que le fabriquant.
- <b>Structure du code : </b>Structure de type MVC qui comprends des DAO, Services et Controllers afin de respecter au mieux les bonnes pratiques
- <b>Evolutivité : </b>Le programme fonctionne autant avec JDBC que Hibernate, dans le but d'être plus flexible //TODO Expliquer comment on switch entre les deux
- <b>Tags JSTL : </b> // TODO Expliquer


### Fonctionnalités avancées :

- <b>Interface : </b> Ergonomique et épurée tout en restant esthétique
- <b>Requêtes AJAX : </b> Permet de lier la BDD et le logiciel //TODO : Expliquer plus ?
- <b>Pagination</b> : Pagination flexible de la liste, qui commence à 20 et peut varier entre 20, 50 et 100
- <b>Ajout d'un ordinateur dans la BDD : </b> On peut ajouter un ordinateur via un bouton qui ouvre <i>une fenêtre modale</i>
- <b>Suppression d'un ordinateur de la BDD : </b>Dans la liste permettant de visualiser la liste des ordinateurs, une colonne dédiée à la suppression est présente, la suppression étant représentée par une petite poubelle
