
        *****************************
        *       King's Quest V      *
        *       CD Text Patch       *
        *                           *
        *       Release 1.0.0       *
        *****************************


Table of Contents:

    1 - Project Scope
    2 - Features
    3 - Installation Instructions
    4 - More Information
    5 - Credits
    6 - Disclaimer


1 - Overview:

    The purpose of this patch to add text dialogs to the CD release of
    King's Quest V. This release is also the one distributed by GOG
    and Steam.

    While the original 1990 Floppy Disk release of King's Quest V had
    text boxes, the 1991 CD release - which added full voice acting
    and narration - had them almost entirely removed. I suspect this
    was done to make this game feel less like a text adventure and
    more like an interactive movie. Later fully-voiced games by Sierra
    (including Space Quest IV, King's Quest VI, Quest for Glory IV and
    so on) included the text dialogs that would also appear in the non-
    voiced works, which makes KQ5-CD unique among Sierra's games in
    this respect.

    This patch re-adds the text dialog functionality to the game
    scripts, using the text content from the Disk version as a
    reference. However, since the voice dialog from the CD is not
    exactly 1-to-1 with the Disk version, the text content in the
    patch has also been updated to match the voiceovers.


2 - Features:

    The game is now fully subtitled, with support for the following:

        *) Text dialogs for the narrator, including the decorative
           first letter
        *) Text dialogs for actor portraits, with the dialog box
           positioned relative to the portrait
        *) Text dialog behavior (positioning, width, timeout, imagery,
           etc) used in the Disk version, with some adjustment where
           necessary
        *) Restore/Restart/Quit text dialogs shown at death have the
           text re-added to them
        *) Text dialogs for the intro and ending sequences
        *) Text dialogs for the Ants and Weeping Willow songs' lyrics


3 - Installation:

    1) Unpack the zip file into your game directory.
        a) The file 0.SCR will be overwritten, so you may want to back
           it up first.
    2) Launch the game in either DOS or ScummVM.

    Note that existing saved games will not be backward compatible.


4 - More Information:

    The most up-to-date project information will be on github.

        * https://github.com/mcmagi/kq5cd-text-patch

    If you have any interest in how I made this patch, feel free to
    watch my YouTube series where I walk through modding the game.

        * https://www.youtube.com/playlist?list=PLnztmu4-lcrJ7UPuXMpiMfl0AOAU0wTFp


5 - Credits:

    Thanks to sluicebox for providing decompiled sci-scripts.

        * https://github.com/sluicebox/sci-scripts/tree/main

    And thanks also to the authors of magnificent SCI Companion, including
    the Kawa-oneechan fork.

        * https://scicompanion.com/
        * https://github.com/Kawa-oneechan/SCICompanion

    And lastly, thanks to the authors of SCI Viewer.

        * https://sciprogramming.com/scitools.php?id=2

    This patch would not have been possible without these tools.

6 - Disclaimer:

    King's Quest is a registered trademark of Activision Publishing.
    This project is not affiliated with nor endorsed by Activision.
    You must own a legal copy of King's Quest V in order to apply
    this patch.

