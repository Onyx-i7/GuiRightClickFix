# GUI Right Click Fix (Minecraft 1.12.2)

A lightweight mod for Forge that fixes a bug that occurs when using the keyboard (or any remapped key) to perform **right-click actions** within inventories and graphical user interfaces

## The Problem
In Minecraft 1.12.2, right-clicking inside inventories (to split items, place items in crafting grids, etc.) is hardcoded to the physical mouse right-click button. If your mouse right-click is broken, or if you prefer keyboard-only navigation, the game does not recognize keyboard remaps for this specific action

## The Solution
This mod listens to your vanilla **"Use Item / Place Block"** keybind (for example, if you remapped it to `Z`). When you are inside any inventory and press that key, the mod safely simulates a right-click on the slot your cursor is currently hovering over

## How to Use
1. Open Minecraft and go to **Options > Controls**
2. Find the **"Use Item / Place Block"** action and bind it to your preferred key
3. Open any inventory or GUI
4. Hover your cursor over the item or slot you want to right-click.
5. Press your assigned key. The game will register it as a right-click


## License
This project is open-source. Feel free to use, modify, and distribute it.

---
> **Note**: For now it only fixes one bug but I plan to create a mod that fixes bugs in 1.12.2 similar to UniversalTweaks
