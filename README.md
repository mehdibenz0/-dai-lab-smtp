# Introduction
Ce répertoire GitHub contient la réalisation du laboratoire SMTP. Le but de ce laboratoire est de mettre en pratique l'utilisation du protocole SMTP. Ce projet permet de créer des campagnes de spam. L'utilisateur peut définir une liste d'e-mails de victimes ainsi que les messages à envoyer. Ensuite, le programme s'occupera de créer des groupes et enverra aléatoirement un des messages à chaque groupe.

Ce labortoire à pour but de réaliser une application capable de communiquer via le protocle SMTP en faisant des campagne de spam où l'utilisateur peut choisir le nombre de victime par campagne et il défini une liste de vicitme et de contenu des mails. Le programme va pars la suite determiner aléatoirement parmi les victime et le contenu des mails.

Nous utilisons l’outil maildev pour pouvoir tester l’envoie de message sans pour autant les envoyer à de vrai personnes

# Maildev
Nous pouvons test l’application grâce à l’outil [Maildev]( https://github.com/maildev/maildev) qui permet de simuler un server SMTP en local et il est possible de consulter le mail qui lui ont été envoyer via un navigateur internet.

# Installation
## 1.Besoin technique
- JDK 17.0 ou plus
- Maven
- Docker
## 2.Configuration
Pour que le projet fonctionne correctement vous copier les 2 fichiers texte messages.txt et victims.txt présent sous src/config et les coller dans le dossier de votre choix sur le pc.
Puis aller dans le fichier src/main/java/Main.java et aux lignes 9 et 10 remplacer les chemins présent pour victimsFile et messagesFile par les chemins respectifs de vos fichier sur votre machine
## 3.Maildev
Le repo [Maildev]( https://github.com/maildev/maildev) détail comment lancer le serveur.

Vous pouvez lancer le serveur maildev avec Docker:
```
docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev
```
Ensuite ouvrez votre navigateur web préféré et aller sur localhost:1080
## 4.Execution
C'est est un projet Maven classique. Il est donc très simple de créer son exécutable et le lancer via les commandes suivantes:
```
mvn clean package
cd target
java -jar smtpPranker-1.0.jar
```

# Implementation
TODO: schéma UML

## SMTPClient
S'occupe de la connexion et de la communication avec le serveur SMTP et notre application pour que l'on puisse envoyé nos mails.

pour la partie SMTP, nous avons implementer un model de requet simple:
```
EHLO 
MAIL FROM: <sender email> 
RCPT_TO: <receiver0 email>,<receiver1 email>,... 
DATA \CRLF
  From: <sender email>
  To: <receiver0 email>,<receiver1 email>,...
  Subject: <Subject> 
  <content> \CRLF
  . \
```
## EmailGroup
Nous permet de générer le ou les groupes des destinataire ainsi que l'expéditeur et le contenu d'un mail

## ConfigurationManager
Va venir lire et traduire les informations présentes dans les fichiers texte contenant la listes des addresses et des messages 

## PrankGenerator
Assemble un expéditeur, un ou plusieurs destinataire et un message pour un faire un mail prank et l'envoie. La création du groupe est faites de manière aléatoire parmis la liste des vistimes

## Main
Qui va s'occuper d'instancier les Prank avec les inforamation reçu via ConfigurationManager et qui va ensuite utilisé SMTPClient pour gérer la connexion ainsi que PrankGenerator pour les envoyer.
