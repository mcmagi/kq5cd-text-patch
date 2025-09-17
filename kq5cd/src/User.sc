;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 996)
(include sci.sh)
(use Main)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(local
	[inputLine 23]
	inputLen
)

(instance uEvt of Event
	(properties)
)

(class User of Obj
	(properties
		alterEgo 0
		input 0
		controls 0
		echo 32
		prevDir 0
		prompt {Enter input}
		inputLineAddr 0
		x -1
		y -1
		mapKeyToDir 1
		curEvent 0
		verbMessager 0
	)

	(method (init inLine length)
		(= inputLineAddr (if argc inLine else @inputLine))
		(= inputLen (if (== argc 2) length else 45))
		(= curEvent uEvt)
	)

	(method (canInput n)
		(if argc
			(= input n)
		)
		(return input)
	)

	(method (doit)
		(curEvent type: 0 message: 0 modifiers: 0 y: 0 x: 0 claimed: 0 port: 0)
		(GetEvent evALL_EVENTS curEvent)
		(self handleEvent: curEvent)
	)

	(method (handleEvent event &tmp evType dir)
		(= gMouseX (event x:))
		(= gMouseY (event y:))
		(if (event type:)
			(= gLastEvent event)
			(if mapKeyToDir
				(MapKeyToDir event)
			)
			(if (== (event type:) evJOYDOWN)
				(event
					type: evKEYBOARD
					message: (if (& (event modifiers:) emSHIFT) 27 else 13)
					modifiers: $0000
				)
			)
			(= evType (event type:))
			(if global68
				(global68 handleEvent: event evType)
			)
			(event localize:)
			(cond
				((& (event type:) $0040) ; direction
					(or
						(and gPMouse (gPMouse handleEvent: event))
						(and gDirectionHandler (gDirectionHandler handleEvent: event))
						(and alterEgo controls (alterEgo handleEvent: event))
						(and gTheIconBar (gTheIconBar handleEvent: event))
					)
				)
				((== evType evKEYBOARD)
					(if gKeyDownHandler
						(gKeyDownHandler handleEvent: event)
					)
				)
				((and (== evType evMOUSEBUTTON) gMouseDownHandler)
					(gMouseDownHandler handleEvent: event)
				)
			)
		)
		(if (not (event claimed:))
			(if gTheIconBar
				(gTheIconBar handleEvent: event)
			)
			(if (and (== (event type:) evVERB) input)
				(cond
					(
						(and
							(== (event message:) 1)
							controls
							(alterEgo handleEvent: event)
						)
						1
					)
					(gUseSortedFeatures
						(OnMeAndLowY init:)
						(gCast eachElementDo: #perform OnMeAndLowY event)
						(gFeatures eachElementDo: #perform OnMeAndLowY event)
						(if (OnMeAndLowY theObj:)
							((OnMeAndLowY theObj:) handleEvent: event)
						)
					)
					((gCast handleEvent: event) 1)
					((gFeatures handleEvent: event) 1)
				)
				(cond
					((event claimed:) 1)
					((gRegions handleEvent: event) 1)
				)
			)
			(if (and (event type:) (not (event claimed:)))
				(gGame handleEvent: event)
			)
		)
	)

	(method (getInput &tmp temp0))

	(method (canControl value)
		(if argc
			(= controls value)
			(= prevDir 0)
		)
		(return controls)
	)

	(method (said &tmp temp0))
)

(class Ego of Actor
	(properties
		signal 8192
		edgeHit 0
	)

	(method (init)
		(super init:)
		(if (not cycler)
			(self setCycle: Walk)
		)
	)

	(method (doit)
		(super doit:)
		(= edgeHit
			(cond
				((<= x 0) 4)
				((>= x 319) 2)
				((>= y 189) 3)
				((<= y (gCurRoom horizon:)) 1)
				(else 0)
			)
		)
	)

	(method (get what &tmp i)
		(for ((= i 0)) (< i argc) ((++ i))
			((gInventory at: [what i]) moveTo: self)
		)
	)

	(method (put what recipient &tmp temp0)
		(if (self has: what)
			((= temp0 (gInventory at: what))
				moveTo: (if (== argc 1) -1 else recipient)
			)
			(if (and gTheIconBar (== (gTheIconBar curInvIcon:) temp0))
				(gTheIconBar
					curInvIcon: 0
					disable: ((gTheIconBar useIconItem:) cursor: 999 yourself:)
				)
			)
		)
	)

	(method (has what &tmp theItem)
		(if (= theItem (gInventory at: what))
			(theItem ownedBy: self)
		)
	)

	(method (handleEvent event &tmp temp0)
		(if script
			(script handleEvent: event)
		)
		(cond
			((or (event claimed:) (not (gCast contains: self))) 1)
			((and (& (event type:) $0040) (gUser controls:)) ; direction
				(if
					(and
						(== (= temp0 (event message:)) 0)
						(& (event type:) evKEYBOARD)
					)
					(event claimed:)
					(return)
				)
				(if (and (== temp0 (gUser prevDir:)) (IsObject mover))
					(= temp0 0)
				)
				(gUser prevDir: temp0)
				(self setDirection: temp0)
				(event claimed: 1)
			)
			((or (== (event type:) evVERB) (== (event type:) evMOUSEBUTTON))
				(if
					(and
						(or (== (event message:) 1) (== (event type:) evMOUSEBUTTON))
						(gUser controls:)
					)
					(self
						setMotion:
							(if gUseObstacles PolyPath else MoveTo)
							(event x:)
							(+ (event y:) z)
					)
					(gUser prevDir: 0)
					(event claimed: 1)
				else
					(super handleEvent: event)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
		(event claimed:)
	)
)

(class OnMeAndLowY of Code
	(properties
		theObj 0
		lastY -1
	)

	(method (init)
		(= theObj 0)
		(= lastY -1)
	)

	(method (doit thisObj event)
		(if (and (thisObj onMe: event) (> (thisObj y:) lastY))
			(= theObj thisObj)
			(= lastY (theObj y:))
		)
	)
)

