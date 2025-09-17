;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 56)
(include sci.sh)
(use Main)
(use Interface)
(use KQ5Room)
(use CDActor)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm056 0
)

(local
	[local0 2]
	local2
	local3
	[local4 7] = [3 0 2 2 1 3 3]
	[local11 26] = [0 189 0 0 319 0 319 189 201 189 178 161 261 160 178 160 155 102 138 160 51 160 139 161 116 189]
)

(instance rm056 of KQ5Room
	(properties
		picture 56
		north 57
		south 55
	)

	(method (init)
		(super init:)
		(gGlobalSound number: 836 loop: -1 playBed:)
		(HandsOff)
		(= global103 0)
		(gEgo illegalBits: 0 init:)
		(switch gPrevRoomNum
			(north
				(gCurRoom setScript: enterNorth)
			)
			(else
				(gEgo setStep: 3 2 posn: 156 186 0 setPri: 14)
				(gCurRoom setScript: toPlatform)
			)
		)
		(gAddToPics add: cup doit:)
		(self setFeatures: KDoor stairs cup shelves supplyRoom)
		(if (== ((gInventory at: 24) owner:) 56) ; Bag_of_Peas
			(bag init: stopUpd:)
		)
		(fire setCycle: Fwd init:)
		(cupboard init: stopUpd:)
		(poly1 points: @local11 size: 13)
		(self addObstacle: poly1)
	)

	(method (doit &tmp temp0 temp1)
		(cond
			(script
				(script doit:)
			)
			((& (= temp1 (gEgo onControl: 0)) $4000)
				(gGlobalSound fade:)
				(gCurRoom newRoom: 57)
			)
			((& temp1 $2000)
				(HandsOff)
				(self setScript: toStair)
			)
		)
	)

	(method (handleEvent event)
		(cond
			((event claimed:)
				(return)
			)
			(script
				(return)
			)
		)
	)

	(method (dispose)
		(gEgo illegalBits: $8000)
		(super dispose:)
	)
)

(instance toPlatform of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: MoveTo 157 157 self)
			)
			(1
				(gEgo
					looper: duneLooper
					setMotion: MoveTo (gEgo x:) 161 self
					setPri: -1
					setLoop: 3
					yStep: 1
				)
				((gEgo head:) show:)
			)
			(2
				(gEgo setLoop: -1)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance toStair of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo
					looper: MyLooper
					setMotion: MoveTo (gEgo x:) 161 self
					setPri: 14
					yStep: 2
				)
			)
			(1
				(gEgo setStep: 3 2 setMotion: MoveTo 155 171 self)
			)
			(2
				(gEgo setMotion: MoveTo 157 189 self)
			)
			(3
				(gGlobalSound fade:)
				(HandsOn)
				(gCurRoom newRoom: 55)
			)
		)
	)
)

(instance enterNorth of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gEgo
					posn: 156 171
					setLoop: 2
					setMotion: MoveTo 156 159 self
				)
			)
			(1
				(HandsOn)
				(gEgo looper: duneLooper setStep: 3 1)
				(client setScript: 0)
			)
		)
	)
)

(instance duneLooper of Script
	(properties)

	(method (doit)
		(if (!= local3 (- 164 (- ((gEgo mover:) y:) 164)))
			(= local3 ((gEgo mover:) y:))
			((gEgo mover:) y: (- 164 (- ((gEgo mover:) y:) 164)))
		)
		(gEgo loop: [local4 (/ (gEgo heading:) 60)])
	)
)

(instance openCupboard of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: MoveTo 69 162 self)
			)
			(1
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 680
					setLoop: 3
					cel: 0
					cycleSpeed: 1
					setCycle: CT 2 1 self
				)
			)
			(2
				(gEgo setCycle: End)
				(cupboard setCycle: End self)
				(gGlobalAudio number: 8796 loop: 1 play:)
			)
			(3
				(cupboard stopUpd:)
				(Say 597)
				(bCel1 init: stopUpd:)
				(bCel2 init: stopUpd:)
				(gEgo
					normal: 1
					view: 0
					setLoop: -1
					loop: 7
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((gEgo head:) show:)
				(= cycles 3)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getPeas of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: MoveTo 70 161 self)
			)
			(1
				(Say 598)
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 680
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
				(gEgo get: 24) ; Bag_of_Peas
				(SetScore 2)
				(-- local2)
				(bag dispose:)
				(bCel2 dispose:)
			)
			(2
				(cupboard setCycle: Beg self)
				(gGlobalAudio number: 8795 loop: 1 play:)
			)
			(3
				(cupboard stopUpd:)
				((gEgo head:) show:)
				(= cycles 1)
			)
			(4
				(bCel1 dispose:)
				(gEgo
					normal: 1
					view: 0
					setLoop: -1
					loop: 7
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(= cycles 3)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance supplyRoom of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 591)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance KDoor of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0400))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 592)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance shelves of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0008))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 593)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 599)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance stairs of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(and
					(not (& (OnControl CONTROL (event x:) (event y:)) $0010))
					(not (& (OnControl CONTROL (event x:) (event y:)) $0040))
				)
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 591)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cupboard of Prop
	(properties
		x 51
		y 142
		view 680
		signal 1
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 594)
					(event claimed: 1)
				)
				(3 ; Do
					(cond
						((!= ((gInventory at: 24) owner:) 56) ; Bag_of_Peas
							(Say 600)
						)
						((not local2)
							(++ local2)
							(HandsOff)
							(gCurRoom setScript: openCupboard)
						)
						((== ((gInventory at: 24) owner:) 56) ; Bag_of_Peas
							(Say 601)
						)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bag of Prop
	(properties
		x 55
		y 125
		view 680
		loop 1
		priority 2
		signal 17
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
				(not local2)
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 595)
					(event claimed: 1)
				)
				(3 ; Do
					(HandsOff)
					(gCurRoom setScript: getPeas)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cup of RPicView
	(properties
		x 51
		y 142
		view 680
		loop 1
		cel 1
		priority 1
	)
)

(instance fire of Prop
	(properties
		x 169
		y 156
		view 680
		loop 5
		priority 1
		signal 16400
		cycleSpeed 2
		detailLevel 3
	)
)

(instance bCel1 of Prop
	(properties
		x 55
		y 91
		view 680
		loop 4
		priority 1
		signal 17
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 596)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bCel2 of Prop
	(properties
		x 34
		y 75
		view 680
		loop 4
		cel 1
		priority 2
		signal 17
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 594)
					(event claimed: 1)
				)
				(3 ; Do
					(if (== ((gInventory at: 24) owner:) 56) ; Bag_of_Peas
						(HandsOff)
						(gCurRoom setScript: getPeas)
					else
						(Say 600)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)

