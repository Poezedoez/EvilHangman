### Structure
HangmanActivty is the main activity, started upon launch, that draws everything on the screen. 
The information required for this is obtained from the current gameplay class that is either ClassicHangman or EvilHangman. 
These classes play their own game mode, but both implement the interface Gameplay 
that has the core gameplay methods that both modes share. 
All the words are obtained in the WordManager class. This class answers to requests of a random word or 
multiple words with a given word length.
The SettingsActivity contains the tools to edit the user preferences, the settings of the game.
The HighscoresActivity shows the 10 highest achieved highscores of all time. Highscores are show here in descending order. 

### API's and frameworks
	* 	In android there is no existing SeekBarPreference. A custom SeekbarPreference is used for this:
		![source](http://bit.ly/1lP8Orp)
	* 	To store the user preferences (even after app closing), SharedPreferences is used.

![alt text](https://github.com/Poezedoez/EvilHangman/tree/master/app/sketches/game_and_settings.jpg "sketch game and settings")
![alt text](https://github.com/Poezedoez/EvilHangman/tree/master/app/sketches/highscores.jpg "sketch highscores")
