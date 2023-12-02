# Introduction
Ce répertoire GitHub contient la réalisation du laboratoire SMTP. Le but de ce laboratoire est de mettre en pratique l'utilisation du protocole SMTP. Ce projet permet de créer des campagnes de spam. L'utilisateur peut définir une liste d'e-mails de victimes ainsi que les messages à envoyer. Ensuite, le programme s'occupera de créer des groupes et enverra aléatoirement un des messages à chaque groupe.

Ce labortoire à pour but de réaliser une application capable de communiquer via le protocle SMTP en faisant des campagne de spam où l'utilisateur peut choisir le nombre de victime par campagne et il défini une liste de vicitme et de contenu des mails. Le programme va pars la suite determiner aléatoirement parmi les victime et le contenu des mails.

Nous utilisons l’outil maildev pour pouvoir tester l’envoie de message sans pour autant les envoyer à de vrai personnes

# Maildev
Nous pouvons test l’application grâce à l’outil [Maildev]( https://github.com/maildev/maildev) qui permet de simuler un server SMTP en local et il est possible de consulter le mail qui lui ont été envoyer via un navigateur internet.

# Installation
## Besoin technique
- JDK 17.0 ou plus
- Maven
- Docker
## Configuration
Pour que le projet fonctionne correctement vous copier les 2 fichiers texte messages.txt et victims.txt présent sous src/config et les coller dans le dossier de votre choix sur le pc.
Puis aller dans le fichier src/main/java/Main.java et aux lignes 9 et 10 remplacer les chemins présent pour victimsFile et messagesFile par les chemins respectifs de vos fichier sur votre machine
## Maildev
Le repo [Maildev]( https://github.com/maildev/maildev) détail comment lancer le serveur
## Execution

#Implementation

