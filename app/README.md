# Evil Hangman
This version of hangman is going to be a funny and also frustrating 
experience as opposed to the classic hangman games. However, if a player
intends to go for a highscore, this effort, will be rewarded. The customizable
settings make it so that you can adjust the game to your level.

### Features
	*   User settings are saved, and remembered, even after killing the app
	* 	Highscore is determined based on difficulty settings, encouraging to 
		challenge yourself with longer words, fewer tries and choice of game mode
	*   The user will get an in-game keyboard, simplifying the input
	*   Correctly chosen characters will be hidden from the keyboard
   	*   The current state of the to-be-guessed word is clearly displayed
 
### Structure
The project consists of main interface activity class that draws everything on the screen. The information required for this is obtained from the current gameplay class that is either ClassicHangman or EvilHangman. These classes play their own game mode. All the strings are handled in the WordChooser class that both modes interact with. The Settings class loads the user preferences on creation and is able to communicate these values to other classes.

### API's and frameworks
	* 	To store the user preferences (even after app closing), SharedPreferences is used.

![alt text](https://github.com/Poezedoez/EvilHangman/tree/master/app/sketches/game_and_settings.jpg "sketch game and settings")
![alt text](https://github.com/Poezedoez/EvilHangman/tree/master/app/sketches/highscores.jpg "sketch highscores")
  
  
