### Structure
The project consists of main interface activity class that draws everything on the screen. 
The information required for this is obtained from the current gameplay class that is either ClassicHangman or EvilHangman. 
These classes play their own game mode, but both implement the interface Gameplay 
that has the core gameplay methods that both modes share. 
All the strings are handled in the WordChooser class that both modes interact with. 
The WordChooser object is contructed with the parameter of the user specified word length.
The WordChooser has a method to return a random word with this length, or make subsets based on user input (evil mode).
The Settings activity loads the user preferences on creation and is able to communicate these values to other classes.
The Highscores activity makes use of the Android HistoryViewActivity. Past highscores are show here in descending order. 

### API's and frameworks
	* 	To store the user preferences (even after app closing), SharedPreferences is used.

![alt text](https://github.com/Poezedoez/EvilHangman/tree/master/app/sketches/game_and_settings.jpg "sketch game and settings")
![alt text](https://github.com/Poezedoez/EvilHangman/tree/master/app/sketches/highscores.jpg "sketch highscores")
