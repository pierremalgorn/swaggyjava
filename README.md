# swaggyjava

Logiciel en JEE permettant la gestion d'une base de donnée simple constitués d'ordinateurs et de leurs fabriquants.

### Fonctionnalités basiques :

- <b>Liste des ordinateurs à partir de la BDD :</b> On peut consulter une liste contenant le nom de l'ordinateur, les dates de mise en service / hors service, ainsi que le fabricant.
- <b>Structure du code : </b>Structure de type MVC qui comprends des DAO, Services et Controllers afin de respecter au mieux les bonnes pratiques de développement
- <b>Evolutivité : </b>Le programme peut fonctionner avec JDBC et Hibernate pour le système ORM. Le changement de système s'effectue au sein des classes CompanyServiceImpl.java et ComputerServiceImpl.java lors de l'instanciation de CompanyDao et ComputerDao. (CompanyDaoJDBCImpl.java et ComputerDaoJDBCImpl.java pour JDBC et ComputerDaoImpl.java pour Hibernate)
- <b>Tags JSTL : </b>Utilisation des tags "c" JSTL afin d'afficher les variables passées par les Servlet


### Fonctionnalités avancées :

- <b>Interface : </b> Ergonomique et épurée tout en restant esthétique avec fenêtre modale. Il n'y a qu'une Servlet qui gère toutes les fonctionnalités. Lancement de la recherche via un clic sur le bouton ou appui sur Entrée
- <b>Requêtes AJAX : </b>Appels en AJAX pour afficher les résultats dynamiquement sans avoir à recharger la page
- <b>Pagination : </b> Pagination flexible de la liste, qui commence à 20 et peut varier entre 20, 50 et 100
- <b>Ajout d'un ordinateur dans la BDD : </b> On peut ajouter un ordinateur via un bouton qui ouvre <i>une fenêtre modale</i>
- <b>Suppression d'un ordinateur de la BDD : </b>Dans la liste permettant de visualiser la liste des ordinateurs, une colonne dédiée à la suppression est présente, la suppression étant représentée par une petite poubelle
- <b>Gestion des Exceptions : </b> Celles-ci sont gérées par la classe DAOException qui permet de lever les excpetions provenant des DAO


### Bugs connus présents :
- Lors de l'utilisation de Hibernate, seules 5 requêtes sont possibles, après le serveur Tomcat ne répond plus et il faut le redémarrer.
