# AppalachiaTrail

This is a game I built in Java that’s kind of like Oregon Trail, but set in the Fallout universe — specifically in Appalachia, like in Fallout 76. There was a lot to this It needed story, logic, inventory, decisions, and a whole lot of small pieces to work together. It was overwhelming at first, but once I got started, it actually became really fun to build.

The game starts with a vault dweller (you), leaving Vault 76 with four friends and trying to make it to Fort Atlas — a Brotherhood of Steel stronghold way up north. Along the way, you stop at a shop to buy supplies using caps, and then start a branching story where the choices you make can help you or kill you.


The main thing this game does is:
Let the player name themselves and their party(although I'm thinking of having it only be the player and not a party), Let the player buy supplies like food, water, and ammo and be able to change your mind on if you wanted that amount of food or water. Go through a branching story (using a binary tree) where you make choices of either left or right or sometimes I want to make it so that you can use stems and ammo and other things like that but I didn't have the time.
The inventory, party members, and story are all handled in separate classes, which made it a lot easier to keep things clean(although its still very messy).

What I had to fix or figure out:
This wasn’t easy to build there were a lot of weird bugs I had to fix and stuff. First, I had to make a working Inventory class that could refund and recalculate costs when you change your mind in the shop. That was probobly the hardest part, especially getting the caps to update right if you wanted to buy something twice. Another cI made a tester called Args.java so I could run the game using a text file of inputs, which helped a ton for debugging.

But Finally Here is How to run it:
For one make sure you have Java 8+ installed
If your using your terminal locate where the files are compile them and then run AppalachiaTrail

javac *.java
java AppalachiaTrail

I also made simple tester for this game that uses simulated imputs to run though the game
The way you use that is by running this command in your command prompt
java AppalachiaTrail < testinput.txt

This project was very fun and I really enjoyed coming up with idea for this project but it is not done and I want to finish it eventually
-Xander Peace
![image](https://github.com/user-attachments/assets/c6dfa471-9f00-4537-bce2-31bc635b68a0)

![image](https://github.com/user-attachments/assets/6fca385f-d785-41fa-9b66-55fd66a86d94)

![image](https://github.com/user-attachments/assets/318abafb-7019-4fc7-a636-eddd468ea653)

