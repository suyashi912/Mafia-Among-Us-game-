# Mafia-Among-Us-game-

This is a command line implementation of Mafia - commonly known as <b>AMONG US</b>. 
It is a popular party game that requires one to think, manipulate, and deceive others in order to win.

<h2>
Rules of the game:</h2>

<h3>Plot:</h3> There is a village of N players.
<p>A player can be either a <ul><li>commoner</li> <li> detective</li><li> a healer</li><li> mafia</li>.
A commoner only knows that he is a commoner.
A detective knows all the other detectives. 
A mafia knows all the other Mafia players.
A healer knows all other healers in the game.</p>

<h3>Objectives:</h3>
<p>The objective for the mafias is to kill or eliminate all the non-mafias such that the
ratio of the alive mafias to all others is 1:1. 
A player can be eliminated in two ways:
<ul><li> By being killed by the Mafia </li>
  <li> Be eliminated in a vote out.</li> </ul>
Once a player is eliminated, they cannot be brought back to life. 
Mafias cannot kill themselves.
The objective for all other players(except the mafias) is to eliminate the mafias through a vote out(as the Mafias cannot be killed). Therefore, by using special powers of detectives and healers, they are required to save themselves and vote out the Mafias.</p>
<h3>
Role of different type of players:</h3><p><ol>
  <li><i> Mafia:</i> To kill all other players to achieve a 1:1 ratio.</li>
  <li><i> Detective:</i> They can randomly test one of the players (except detective) to test whether the player is mafia or not. If they correctly identify a mafia, the caught mafia will be voted out in that round by default. </li>
<li><i> Healer: </i>They randomly select a player from the game to give him a boost of 500 HP (All players, including mafias and healers themselves). </li>
  <li><i> Commoner: </i>They don’t have any special role. They only take part in the voting process. </li></ol>
<h3>End of Game:</h3>
<p><h4>The game ends when either all mafias are voted out or the ratio of mafias to others becomes 1:1.
The Mafia wins in the latter case and loses in the former.
You need to announce the winners at the end of the game along with the roles of each player.</h4></p>

