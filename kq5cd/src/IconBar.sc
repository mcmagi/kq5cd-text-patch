;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 937)
(include sci.sh)
(use Main)
(use System)

(class IconI of Obj
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state 0
		cursor -1
		type 16384
		message -1
		modifiers 0
		signal 1
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
	)

	(method (show iX iY &tmp [temp0 7])
		(|= signal $0020)
		(if argc
			(= nsRight (+ (= nsLeft iX) (CelWide view loop cel)))
			(= nsBottom (+ (= nsTop iY) (CelHigh view loop cel)))
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if (& signal $0004)
			(self mask:)
		)
		(if (and gPMouse (gPMouse respondsTo: #stop))
			(gPMouse stop:)
		)
	)

	(method (mask)
		(DrawCel
			maskView
			maskLoop
			maskCel
			(+
				nsLeft
				(/
					(-
						(CelWide view loop cel)
						(CelWide maskView maskLoop maskCel)
					)
					2
				)
			)
			(+
				nsTop
				(/
					(-
						(CelHigh view loop cel)
						(CelHigh maskView maskLoop maskCel)
					)
					2
				)
			)
			-1
		)
	)

	(method (onMe obj)
		(return
			(and
				(>= (obj x:) nsLeft)
				(>= (obj y:) nsTop)
				(<= (obj x:) nsRight)
				(<= (obj y:) nsBottom)
			)
		)
	)

	(method (highlight tOrF &tmp t l b r sColor)
		(if (not (& signal $0020))
			(return)
		)
		(= sColor (if (and argc tOrF) highlightColor else lowlightColor))
		(= t (+ nsTop 2))
		(= l (+ nsLeft 2))
		(= b (- nsBottom 3))
		(= r (- nsRight 4))
		(Graph grDRAW_LINE t l t r sColor -1 -1)
		(Graph grDRAW_LINE t r b r sColor -1 -1)
		(Graph grDRAW_LINE b r b l sColor -1 -1)
		(Graph grDRAW_LINE b l t l sColor -1 -1)
		(Graph grUPDATE_BOX (- nsTop 2) (- nsLeft 2) nsBottom (+ nsRight 3) 1)
	)

	(method (select relVer &tmp event whichCel)
		(return
			(cond
				((& signal $0004) 0)
				((and argc relVer (& signal $0001))
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
					(while (!= ((= event (Event new:)) type:) evMOUSERELEASE)
						(event localize:)
						(cond
							((self onMe: event)
								(if (not whichCel)
									(DrawCel
										view
										loop
										(= whichCel 1)
										nsLeft
										nsTop
										-1
									)
									(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
					)
					whichCel
				)
				(else 1)
			)
		)
	)
)

(instance ibEvent of Event
	(properties)
)

(class IconBar of Set
	(properties
		height 10
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		port 0
		window 0
		state 1024
		activateHeight 10
		y 0
	)

	(method (handleEvent event &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp2 (GetPort))
		(SetPort -1)
		(event localize:)
		(= temp3 (event x:))
		(= temp4 (event y:))
		(SetPort temp2)
		(event localize:)
		(cond
			((& state $0004))
			(
				(or
					(and
						(not (= temp1 (event type:)))
						(& state $0400)
						(<= 0 temp4 height)
						(<= 0 temp3 320)
						(not (= temp0 0))
					)
					(and
						(== temp1 evKEYBOARD)
						(or
							(== (event message:) KEY_ESCAPE)
							(== (event message:) KEY_DELETE)
						)
						(= temp0 1)
					)
				)
				(= oldMouseX (event x:))
				(= oldMouseY (event y:))
				(self show:)
				(if temp0
					(if global400
						(gGame
							setCursor:
								gTheCursor
								1
								(+
									(curIcon nsLeft:)
									(/
										(- (curIcon nsRight:) (curIcon nsLeft:))
										2
									)
								)
								(- (curIcon nsBottom:) 13)
						)
					else
						(gGame setCursor: gTheCursor 1)
						(MoveCursor
							(+
								(curIcon nsLeft:)
								(/ (- (curIcon nsRight:) (curIcon nsLeft:)) 2)
							)
						)
					)
				)
				(self doit:)
				(if temp0
					(if global400
						(gGame
							setCursor: (curIcon cursor:) 1 oldMouseX oldMouseY
						)
					else
						(gGame setCursor: (curIcon cursor:) 1)
						(MoveCursor oldMouseX oldMouseY)
					)
				else
					(gGame setCursor: (curIcon cursor:) 1)
				)
				(self hide:)
			)
			((& temp1 evKEYBOARD)
				(switch (event message:)
					(KEY_RETURN
						(if (IsObject curIcon)
							(event
								type: (curIcon type:)
								message: (curIcon message:)
							)
						)
					)
					(KEY_INSERT
						(self swapCurIcon:)
						(event claimed: 1)
					)
					(0
						(if (& (event type:) $0040) ; direction
							(self advanceCurIcon:)
							(event claimed: 1)
						)
					)
				)
			)
			((& temp1 evMOUSEBUTTON)
				(cond
					((& (event modifiers:) emSHIFT)
						(self advanceCurIcon:)
						(event claimed: 1)
					)
					((& (event modifiers:) emCTRL)
						(self swapCurIcon:)
						(event claimed: 1)
					)
					((IsObject curIcon)
						(event
							type: (curIcon type:)
							message: (curIcon message:)
						)
					)
				)
			)
		)
	)

	(method (disable theIconNumber &tmp i thisIcon)
		(if argc
			(for ((= i 0)) (< i argc) ((++ i))
				(= thisIcon
					(if (IsObject [theIconNumber i])
						[theIconNumber i]
					else
						(self at: [theIconNumber i])
					)
				)
				(thisIcon signal: (| (thisIcon signal:) $0004))
				(cond
					((== thisIcon highlightedIcon)
						(self advance:)
					)
					((== thisIcon curIcon)
						(self advanceCurIcon:)
					)
				)
			)
		else
			(|= state $0004)
		)
	)

	(method (enable theIconNumber &tmp i thisIcon)
		(if argc
			(for ((= i 0)) (< i argc) ((++ i))
				(= thisIcon
					(if (IsObject [theIconNumber i])
						[theIconNumber i]
					else
						(self at: [theIconNumber i])
					)
				)
				(thisIcon signal: (& (thisIcon signal:) $fffb))
			)
		else
			(&= state $fffb)
		)
	)

	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(gSounds pause:)
		(|= state $0020)
		(gGame setCursor: gNormalCursor 1)
		(= height
			(CelHigh ((= theIcon (self at: 0)) view:) (theIcon loop:) (theIcon cel:))
		)
		(= port (GetPort))
		(SetPort -1)
		(= underBits (Graph grSAVE_BOX y 0 (+ y height) 320 1))
		(= pnv (PicNotValid))
		(PicNotValid 1)
		(= theX 0)
		(= theY y)
		(for ((= node (FirstNode elements))) node ((= node nextNode))
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(if (<= (obj nsRight:) 0)
				(obj show: theX theY)
				(= theX (obj nsRight:))
			else
				(obj show:)
			)
		)
		(if curInvIcon
			(if (gEgo has: (gInventory indexOf: curInvIcon))
				(= theX
					(+
						(/
							(-
								(- (useIconItem nsRight:) (useIconItem nsLeft:))
								(CelWide
									(curInvIcon view:)
									(+ (curInvIcon loop:) 1)
									(curInvIcon cel:)
								)
							)
							2
						)
						(useIconItem nsLeft:)
					)
				)
				(= theY
					(+
						y
						(/
							(-
								(- (useIconItem nsBottom:) (useIconItem nsTop:))
								(CelHigh
									(curInvIcon view:)
									(+ (curInvIcon loop:) 1)
									(curInvIcon cel:)
								)
							)
							2
						)
						(useIconItem nsTop:)
					)
				)
				(DrawCel
					(curInvIcon view:)
					(+ (curInvIcon loop:) 1)
					(curInvIcon cel:)
					theX
					theY
					-1
				)
				(if (& (useIconItem signal:) $0004)
					(useIconItem mask:)
				)
			else
				(= curInvIcon 0)
			)
		)
		(PicNotValid pnv)
		(Graph grUPDATE_BOX y 0 (+ y height) 320 1)
		(self highlight: curIcon)
		(gGame
			setCursor:
				gTheCursor
				(+
					(curIcon nsLeft:)
					(/ (- (curIcon nsLeft:) (curIcon nsRight:)) 2)
				)
		)
	)

	(method (hide &tmp node nextNode obj)
		(if
			(and
				(& state $0020)
				(if (IsObject helpIconItem)
					(not (& (helpIconItem signal:) $0010))
				else
					1
				)
			)
			(gSounds pause: 0)
			(&= state $ffdf)
			(for ((= node (FirstNode elements))) node ((= node nextNode))
				(= nextNode (NextNode node))
				(if (not (IsObject (= obj (NodeValue node))))
					(return)
				)
				((= obj (NodeValue node)) signal: (& (obj signal:) $ffdf))
			)
			(Graph grRESTORE_BOX underBits)
			(Graph grUPDATE_BOX y 0 (+ y height) 320 1)
			(Graph grREDRAW_BOX y 0 (+ y height) 320)
			(SetPort port)
			(= height activateHeight)
		)
	)

	(method (doit)
		(while (& state $0020)
			(ibEvent
				type: 0
				message: 0
				modifiers: 0
				y: 0
				x: 0
				claimed: 0
				port: 0
			)
			(GetEvent evALL_EVENTS ibEvent)
			(if (== (ibEvent type:) 256)
				(ibEvent
					type: 4
					message: (if (& (ibEvent modifiers:) $0003) 27 else 13)
					modifiers: 0
				)
			)
			(ibEvent localize:)
			(if
				(and
					(or
						(== (ibEvent type:) 1)
						(and (== (ibEvent type:) 4) (== (ibEvent message:) 13))
					)
					(IsObject helpIconItem)
					(& (helpIconItem signal:) $0010)
				)
				(ibEvent type: 16384 message: (helpIconItem message:))
			)
			(MapKeyToDir ibEvent)
			(if (self dispatchEvent: ibEvent)
				(break)
			)
		)
		(if (IsObject ibEvent)
			(ibEvent dispose:)
		)
	)

	(method (dispatchEvent event &tmp evtY evtX evtType evtMsg thisIcon evtClaimed thePort)
		(= evtX (event x:))
		(= evtY (event y:))
		(= evtType (event type:))
		(= evtMsg (event message:))
		(= evtClaimed (event claimed:))
		(= thisIcon (self firstTrue: #onMe event))
		(event dispose:)
		(if (& evtType $0040) ; direction
			(switch evtMsg
				(3
					(self advance:)
				)
				(7
					(self retreat:)
				)
			)
		else
			(switch evtType
				(evNULL
					(cond
						((not (and (<= 0 evtY (+ y height)) (<= 0 evtX 320)))
							(if
								(and
									(& state $0400)
									(or
										(not (IsObject helpIconItem))
										(not (& (helpIconItem signal:) $0010))
									)
								)
								(= oldMouseY 0)
								(= evtClaimed 1)
							)
						)
						((and thisIcon (!= thisIcon highlightedIcon))
							(= oldMouseY 0)
							(self highlight: thisIcon)
						)
					)
				)
				(evMOUSEBUTTON
					(if (and thisIcon (self select: thisIcon 1))
						(if (== thisIcon helpIconItem)
							(if (thisIcon cursor:)
								(gGame setCursor: (thisIcon cursor:))
							)
							(helpIconItem
								signal: (| (helpIconItem signal:) $0010)
							)
						else
							(= evtClaimed (& (thisIcon signal:) $0040))
						)
					)
				)
				(evKEYBOARD
					(switch evtMsg
						(KEY_ESCAPE
							(= evtClaimed 1)
						)
						(KEY_RETURN
							(if (not thisIcon)
								(= thisIcon highlightedIcon)
							)
							(cond
								((and thisIcon (== thisIcon helpIconItem))
									(if (!= (thisIcon cursor:) -1)
										(gGame setCursor: (thisIcon cursor:))
									)
									(if helpIconItem
										(helpIconItem
											signal:
												(| (helpIconItem signal:) $0010)
										)
									)
								)
								((IsObject thisIcon)
									(self select: thisIcon)
									(= evtClaimed (& (thisIcon signal:) $0040))
								)
							)
						)
						(KEY_SHIFTTAB
							(self retreat:)
						)
						(KEY_TAB
							(self advance:)
						)
					)
				)
				(evVERB
					(if (== evtMsg 6)
						(if (and thisIcon (thisIcon helpStr:))
							(= thePort (GetPort))
							(DoAudio audPLAY (thisIcon helpStr:))
							(SetPort thePort)
						)
						(if helpIconItem
							(helpIconItem
								signal: (& (helpIconItem signal:) $ffef)
							)
						)
						(gGame setCursor: gNormalCursor)
					)
				)
			)
		)
		(return evtClaimed)
	)

	(method (advance &tmp theIcon i)
		(for ((= i 1)) (<= i size) ((= i (mod (+ i 1) size)))
			(= theIcon
				(self at: (mod (+ i (self indexOf: highlightedIcon)) size))
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(breakif (not (& (theIcon signal:) $0004)))
		)
		(if (& state $0020)
			(if global400
				(gGame
					setCursor:
						gTheCursor
						1
						(+
							(theIcon nsLeft:)
							(/ (- (theIcon nsRight:) (theIcon nsLeft:)) 2)
						)
						(- (theIcon nsBottom:) 3)
				)
			else
				(gGame setCursor: gTheCursor 1)
				(MoveCursor
					(+
						(theIcon nsLeft:)
						(/ (- (theIcon nsRight:) (theIcon nsLeft:)) 2)
					)
					(- (theIcon nsBottom:) 3)
				)
			)
		)
		(self highlight: theIcon)
	)

	(method (retreat &tmp theIcon i)
		(for ((= i 1)) (<= i size) ((= i (mod (+ i 1) size)))
			(= theIcon (self at: (- (self indexOf: highlightedIcon) i)))
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(breakif (not (& (theIcon signal:) $0004)))
		)
		(if (& state $0020)
			(if global400
				(gGame
					setCursor:
						gTheCursor
						1
						(+
							(theIcon nsLeft:)
							(/ (- (theIcon nsRight:) (theIcon nsLeft:)) 2)
						)
						(- (theIcon nsBottom:) 3)
				)
			else
				(gGame
					setCursor:
						gTheCursor
						1
						(+
							(theIcon nsLeft:)
							(/ (- (theIcon nsRight:) (theIcon nsLeft:)) 2)
						)
						(- (theIcon nsBottom:) 3)
				)
				(MoveCursor
					(+
						(theIcon nsLeft:)
						(/ (- (theIcon nsRight:) (theIcon nsLeft:)) 2)
					)
					(- (theIcon nsBottom:) 3)
				)
			)
		)
		(self highlight: theIcon)
	)

	(method (select theIcon relVer)
		(return
			(if (theIcon select: (and (>= argc 2) relVer))
				(if (not (& (theIcon signal:) $0002))
					(= curIcon theIcon)
				)
				1
			)
		)
	)

	(method (highlight param1 &tmp temp0)
		(if (not (& (param1 signal:) $0004))
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight: 0)
			)
			(= highlightedIcon param1)
			(highlightedIcon highlight: 1)
		)
	)

	(method (advanceCurIcon &tmp theIcon i)
		(if (& state $0004)
			(return)
		)
		(= theIcon curIcon)
		(= i 0)
		(while
			(&
				((= theIcon (self at: (mod (+ (self indexOf: theIcon) 1) size)))
					signal:
				)
				$0006
			)
			(if (> i (+ 1 size))
				(return)
			else
				(++ i)
			)
		)
		(= curIcon theIcon)
		(gGame setCursor: (curIcon cursor:) 1)
	)

	(method (swapCurIcon &tmp firstIcon)
		(cond
			((& state $0004)
				(return)
			)
			(
				(and
					(!= curIcon (= firstIcon (NodeValue (self first:))))
					(not (& (firstIcon signal:) $0004))
				)
				(= prevIcon curIcon)
				(= curIcon (NodeValue (self first:)))
			)
			((and prevIcon (not (& (prevIcon signal:) $0004)))
				(= curIcon prevIcon)
			)
		)
		(gGame setCursor: (curIcon cursor:) 1)
	)
)

