# Basics
The game stores levels in text files in `core/assets/areas/`.
Each sub folder of `areas` corresponds to an `areaID` of the game, each with their own color palate and spritesheets.
The names of the text files (`0.txt`, `12.txt`, etc.) correspond to their `levelID` (0 for `0.txt`, 12 for `12.txt`, etc.).

# Format
The contents of the files are divided into 4 sections.

## Tiles
Tiles are non-collidable square sprites of size 16.
The format for tiles are as follows:
`x pos, y pos, directon`
The direction determines the sprite that the tile uses.

## Collisions
Collisions are invisible barriers that stop the player from moving into them.
The format for tiles are as follows:
`x pos, y pos, width, height`

## Player
defines the position that the player should start at and in what direction they should face.
The format is:
`x pos, y pos, direction`

## World Objects
World objects are general things in the world, they can have a sprite or not, and they can be collidable or not.
Each world object has a different format for creating them, which will be gone over here.

### Level Transitions
Level transitions change the game's currently loaded level by the amount specified when the player collides with them (the amount is added to the current `levelID`).
The format for level transitions are the same as for collisions:
`x pos, y pos, width, height`

### Decor Objects
Decor (decoration) objects are sprites in the world. They can be collidable or not. Their sprite is determined by the coordinates given and the current decor spritesheet.
The format for creating them is:
`x pos, y pos, texture x pos, texture y pos, texture width, texture height, collidable`
`collidable` is a boolean (either "true" or "false").
The texture positions determine what region of the decor spritesheet to use as the sprite.

# In-code
To make things even more confusion the overworld is stored differently from this in the `Overworld` class.
Tiles are stored in the list `tiles`, collisions are stored in the list `collisions`, but level transitions have their own list, `transitions`, then the other world objects are stored in `worldObjects`