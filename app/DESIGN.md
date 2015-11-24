### Structure
ActivityHangman draws everything from the game on the screen. 
Everything is set in motion gamewise when the user presses the 'guess' button. Upon this press the guesses character is passed to the game and in ActivityHangman is also checked for game win. If this is the case, the HighscoreActivity is started passing as Bundle parameter the score from the last game.
The information required for showing the current game state in ActivityHangman is obtained from the current gameplay class that is either ClassicHangman or EvilHangman under the -Gameplay game- variable. 
These classes play their own game mode, but both extend the abstract class AbsractGameplay that itself implements the interface Gameplay. The AbstractGameplay class has the core gameplay methods that both modes share.
The words used in the game are loaded in the WordLoader class. 
The WordLoader has a method to return a random word with this length, or give a set of words (evil mode).
The Settings activity loads the user preferences on creation. The Settingsactivity gets the current settings passed as bundle from ActivityHangman. Given this settings object the activity can now modify these settings calling the setter methods on this object based on the user input.
#### Return types
	*	Gameplay game = loadGame based on mode (ActivityHangman) 
	* 	int getGuesses (Gameplay)
	* 	char[] getDisplayedCharacters (Gameplay)
	* 	List<Character> getGuesses (Gameplay)
	* 	List<String> getWordsWithLength(int length) (WordLoader) used by EvilGameplay
	* 	String getRandomWord(int length) (WordLoader) used by GoodGameplay
	* 	void makeSmallerSubset(this.subset) (EvilGameplay)

### API's and frameworks
	* 	To store the user preferences (even after app closing), SharedPreferences is used.

### Sketches
![sketch structure](http://i.imgur.com/ZLhxKMv.jpg?1 "sketch structure")
![sketch game and settings](https://github.com/Poezedoez/EvilHangman/blob/master/app/sketches/game_and_settings.jpg "sketch game and settings")
![sketch highscores](https://github.com/Poezedoez/EvilHangman/blob/master/app/sketches/highscores.jpg "sketch highscores")


