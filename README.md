# calculus
Ce projet a été réalisé dans le cadre d'un test technique demandé par DYNSEO. L'objectif était de créer une application de calcul mental en Java, en se basant sur leur jeu "Calculus" disponible dans l'une de leurs applications. Le but personnel n'était pas de créer une application parfaite mais quelque chose d'au moins fonctionnel : par exemple, le système de difficulté n'est pas régulier dans ses questions.

Fonctionnement du générateur de questions :
Le générateur de questions crée des expressions mathématiques aléatoires en fonction de la difficulté choisie. Il utilise des opérandes générés aléatoirement et les combine avec des opérateurs générés aléatoirement. Les opérandes et les opérations sont ajustés en fonction de la difficulté pour créer des questions appropriées.
Concernant les opérateurs, pour la difficulté facile, une question sur deux sera une addition ou une soustraction selon un nombre aléatoire qui donnera l'opérateur s'il est pair ou impair. On retrouve ce même système avec les autres difficultés en utilisant le modulo ce qui donne un certain pourcentage pour chaque opérateur. Par exemple, en mode normal, il y a 10% d'avoir une soustraction, 33% d'avoir une multiplication et 33% d'avoir une division.
Concernant les opérandes, la deuxième opérande sera inférieure à la seconde et sont choisies aléatoirement selon la difficulté. Le maximum est de 10 pour le mode facile, puis 100 en normal et 1000 en difficile.

Fonctionnement du générateur de propositions :
Le générateur de propositions crée des options de réponse pour chaque question. Il génère plusieurs réponses en utilisant une combinaison de la réponse correcte et des réponses incorrectes. Ces fausses propositions sont générées en ajoutant ou en soustrayant un nombre aléatoire à la réponse correcte. Ce nombre aléatoire est plus ou moins grand selon la difficulté.

Remarque :
Pour exécuter ce petit projet, vous aurez besoin d'Android Studio.