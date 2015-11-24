### Structure
ActivityHangman draws everything from the game on the screen. 
The information required for this is obtained from the current gameplay class that is either ClassicHangman or EvilHangman. 
These classes play their own game mode, but both extend the abstract class AbsractGameplay that itself implements the interface Gameplay. The AbstractGameplay class has the core gameplay methods that both modes share.
The words used in the game are loaded in the WordLoader class. 
The WordLoader has a method to return a random word with this length, or give a set of words (evil mode).
The Settings activity loads the user preferences on creation and is able to communicate these values to other classes by instantiation of it.
The Highscores activity makes use of the Android HistoryViewActivity. Past highscores are show here in descending order.
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
![sketch structure](https://www.dropbox.com/s/r7uwn9fub95wcu3/structure.jpg?raw=1 "sketch structure")
![sketch game and settings](https://github.com/Poezedoez/EvilHangman/blob/master/app/sketches/game_and_settings.jpg "sketch game and settings")
![sketch highscores](https://github.com/Poezedoez/EvilHangman/blob/master/app/sketches/highscores.jpg "sketch highscores")


