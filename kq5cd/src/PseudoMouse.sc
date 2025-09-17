;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 933)
(include sci.sh)
(use Main)
(use System)

(class PseudoMouse of Code
	(properties
		cursorInc 2
		minInc 2
		maxInc 20
		prevDir 0
		joyInc 5
	)

	(method (handleEvent event)
		(if (and (gUser canInput:) (& (event type:) $0040)) ; direction
			(= prevDir
				(if (or (not gTheIconBar) (!= ((gTheIconBar curIcon:) message:) 1))
					(= prevDir (event message:))
				else
					(return)
				)
			)
			(= cursorInc
				(if (& (event type:) evKEYBOARD)
					(if (& (event modifiers:) emSHIFT) minInc else maxInc)
				else
					joyInc
				)
			)
			(cond
				((& (event type:) evKEYBOARD)
					(if prevDir
						(self doit:)
					else
						(event claimed: 0)
						(return)
					)
				)
				(prevDir
					(self start:)
				)
				(else
					(self stop:)
				)
			)
			(event claimed: 1)
			(return)
		)
	)

	(method (start dir)
		(if argc
			(= prevDir dir)
		)
		(gTheDoits add: self)
	)

	(method (stop)
		(= prevDir 0)
		(gTheDoits delete: self)
	)

	(method (doit &tmp theX theY)
		(= theX (gLastEvent x:))
		(= theY (gLastEvent y:))
		(switch prevDir
			(1
				(-= theY cursorInc)
			)
			(2
				(+= theX cursorInc)
				(-= theY cursorInc)
			)
			(3
				(+= theX cursorInc)
			)
			(4
				(+= theX cursorInc)
				(+= theY cursorInc)
			)
			(5
				(+= theY cursorInc)
			)
			(6
				(-= theX cursorInc)
				(+= theY cursorInc)
			)
			(7
				(-= theX cursorInc)
			)
			(8
				(-= theX cursorInc)
				(-= theY cursorInc)
			)
		)
		(if global400
			(gGame setCursor: gTheCursor 1 theX theY)
		else
			(gGame setCursor: gTheCursor)
			(MoveCursor theX theY)
		)
	)
)

