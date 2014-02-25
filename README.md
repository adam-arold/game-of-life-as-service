# Game of Life as service

Game of life implementation with a service-like api.

## The supported operations are:
 - start
 - pause
 - stop
 - get latest generation
 - stamp pattern
 - add tick listener
 
## Usage:
 
`GameOfLifeServiceBuilder` can be used to build a  `GameOfLifeService`.
 
Use the `PatternRepository` to 
 - load the list of patterns
 - get the pattern names
 - get a pattern by its id (simple incrementing id)
 
The `PatternRepository` holds all the common `Pattern`s in game of life like the glider and so on. 
 
`Pattern` stamping on the game `Universe` works by supplying a `Pattern` (can be looked up from `PatternRepository`),
a starting `x` and `y` coordinate and a `PatternOrientation` which can be `TOP`, `RIGHT`, `BOTTOM`, and `LEFT`.

After a `Generation` is calculated in the game `Universe` a `Tick` event happens. You can subscribe to this Tick event
using the `GameOfLifeService#addTickListener` method. When the event listener fires you will be presented with a
`Tick` object which contains the current state of the `Universe` and the current `Generation` as well.
 
 
## License
Game of Life as service is made available under the [MIT License](http://www.opensource.org/licenses/mit-license.php).

## Credits

Game of Life as service is created and maintained by Adam Arold