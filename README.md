# RPG Console

## 🎮 **Bienvenue dans RPG Console !**  
Plongez dans un jeu de rôle textuel captivant, entièrement conçu pour s'exécuter dans votre console. Choisissez votre héros, explorez un univers rempli de dangers et de trésors, et prouvez votre courage en affrontant des monstres redoutables. Bonne chance, aventurier !

---

### 🌟 **Fonctionnalités principales**

- **Création de personnage** :  
  Choisissez un pseudo unique et votre clan (Guerrier, Ogre, Assassin, etc.) pour commencer votre aventure. Votre héros démarre avec :  
  - **100 points de vie**
  - **50 pièces d’or**

- **Un gameplay intuitif** :  
  Le jeu est contrôlé exclusivement via les chiffres de votre clavier. Chaque décision compte !

- **Progression et survie** :  
  - Combattez des monstres et détruisez des obstacles pour gagner de l'XP et améliorer vos capacités.  
  - Collectez de l'or pour acheter des armes puissantes et des potions pour restaurer votre santé.  
  - Attention : Les ennemis attaquent aussi ! Gardez un œil sur vos points de vie.  

- **Objectif final** :  
  Trouvez la sortie et survivez à ce périple périlleux.  

---

### 🚀 **Comment jouer ?**

#### **1. Lancement du jeu**
- Exécutez le fichier **`MainGame.java`** dans un environnement prenant en charge Java.  

#### **2. Création de votre personnage**
- Suivez les instructions dans la console pour choisir votre pseudo et votre clan.

#### **3. Gameplay**
- Lisez attentivement les scénarios décrits à l’écran.  
- Entrez des chiffres pour :  
  - **Explorer les lieux**  
  - **Combattre les ennemis**  
  - **Acheter des équipements dans le magasin**  
  - **Utiliser des potions pour récupérer de la santé**

#### **4. Gagner de l'XP et de l'or**
- Les monstres vaincus vous rapportent de l'expérience et de l'or.  
- Dépensez votre or pour acheter des armes et des potions afin de devenir plus puissant.  

#### **5. Évitez la défaite !**
- Les ennemis attaquent et réduisent vos points de vie. Si vous tombez à 0 PV, l’aventure s’arrête.  

---

### 🛠️ **Configuration requise**

- **Java JDK** 8 ou supérieur installé sur votre machine.  
- Un terminal ou un IDE supportant l'exécution des programmes Java.

---

### 📝 **Structure du projet**



### Racine `/Game`
- **`MainGame.java`** : Point d'entrée du jeu.
- **`ActionsPlayer.java`** : Gère les actions que le joueur peut effectuer.
- **`Monster.java`** : Définition des monstres et de leur comportement.
- **`Clan.java`** : Gestion des différents clans jouables.
- **`Destructible.java`** : Gestion des objets destructibles.
- **`Direction.java`** : Gestion des directions pour les déplacements.
- **`GameMap.java`** : Gestion de la carte du jeu.
- **`Joueur.java`** : Définition des attributs et comportements du joueur.
- **`Monsgter.java`** : (Possible doublon, vérifier l'orthographe).
- **`Weapon.java`** : Classe mère pour les armes.
- **`WeaponStore.java`** : Gestion du magasin d'armes.

### Sous-dossier `/Armes`
- **`Axe.java`** : Définition de la hache.
- **`Bow.java`** : Définition de l'arc.
- **`Fist.java`** : Définition des coups de poing.
- **`Potion.java`** : Gestion des potions.
- **`Hammer.java`** : Définition du marteau.
- **`Knive.java`** : Définition du couteau.

### Sous-dossier `/Clans`
- **`Archer.java`** : Clan des archers.
- **`Assassin.java`** : Clan des assassins.
- **`Guerrier.java`** : Clan des guerriers.
- **`Healer.java`** : Clan des soigneurs.
- **`Ogre.java`** : Clan des ogres.
- **`Sorcier.java`** : Clan des sorciers.


---

### 💡 **Conseils pour réussir**

1. **Planifiez vos achats** : Investissez judicieusement dans des armes et des potions pour rester en vie.  
2. **Soyez stratégique** : Certains combats peuvent être évités pour économiser des ressources.  
3. **Gardez vos PV hauts** : Ne sous-estimez jamais un ennemi ! Une potion en réserve peut sauver votre partie.  

---

### 🎉 **Bonne chance !**

Que votre aventure soit riche en or, en gloire et en victoires épiques. Serez-vous le héros qui triomphera et atteindra la sortie ? À vous de jouer !  


### Sacha CLEMENT
