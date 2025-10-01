# KQ5 CD Text Patch #

A patch to add text dialogs to the CD release of King's Quest V. This release is also the one distributed by GOG and Steam.

## Overview ##

While the original 1990 Floppy Disk release of King's Quest V had text boxes, the 1991 CD release - which added full voice acting and narration - had them almost entirely removed. I suspect this was done to make this game feel less like a text adventure and more like an interactive movie. Later fully-voiced games by Sierra (including Space Quest IV, King's Quest VI, Quest for Glory IV and so on) included the text dialogs that would also appear in the non-voiced works, which makes KQ5-CD unique among Sierra's games in this respect.

This patch re-adds the text dialog functionality to the game scripts, using the text content from the Disk version as a reference. However, since the voice dialog from the CD is not exactly 1-to-1 with the Disk version, the text content in the patch has also been updated to match the voiceovers.

## Features ##

The game is now fully subtitled, with support for the following:

* Text dialogs for the narrator, including the decorative first letter
* Text dialogs for actor portraits, with the dialog box positioned relative to the portrait
* Text dialog behavior (positioning, width, timeout, imagery, etc) used in the Disk version, with some adjustment where necessary
* Restore/Restart/Quit text dialogs shown at death have the text re-added to them
* Text dialogs for the intro and ending sequences
* Text dialogs for the Ants and Weeping Willow songs' lyrics

## Installation ##

* Unpack the zip file into your game directory.
  * The file `0.SCR` will be overwritten, so you may want to back it up first.
* Launch the game in either DOS or ScummVM.

Note that existing saved games will not be backward compatible.

## Screenshots ##

<table style="border: none">
  <tr>
    <td>
      <img width="515" height="300" alt="scummvm-kq5-cd-1-00006" src="https://github.com/user-attachments/assets/0d838634-3d48-4f65-8124-f95860928257" />
    </td>
    <td>
      <img width="515" height="300" alt="scummvm-kq5-cd-1-00005" src="https://github.com/user-attachments/assets/6e52b889-da4a-489a-83f2-73388c86ef16" />
    </td>
  </tr>
  <tr>
    <td>
      <img width="515" height="300" alt="scummvm-kq5-cd-1-00004" src="https://github.com/user-attachments/assets/d450d826-1b7f-45b3-a771-39631eb01c36" />
    </td>
    <td>
      <img width="515" height="300" alt="scummvm-kq5-cd-1-00000" src="https://github.com/user-attachments/assets/e799eaf6-d770-49c8-b471-7102fbc2234d" />
    </td>
  </tr>
  <tr>
    <td>
      <img width="515" height="300" alt="scummvm-kq5-cd-1-00002" src="https://github.com/user-attachments/assets/cb4bc06c-d085-4cc1-9181-e10313808dce" />
    </td>
    <td>
      <img width="515" height="300" alt="scummvm-kq5-cd-1-00001" src="https://github.com/user-attachments/assets/496b0ad3-9ef2-4bea-8553-be55ab114642" />
    </td>
  </tr>
</table>

## More Information ##

If you have any interest in how I made this patch, feel free to watch my [YouTube series](https://www.youtube.com/playlist?list=PLnztmu4-lcrJ7UPuXMpiMfl0AOAU0wTFp) where I walk through modding the game.

## Credits ##

Thanks to sluicebox for providing decompiled [sci-scripts](https://github.com/sluicebox/sci-scripts/tree/main).

And thanks also to the authors of magnificent [SCI Companion](https://scicompanion.com/), including the [Kawa-oneechan fork](https://github.com/Kawa-oneechan/SCICompanion).

And lastly, thanks to the authors of [SCI Viewer](https://sciprogramming.com/scitools.php?id=2).

This patch would not have been possible without these tools.

## Disclaimer ###

King's Quest is a registered trademark of Activision Publishing. This project is not affiliated with nor endorsed by Activision. You must own a legal copy of King's Quest V in order to apply this patch.

## Building ##

You'll need to use [SCI Companion](https://scicompanion.com/) to build and export the script files.

### Script Files ###

Copy the game files to the `kq5cd` directory.

In SCI Companion, load the game from the `kq5cd` directory and compile all game scripts.

Then export the following scripts to the `kq5cd/src` directory
* Main.sc -> 0.scr
* Interface.sc -> 255.scr
* TextMod.sc -> 300.scr
* deathScript1.sc -> 604.scr
* Talker.sc -> 928.scr

### Building the Package ###

Run `make all`. This will:
* Generate the `tex` (text resource) files from the `tsv` sources
* Copy in the exported `scr` files from above
* Package it together as a zip
