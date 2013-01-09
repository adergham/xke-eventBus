# xke-eventBus #

### Avant de commencer ###

Mettez à jour les propriétés suivantes dans conf/Config.groovy:
 * chat.username : votre pseudo 
 * chat.my.ip : l'adresse ip de votre machine
 * chat.my.port : le port sur lequel votre application va fonctionner (par défaut 8080)
 
La documentation du plugin Platform-core se trouve [ici](http://grailsrocks.github.com/grails-platform-core/guide/events.html).


## Objectif : ##

Au cours de ce Hand's on, nous allons completer une application de chat pour la faire fonctionner.
Si vous êtes bloqué, vous pouvez effectuer un checkout de la branche "solution".


## 1. Valider un contacte nouvellement enregistré ##

A travers l’interface Scaffoldée de Grails, il est possible d’enregistrer un nouveau contact dans votre liste de diffusion. 
Cependant, l’adresse ip du contacte doit être validée.
Cette validation est assurée par la méthode ContactService.requestContactValidation() qu’il faut pouvoir appeler à l’enregistrement d’un nouveau contacte.

#### Instructions : ####
 * Implémenter ContactService.afterInsert()
 * Déclarer un Listener pour détecter l’event gorm émis après l'insertion d'un nouveau contacte

#### Validation : ####
 * ContactServiceTests.testAfterInsertInvalidContact()




## 2. Afficher sur l’écran de chat les contactes qui vous ont rajouté dans leur liste ##
ContactController.notifyContact() reçoit les requêtes de validation des autres utilisateurs et les enregistre dans votre base de données.

#### Instructions: ####
 * Implémenter la méthode ContactService.refreshContactList() pour y publier un évènement “addContactToList” à l’attention du browser.
 * Completer afterInsert pour appeler refreshContactList() si le contacte est valide
 * Le Listener javascript est déjà écrit dans chat.js

#### Validation : ####
 * ContactServiceIntegrationTests.testAfterInsertValidContactListener()



## 3. Recevoir un message et l’enregistrer en base de données ##

#### Instruction : ####
 * Finir d’implémenter la méthode ChatController.receiveMessage en appelant chatService.logMessage


#### Validation : ####
 * ChatControllerIntegrationTests.testReceiveMessageAndLogIt()




## 4. Recevoir un message et l’afficher à l’écran ##

#### Instruction : ####
 * Completer l’implémentation de receiveMessage pour publier un évenement “message.received”
 * Le listener javascript est déjà écrit dans chat.js

#### Validation : ####
* ChatControllerIntegrationTests.testReceiveMessageAndDisplayIt()



## 5. Ecrire un message et l’enregistrer en base ##

#### Instructions : ####
 * publier l’évènement “message.input” dans $("#chatInput").submit(...)
 * définir un listener sur la méthode chatService.logMessage

#### Validation : ####
 * ChatServiceIntegrationTests.testInputMessageFromUIToDatabase()




## 6. Ecrire un message, le diffuser à tous les contactes et l’afficher à l’écran ##

#### Instructions : ####
 * Implémenter la methode ChatService.inputMessage pour appeler la méthode broadcastMessage et publier un evenement “display.message”.
 * Déclarer un listener sur inputMessage pour traiter l’evenement publié par le browser (voir étape précédent)

#### Validation : ####
 * ChatServiceIntegrationTests.testInputMessageFromUIToBroadcastAndDisplay()




## 7. Faire en sorte que le l’affichage et la diffusion consomment le même évènement ##

#### Instructions: ####
 * Remplacer  l’appel de broadcastMessage dans inputMessage par une publication d’evenement
 * Déclarer un listener sur broadcastMessage

#### Validation : ####
 * ChatServiceIntegrationTests.testInputMessageFromUIToBroadcastAndDisplay()




## 8. Faire en sorte qu’écrire et recevoir un message déclenche un enregistrement en base ##

#### Instructions: ####
 * Remplacer l’appel de chatService.logMessage dans chatController.receiveMessage par la publication d’un evenement “message.received”
 * Déclarer un Listener sur logMessage en utilisant une wildcard dans le nom du topic.

