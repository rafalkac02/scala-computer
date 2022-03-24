# Computer

### Project description
Load a file with instructions and execute them one by one on a board of n x m size. Use following instructions:

- CREATE [x] [y] [arrow] - places an arrow symbol (^, >, v, <) on the (x, y) field and returns an ID
- ROTATE [id] 0, 90, 180, 270, -90, -180, -270 - rotates an arrow to a given direction
- MOVE [id] [n] - moves an arrow forward n steps

Display a state of a board every instruction.