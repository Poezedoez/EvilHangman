### Structure
HangmanActivty is the main activity, started upon launch, that draws everything on the screen. 
The information required for this is obtained from the current Gameplay, instantiated as an EvilGameplay or GoodGameplay. 
These classes play their own game mode, but both implement the interface Gameplay that has 
the core gameplay methods that both modes share. 
All the words are obtained in the WordManager class. This class answers to a request of a random word or 
multiple words with a given word length.
The SettingsActivity contains the tools to edit the user preferences, the settings of the game.
The HighscoresActivity shows the 10 highest achieved highscores of all time. Highscores are shown here in descending order. 

An overview in the structure can be seen below:
![structure](EvilHangman/sketches/IMG_20151124_155307.jpg)

### API's and frameworks
	* 	In android there is no existing SeekBarPreference. A custom SeekbarPreference is used for this:
		[source](http://bit.ly/1lP8Orp)
	* 	To store the user preferences (even after app closing), SharedPreferences is used.

### Sketches
