# KQ5 CD Text Patch #

A patch to add text dialogs to the CD release of King's Quest V. This release is also the one distributed by GOG and Steam.

## Overview ##

While the original 1990 Floppy Disk release of King's Quest V had text boxes, the 1991 CD release - which added full voice acting and narration - had them almost entirely removed. I suspect this was done to make this game feel less like a text adventure and more like an interactive movie. Later fully-voiced games by Sierra (including Space Quest IV, King's Quest VI, Quest for Glory IV and so on) included the text dialogs that would also appear in the non-voiced works, which makes KQ5-CD unique among Sierra's games in this respect.

This patch re-adds the text dialog functionality to the game scripts, using the text content from the Disk version as a reference. However, since the voice dialog from the CD is not exactly 1-to-1 with the Disk version, the text content in the patch has also been updated to match the voiceovers.

## Screenshots ##

<img width="515" height="300" alt="scummvm-kq5-cd-1-00006" src="https://github.com/user-attachments/assets/0d838634-3d48-4f65-8124-f95860928257" />
<img width="515" height="300" alt="scummvm-kq5-cd-1-00005" src="https://github.com/user-attachments/assets/6e52b889-da4a-489a-83f2-73388c86ef16" />

## Status ##

What works:
* Text dialogs for the narrator, including the decorative first letter
* Text dialogs for actor talk windows with the dialog box positioned relative to talker window
* Support for custom appearance per text box (positioning, width, timeout, etc) used in the Disk version
* Restore/Restart/Quit text dialogs shown at death have the text re-added to them

Still TODO:
* Intro
* Outro
* Genie cutscene

## Installation ##

Unpack the zip file into your game directory and run the game. It works with both DOS and ScummVM. However, existing saved games may not be backward compatible.

## Building ##

TBD
