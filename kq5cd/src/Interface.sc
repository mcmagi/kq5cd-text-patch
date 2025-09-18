;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 255)
(include sci.sh)
(use Main)
(use System)

(public
	Print 0
	ShowView 1
	GetInput 2
	GetNumber 3
	Printf 4
	MousedOn 5
)

(procedure (StillDown &tmp event ret)
	(= event (Event new:))
	(= ret (!= (event type:) evMOUSERELEASE))
	(event dispose:)
	(return ret)
)

(procedure (MousedOn obj event)
	(return
		(and
			(< (obj nsLeft:) (event x:) (obj nsRight:))
			(< (obj nsTop:) (event y:) (obj nsBottom:))
		)
	)
)

(procedure (GetInput str maxLen prompt &tmp [temp0 4])
	(if (Print (if (>= argc 3) prompt else {}) #edit str maxLen &rest)
		(StrLen str)
	)
)

(procedure (ShowView txt v l c) ; UNUSED
	(Print txt #icon v l c &rest)
)

(procedure (Print args0 &tmp newDialog newDText newDIcon newDEdit result idx xPos yPos width tmpDialog pItem port [dButtons 6] temp18 buttonCount temp20 [msg 1001] charIdx jDText printDText subtitleDText printLang subtitleLang hasWidth hasIcon jOffset unused isFirst isLetter newArgsList newNode [args 20] argsCount)
	; clone args array
	(= argsCount argc)
	(for ((= idx 0)) (< idx argc) ((++ idx))
		(= [args idx] [args0 idx])
	)
	(= hasWidth 0)
	(= hasIcon 0)
	(= xPos (= yPos -1))
	(= printLang (gGame printLang:))
	(= subtitleLang (gGame subtitleLang:))
	(= isFirst
		(= tmpDialog
			(= width
				(= temp18
					(= newDIcon (= newDEdit (= jDText (= jOffset (= buttonCount 0)))))
				)
			)
		)
	)
	((= newDialog (Dialog new:)) window: gSystemWindow name: {PrintD})
	(cond
		((u< [args 0] 1000)
			(GetFarText [args 0] [args 1] @msg)
			(= idx 2)
		)
		([args 0]
			(StrCpy @msg [args 0])
			(= idx 1)
		)
		(else
			(= msg 0)
			(= idx 0)
		)
	)
	(for ((= charIdx 0)) (StrAt @msg charIdx) ((++ charIdx))
		(if
			(and
				(== (StrAt @msg charIdx) 14848)
				(== (StrAt @msg (+ 1 charIdx)) 74)
			)
			(gGame printLang: 1 subtitleLang: 81)
			(StrSplit @msg @msg {#J}) ; StrSplit() but symbol not present
			(gGame printLang: printLang subtitleLang: subtitleLang)
			(StrAt @msg charIdx 0)
			(if (OneOf 81 printLang subtitleLang)
				((= jDText (DText new:))
					text: (+ @msg 2 charIdx)
					font: 900
					name: {jDText}
				)
			)
		)
	)
	((= newDText (DText new:)) text: @msg font: gUserFont)
	(= printDText (if (and jDText (== printLang 81)) jDText else newDText))
	(= subtitleDText
		(cond
			((== subtitleLang 81) jDText)
			(jDText
				(if subtitleLang
					newDText
				else
					(newDText dispose:)
					(= newDText 0)
				)
			)
		)
	)
	(printDText moveTo: 4 4 setSize:)
	(newDialog add: printDText setSize:)
	(if subtitleDText
		(subtitleDText
			setSize:
			moveTo: (printDText nsLeft:) (+ 4 (printDText nsBottom:))
		)
		(newDialog add: subtitleDText setSize:)
	)
	(for ((= idx idx)) (< idx argsCount) ((++ idx))
		(switch [args idx]
			(#mode
				(++ idx)
				(if (and newDText (not subtitleDText))
					(newDText mode: [args idx])
				)
			)
			(#font
				(++ idx)
				(if newDText
					(newDText font: [args idx] setSize: width)
				)
			)
			(#width
				(= hasWidth 1)
				(= width [args (++ idx)])
				(printDText setSize: width)
				(if subtitleDText
					(subtitleDText
						setSize: width
						moveTo: (printDText nsLeft:) (+ 4 (printDText nsBottom:))
					)
				)
			)
			(#time
				(++ idx)
				(newDialog time: [args idx])
			)
			(#title
				(++ idx)
				(newDialog text: [args idx])
			)
			(#at
				(= xPos [args (++ idx)])
				(= yPos [args (++ idx)])
			)
			(#draw
				(Animate (gCast elements:) 0)
			)
			(#edit
				(++ idx)
				((= newDEdit (DEdit new:)) text: [args idx])
				(++ idx)
				(newDEdit max: [args idx] setSize:)
			)
			(#button
				((= [dButtons buttonCount] (DButton new:))
					text: [args (++ idx)]
					value: [args (++ idx)]
					setSize:
				)
				(+= temp18 (+ ([dButtons buttonCount] nsRight:) 4))
				(++ buttonCount)
			)
			(#icon
				(= hasIcon 1)
				(if (IsObject [args (+ idx 1)])
					(= newDIcon ([args (+ idx 1)] new:))
					(newDIcon setSize:)
					(+= idx 1)
				else
					(= newDIcon (DIcon new:))
					(newDIcon
						view: [args (+ idx 1)]
						loop: [args (+ idx 2)]
						cel: [args (+ idx 3)]
						setSize:
					)
					(+= idx 3)
				)
			)
			(#dispose
				(if (and (< (+ idx 1) argsCount) (IsObject [args (+ idx 1)]))
					(newDialog caller: [args (+ idx 1)])
					(++ idx)
				)
				(if gModelessDialog
					(gModelessDialog dispose:)
				)
				(= tmpDialog newDialog)
			)
			(#window
				(++ idx)
				(newDialog window: [args idx])
			)
			(#first
				(= isFirst 1)
			)
            (#letter
                (= isLetter 1)
            )
            (#selector
            	(++ idx)
            	; parse selector string into a list
				(= newArgsList (ParseSelector [args idx]))
				; append selector args to args array, increasing number of args
				(for ((= newNode (FirstNode newArgsList))) (!= newNode null) ((= newNode (NextNode newNode))(++ argsCount))
					(= [args argsCount] (NodeValue newNode))
				)
				(DisposeList newArgsList)
			)
		)
	)
	(if isFirst
		(= tmpDialog 0)
	)
	(if
		(and
			(not (or hasWidth hasIcon))
			(> (- (newDialog nsBottom:) (newDialog nsTop:)) 100)
		)
		(printDText setSize: 300)
		(if subtitleDText
			(subtitleDText
				setSize: 300
				moveTo: (printDText nsLeft:) (+ 4 (printDText nsBottom:))
			)
		)
	)
	(if newDIcon
		(newDIcon moveTo: 4 4)
		(if (or (== printDText jDText) (== subtitleDText jDText))
			(= jOffset 8)
		)
		(if (& (newDIcon state:) $0010)
			(printDText
				moveTo: (+ 4 jOffset) (+ (newDIcon nsBottom:) (printDText nsTop:))
				setSize:
			)
		else
			(printDText
				moveTo: (+ 4 (newDIcon nsRight:) jOffset) (printDText nsTop:)
				setSize:
			)
		)
		(if subtitleDText
			(subtitleDText moveTo: (printDText nsLeft:) (+ 4 (printDText nsBottom:)))
		)
		(if isLetter
			(newDText moveTo: (+ 4 (newDIcon nsLeft:)) (- (newDIcon nsBottom:) 7))
		)
		(newDialog add: newDIcon)
	)
	(newDialog setSize:)
	(if newDEdit
		(newDEdit
			moveTo:
				((if subtitleDText else printDText) nsLeft?)
				(+ 4 ((if subtitleDText else printDText) nsBottom?))
		)
		(newDialog add: newDEdit setSize:)
	)
	(= temp20
		(if (> temp18 (newDialog nsRight:))
			4
		else
			(- (newDialog nsRight:) temp18)
		)
	)
	(for ((= idx 0)) (< idx buttonCount) ((++ idx))
		([dButtons idx] moveTo: temp20 (newDialog nsBottom:))
		(newDialog add: [dButtons idx])
		(= temp20 (+ 4 ([dButtons idx] nsRight:)))
	)
	(newDialog setSize: center:)
	(if
		(or
			(and newDIcon (& (newDIcon state:) $0010))
			(and newDIcon (not (StrLen @msg)))
		)
		(newDIcon
			moveTo:
				(/
					(-
						(- (newDialog nsRight:) (newDialog nsLeft:))
						(- (newDIcon nsRight:) (newDIcon nsLeft:))
					)
					2
				)
				4
		)
	)
	(newDialog
		moveTo:
			(if (== -1 xPos)
				(newDialog nsLeft:)
			else
				xPos
			)
			(if (== -1 yPos)
				(newDialog nsTop:)
			else
				yPos
			)
	)
	(= port (GetPort))
	(newDialog open: (if (newDialog text:) 4 else 0) (if tmpDialog 15 else -1))
	(if tmpDialog
		(= gModelessPort (GetPort))
		; return modeless dialog reference if used
		(SetPort port)
		(return (= gModelessDialog tmpDialog))
	else
		(gSounds pause: 1)
	)
	(if
		(and
			(= pItem (newDialog firstTrue: #checkState 1))
			(not (newDialog firstTrue: #checkState 2))
		)
		(pItem state: (| (pItem state:) $0002))
	)
	(if (== (= result (newDialog doit: pItem)) -1)
		(= result 0)
	)
	; set return result as selected button value (if any)
	(for ((= idx 0)) (< idx buttonCount) ((++ idx))
		(if (== result [dButtons idx])
			(= result (result value:))
			(break)
		)
	)
	(if (not (newDialog theItem:))
		(= result 1)
	)
	(newDialog dispose:)
	(gSounds pause: 0)
	(return result)
)

(procedure (GetNumber string default &tmp [theLine 40])
	(= theLine 0)
	(if (> argc 1)
		(Format @theLine 255 0 default) ; "%d"
	)
	(return
		(if (GetInput @theLine 5 string)
			(ReadNumber @theLine)
		else
			-1
		)
	)
)

(procedure (Printf &tmp [str 500])
	(Format @str &rest)
	(Print @str)
)

(procedure (ParseSelector selectorStr &tmp startIdx endIdx argLen [argStr 20] newArgsList newArgsCtr newValue char1)
	(= newArgsList (NewList))
	(= newArgsCtr 0)
	(= startIdx 0)

	; loop through each argument in selector string until we hit terminator
	(repeat
		; locate arg by finding next space (or terminator) in string
		(= endIdx (StrChr selectorStr 32 startIdx))
		(= argLen (- endIdx startIdx))

		; extract argument from selector string
		(StrCpy @argStr (+ selectorStr startIdx) argLen)
		(= char1 (StrAt @argStr 0))
		(= newValue -1)
		(cond
			; if arg starts with a number, convert string to integer
			((and (>= char1 48) (<= [char1 0] 57))
				(= newValue (ReadNumber @argStr))
			)
          	; otherwise, translate string to a selector used in Print function
           	((== (StrCmp @argStr {#time}) 0)
           		(= newValue #time)
			)
           	((== (StrCmp @argStr {#mode}) 0)
           		(= newValue #mode)
			)
           	((== (StrCmp @argStr {#font}) 0)
           		(= newValue #font)
			)
           	((== (StrCmp @argStr {#window}) 0)
           		(= newValue #window)
			)
           	((== (StrCmp @argStr {#edit}) 0)
           		(= newValue #edit)
			)
           	((== (StrCmp @argStr {#at}) 0)
           		(= newValue #at)
			)
           	((== (StrCmp @argStr {#width}) 0)
           		(= newValue #width)
			)
           	((== (StrCmp @argStr {#title}) 0)
           		(= newValue #title)
			)
           	((== (StrCmp @argStr {#button}) 0)
           		(= newValue #button)
			)
           	((== (StrCmp @argStr {#icon}) 0)
           		(= newValue #icon)
			)
           	((== (StrCmp @argStr {#draw}) 0)
           		(= newValue #draw)
			)
           	((== (StrCmp @argStr {#dispose}) 0)
           		(= newValue #dispose)
			)
           	((== (StrCmp @argStr {#first}) 0)
           		(= newValue #first)
			)
           	((== (StrCmp @argStr {#letter}) 0)
           		(= newValue #letter)
			)
           	((== (StrCmp @argStr {#dontErase}) 0)
           		; TODO: #dontErase
			)
		)
		; if value found, add to args list
		(if (>= newValue 0)
			;(Printf "ParseSelector: %s -> arg(%d)=%d" @argStr newArgsCtr newValue)
			(AddToEnd newArgsList (NewNode newValue newArgsCtr))
			(++ newArgsCtr)
		)

		; break out if we hit the end of the string
		(breakif (== (StrAt selectorStr endIdx) 0))

		; advance startIdx to end of current arg (+1 for space)
		(= startIdx (+ endIdx 1))
	)
	(return newArgsList)
)

(procedure (StrChr str chr fromIdx &tmp idx len)
	; get starting position
	(if (< argc 3)
		(= fromIdx 0)
	)
	(= len (StrLen str))

	; find first occurrance of chr
	(for ((= idx fromIdx)) (< idx len) ((++ idx))
		(if (== (StrAt str idx) chr)
			(return idx)
		)
	)
	(return idx)
)

(class MenuBar of Obj
	(properties
		state 0
	)

	(method (draw)
		(= state 1)
		(DrawMenuBar 1)
	)

	(method (hide)
		(DrawMenuBar 0)
	)

	(method (add)
		(AddMenu &rest)
	)

	(method (handleEvent event &tmp retVal oldRepeat)
		(= retVal 0)
		(if state
			(= oldRepeat (Joystick 12 30))
			(= retVal (MenuSelect event &rest))
			(Joystick 12 oldRepeat)
		)
		(return retVal)
	)
)

(class Item of Obj
	(properties
		type 0
		state 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
	)

	(method (enable bool)
		(if bool
			(|= state $0001)
		else
			(&= state $fffe)
		)
	)

	(method (select bool)
		(if bool
			(|= state $0008)
		else
			(&= state $fff7)
		)
		(self draw:)
	)

	(method (handleEvent event &tmp ret evtType evt)
		(if (event claimed:)
			(return 0)
		)
		(= ret 0)
		(if
			(and
				(& state $0001)
				(or
					(and (== (= evtType (event type:)) evSAID) (Said said))
					(and (== evtType evKEYBOARD) (== (event message:) key))
					(and (== evtType evMOUSEBUTTON) (self check: event))
				)
			)
			(event claimed: 1)
			(= ret (self track: event))
		)
		(return ret)
	)

	(method (check event)
		(return
			(and
				(>= (event x:) nsLeft)
				(>= (event y:) nsTop)
				(< (event x:) nsRight)
				(< (event y:) nsBottom)
			)
		)
	)

	(method (track event &tmp in lastIn)
		(if (== 1 (event type:))
			(= lastIn 0)
			(repeat
				(= event (Event new: evPEEK))
				(event localize:)
				(if (!= (= in (self check: event)) lastIn)
					(HiliteControl self)
					(= lastIn in)
				)
				(event dispose:)
				(breakif (not (StillDown)))
			)
			(if in
				(HiliteControl self)
			)
			(return in)
		else
			(return self)
		)
	)

	(method (isType param1)
		(return (== type param1))
	)

	(method (checkState param1)
		(return (& state param1))
	)

	(method (doit)
		(return value)
	)

	(method (setSize))

	(method (move param1 param2)
		(+= nsRight param1)
		(+= nsLeft param1)
		(+= nsTop param2)
		(+= nsBottom param2)
	)

	(method (moveTo param1 param2)
		(self move: (- param1 nsLeft) (- param2 nsTop))
	)

	(method (draw)
		(DrawControl self)
	)

	(method (cycle))
)

(class DText of Item
	(properties
		type 2
		text 0
		font 1
		mode 0
	)

	(method (new &tmp newText)
		((super new:) font: gUserFont yourself:)
	)

	(method (setSize w &tmp [r 4])
		(TextSize
			@[r 0]
			text
			font
			(if argc w else 0)
			{\0d\n----------\0d\n}
		)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft [r 3]))
	)
)

(class DIcon of Item
	(properties
		type 4
		view 0
		loop 0
		cel 0
	)

	(method (setSize &tmp [r 4])
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
)

(class DButton of Item
	(properties
		type 1
		state 3
		text 0
		font 0
	)

	(method (setSize &tmp [r 4])
		(TextSize @[r 0] text font 0 0)
		(+= [r 2] 2)
		(+= [r 3] 2)
		(= nsBottom (+ nsTop [r 2]))
		(= [r 3] (* (/ (+ [r 3] 15) 16) 16))
		(= nsRight (+ [r 3] nsLeft))
	)
)

(class DEdit of Item
	(properties
		type 3
		state 1
		text 0
		font 0
		max 0
		cursor 0
	)

	(method (track evt)
		(EditControl self evt)
		(return self)
	)

	(method (setSize &tmp [r 4])
		(= font gInputFont)
		(TextSize @[r 0] {M} font 0 0)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft (/ (* [r 3] max 3) 4)))
		(= cursor (StrLen text))
	)
)

(class DSelector of Item
	(properties
		type 6
		font 0
		x 20
		y 6
		text 0
		cursor 0
		topString 0
		mark 0
	)

	(method (indexOf what &tmp ptr i)
		(= ptr text)
		(for ((= i 0)) (< i 300) ((++ i))
			(if (== 0 (StrLen ptr))
				(return -1)
			)
			(if (not (StrCmp what ptr))
				(return i)
			)
			(+= ptr x)
		)
	)

	(method (at what)
		(return (+ text (* x what)))
	)

	(method (setSize &tmp [r 4])
		(TextSize @[r 0] {M} font 0 0)
		(= nsBottom (+ nsTop 20 (* [r 2] y)))
		(= nsRight (+ nsLeft (/ (* [r 3] x 3) 4)))
		(= topString (= cursor text))
		(= mark 0)
	)

	(method (retreat lines &tmp redraw)
		(= redraw 0)
		(while lines
			(if (!= cursor text)
				(= redraw 1)
				(-= cursor x)
				(if mark
					(-- mark)
				else
					(-= topString x)
				)
			else
				(break)
			)
			(-- lines)
		)
		(return
			(if redraw
				(self draw:)
				1
			)
		)
	)

	(method (advance lines &tmp redraw)
		(if (not (StrAt cursor 0))
			(return)
		)
		(= redraw 0)
		(while lines
			(if (StrAt cursor x)
				(= redraw 1)
				(+= cursor x)
				(if (< (+ mark 1) y)
					(++ mark)
				else
					(+= topString x)
				)
			else
				(break)
			)
			(-- lines)
		)
		(return
			(if redraw
				(self draw:)
				1
			)
		)
	)

	(method (handleEvent event &tmp temp0 [temp1 3] temp4 [temp5 4])
		(if (event claimed:)
			(return 0)
		)
		(= temp0 0)
		(switch (event type:)
			(evKEYBOARD
				(event
					claimed:
						(switch (event message:)
							(KEY_HOME
								(self retreat: 50)
							)
							(KEY_END
								(self advance: 50)
							)
							($5100 ; PAGEDOWN
								(self advance: (- y 1))
							)
							($4900 ; PAGEUP
								(self retreat: (- y 1))
							)
							(KEY_DOWN
								(self advance: 1)
							)
							(KEY_UP
								(self retreat: 1)
							)
							(else 0)
						)
				)
			)
			(evMOUSEBUTTON
				(if (self check: event)
					(event claimed: 1)
					(cond
						((< (event y:) (+ nsTop 10))
							(repeat
								(self retreat: 1)
								(breakif (not (StillDown)))
							)
						)
						((> (event y:) (- nsBottom 10))
							(repeat
								(self advance: 1)
								(breakif (not (StillDown)))
							)
						)
						(else
							(TextSize @[temp5 0] {M} font 0 0)
							(if
								(>
									(= temp4
										(/
											(- (event y:) (+ nsTop 10))
											[temp5 2]
										)
									)
									mark
								)
								(self advance: (- temp4 mark))
							else
								(self retreat: (- mark temp4))
							)
						)
					)
				)
			)
		)
		(if (and (event claimed:) (& state $0002)) self else 0)
	)
)

(class Dialog of List
	(properties
		text 0
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		busy 0
		caller 0
		seconds 0
		lastSeconds 0
	)

	(method (open wtype pri)
		(if (and (PicNotValid) gCast)
			(Animate (gCast elements:) 0)
		)
		(= window (window new:))
		(window
			top: nsTop
			left: nsLeft
			bottom: nsBottom
			right: nsRight
			title: text
			type: wtype
			priority: pri
			open:
		)
		(= seconds time)
		(self draw:)
	)

	(method (draw)
		(self eachElementDo: #draw)
	)

	(method (doit def &tmp done event ret eatMice lastTick)
		(= done 0)
		(= busy 1)
		(self eachElementDo: #init)
		(if theItem
			(theItem select: 0)
		)
		(= theItem
			(if (and argc def)
				def
			else
				(self firstTrue: #checkState 1)
			)
		)
		(if theItem
			(theItem select: 1)
		)
		(if (not theItem)
			(= eatMice gEatMice)
			(= lastTick (GetTime))
		else
			(= eatMice 0)
		)
		(= ret 0)
		(while (not ret)
			(self eachElementDo: #cycle)
			(= event ((Event new:) localize:))
			(if eatMice
				(-- eatMice)
				(if (== (event type:) 1)
					(event type: 0)
				)
				(while (== lastTick (GetTime))
				)
				(= lastTick (GetTime))
			)
			(= ret (self handleEvent: event))
			(event dispose:)
			(self check:)
			(if (or (== ret -1) (not busy))
				(= ret 0)
				(EditControl theItem 0)
				(break)
			)
			(Wait 1)
		)
		(= busy 0)
		(return ret)
	)

	(method (check &tmp thisSeconds)
		(if seconds
			(= thisSeconds (GetTime 1)) ; SysTime12
			(if (!= lastSeconds thisSeconds)
				(= lastSeconds thisSeconds)
				(if (not (-- seconds))
					(self cue:)
				)
			)
		)
	)

	(method (cue)
		(if (not busy)
			(self dispose:)
		else
			(= busy 0)
		)
	)

	(method (dispose &tmp theCaller)
		(if (== self gModelessDialog)
			(SetPort gModelessPort)
			(= gModelessDialog 0)
			(= gModelessPort 0)
		)
		(if window
			(window dispose:)
		)
		(= theItem (= window 0))
		(= theCaller caller)
		(super dispose:)
		(if theCaller
			(theCaller cue:)
		)
	)

	(method (advance &tmp obj node)
		(if theItem
			(theItem select: 0)
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self next: node)))
					(= node (self first:))
				)
				(= theItem (NodeValue node))
				(if (& (theItem state:) $0001)
					(break)
				)
			)
			(theItem select: 1)
			(gGame
				setCursor:
					gTheCursor
					1
					(+
						(theItem nsLeft:)
						(/ (- (theItem nsRight:) (theItem nsLeft:)) 2)
					)
					(- (theItem nsBottom:) 3)
			)
		)
	)

	(method (retreat &tmp obj node)
		(if theItem
			(theItem select: 0)
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self prev: node)))
					(= node (self last:))
				)
				(= theItem (NodeValue node))
				(if (& (theItem state:) $0001)
					(break)
				)
			)
			(theItem select: 1)
			(gGame
				setCursor:
					gTheCursor
					1
					(+
						(theItem nsLeft:)
						(/ (- (theItem nsRight:) (theItem nsLeft:)) 2)
					)
					(- (theItem nsBottom:) 3)
			)
		)
	)

	(method (handleEvent event &tmp ret)
		(if (& (event type:) $0040) ; direction
			(event type: evKEYBOARD)
			(switch (event message:)
				(5
					(event message: KEY_DOWN)
				)
				(1
					(event message: KEY_UP)
				)
				(7
					(event message: KEY_LEFT)
				)
				(3
					(event message: KEY_RIGHT)
				)
				(else
					(event type: $0040) ; direction
				)
			)
		)
		(if
			(or
				(event claimed:)
				(== (event type:) evNULL)
				(and
					(!= evMOUSEBUTTON (event type:))
					(!= evKEYBOARD (event type:))
					(!= $0040 (event type:)) ; direction
					(!= evJOYDOWN (event type:))
				)
			)
			(EditControl theItem event)
			(return 0)
		)
		(if (= ret (self firstTrue: #handleEvent event))
			(EditControl theItem 0)
			(if (not (ret checkState: 2))
				(if theItem
					(theItem select: 0)
				)
				((= theItem ret) select: 1)
				(ret doit:)
				(= ret 0)
			)
		else
			(= ret 0)
			(cond
				(
					(and
						(or
							(== (event type:) evJOYDOWN)
							(and
								(== evKEYBOARD (event type:))
								(== KEY_RETURN (event message:))
							)
						)
						theItem
						(theItem checkState: 1)
					)
					(= ret theItem)
					(EditControl theItem 0)
					(event claimed: 1)
				)
				(
					(or
						(and
							(not (self firstTrue: #checkState 1))
							(or
								(and
									(== evKEYBOARD (event type:))
									(== KEY_RETURN (event message:))
								)
								(== evMOUSEBUTTON (event type:))
								(== evJOYDOWN (event type:))
							)
						)
						(and (== evKEYBOARD (event type:)) (== KEY_ESCAPE (event message:)))
					)
					(event claimed: 1)
					(= ret -1)
				)
				(
					(and
						(IsObject theItem)
						(theItem isType: 3)
						(== (event type:) evKEYBOARD)
						(== (event message:) KEY_RIGHT)
					)
					(if (>= (theItem cursor:) (StrLen (theItem text:)))
						(self advance:)
					else
						(EditControl theItem event)
					)
				)
				(
					(and
						(IsObject theItem)
						(theItem isType: 3)
						(== (event type:) evKEYBOARD)
						(== (event message:) KEY_LEFT)
					)
					(if (<= (theItem cursor:) 0)
						(self retreat:)
					else
						(EditControl theItem event)
					)
				)
				(
					(and
						(== evKEYBOARD (event type:))
						(OneOf (event message:) KEY_TAB KEY_RIGHT KEY_DOWN)
					)
					(event claimed: 1)
					(self advance:)
				)
				(
					(and
						(== evKEYBOARD (event type:))
						(OneOf (event message:) KEY_SHIFTTAB KEY_LEFT KEY_UP)
					)
					(event claimed: 1)
					(self retreat:)
				)
				(else
					(EditControl theItem event)
				)
			)
		)
		(return ret)
	)

	(method (move h v)
		(+= nsRight h)
		(+= nsLeft h)
		(+= nsTop v)
		(+= nsBottom v)
	)

	(method (moveTo h v)
		(self move: (- h nsLeft) (- v nsTop))
	)

	(method (center)
		(self
			moveTo:
				(+
					(window brLeft:)
					(/
						(-
							(- (window brRight:) (window brLeft:))
							(- nsRight nsLeft)
						)
						2
					)
				)
				(+
					(window brTop:)
					(/
						(-
							(- (window brBottom:) (window brTop:))
							(- nsBottom nsTop)
						)
						2
					)
				)
		)
	)

	(method (setSize &tmp node obj [r 4])
		(if text
			(TextSize @[r 0] text 0 -1 0)
			(= nsTop [r 0])
			(= nsLeft [r 1])
			(= nsBottom [r 2])
			(= nsRight [r 3])
		else
			(= nsRight (= nsBottom (= nsLeft (= nsTop 0))))
		)
		(for ((= node (self first:))) node ((= node (self next: node)))
			(= obj (NodeValue node))
			(if (< (obj nsLeft:) nsLeft)
				(= nsLeft (obj nsLeft:))
			)
			(if (< (obj nsTop:) nsTop)
				(= nsTop (obj nsTop:))
			)
			(if (> (obj nsRight:) nsRight)
				(= nsRight (obj nsRight:))
			)
			(if (> (obj nsBottom:) nsBottom)
				(= nsBottom (obj nsBottom:))
			)
		)
		(+= nsRight 4)
		(+= nsBottom 4)
		(self moveTo: 0 0)
	)
)

(class Controls of List
	(properties)

	(method (draw)
		(self eachElementDo: #setSize)
		(self eachElementDo: #draw)
	)

	(method (handleEvent event &tmp cont)
		(if (event claimed:)
			(return 0)
		)
		(if
			(and
				(= cont (self firstTrue: #handleEvent event))
				(not (cont checkState: 2))
			)
			(cont doit:)
			(= cont 0)
		)
		(return cont)
	)
)

; symbol not present in vocab.999
(procedure (StrSplit)
	(kernel_123 &rest)
)