;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 36)
(include sci.sh)
(use Main)
(use Interface)
(use CodeCue)
(use KQ5Room)
(use Polygon)
(use Chase)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm036 0
)

(local
	[local0 16] = [120 0 126 140 130 151 85 149 73 157 37 157 0 159 0 0]
	[local16 26] = [296 147 295 140 165 140 144 139 143 0 319 0 319 189 313 189 311 175 227 169 159 165 158 161 195 148]
	local42
	[local43 9] = [1028 10 100 3 9 26 24 32 32]
)

(procedure (localproc_0)
	(= [local43 1] (Min (Max 0 (- (gEgo x:) 40)) 239))
)

(instance rm036 of KQ5Room
	(properties
		picture 36
		horizon 135
		north 38
		west 35
	)

	(method (init)
		(super init:)
		(self setFeatures: path36 entrance)
		(switch gPrevRoomNum
			(north
				(gEgo posn: 149 140)
				(gGlobalSound number: 5 loop: -1 playBed:)
			)
			(else
				(gEgo posn: 15 169)
			)
		)
		(gEgo view: 10 setStep: 3 2 illegalBits: $8000 heading: 90 init:)
		(if (!= ((gInventory at: 2) owner:) 36) ; Pie
			(yeti
				moveSpeed: 1
				cycleSpeed: 1
				setCycle: Walk
				ignoreActors: 1
				setLoop: 0
				init:
				setScript: chaseEgo
			)
			(SetFlag 40)
			(gGlobalSound2 number: 110 loop: -1 vol: 127 play:)
		)
		(poly1 points: @local0 size: 8)
		(poly2 points: @local16 size: 13)
		(self addObstacle: poly1 poly2)
	)

	(method (doit &tmp temp0)
		(cond
			(script
				(script doit:)
			)
			(
				(and
					(gEgo edgeHit:)
					(= temp0 (self edgeToRoom: (gEgo edgeHit:)))
				)
				(if (== temp0 38)
					(gGlobalSound fade:)
				)
				(gCurRoom newRoom: temp0)
			)
			((& (gEgo onControl: 0) $2000)
				(if (gCast contains: yeti)
					(yeti setScript: 0 setMotion: 0)
				)
				(HandsOff)
				(self setScript: fallingDown)
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
		(DisposeScript 972)
		(super dispose:)
	)
)

(instance chaseEgo of Script
	(properties)

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (HaveMouse)
					(= cycles 10)
				else
					(= seconds 3)
				)
			)
			(1
				(gGlobalAudio number: 7064 loop: 1 play:)
				(yeti setMotion: MoveTo 168 (yeti y:) self)
			)
			(2
				(yeti setMotion: Chase gEgo 32 self)
			)
			(3
				(HandsOff)
				(gEgo setMotion: 0)
				(yeti
					illegalBits: 0
					setPri: (+ (gEgo priority:) 2)
					setMotion:
						MoveTo
						(+ (gEgo x:) 28)
						(- (gEgo y:) 3)
						self
				)
			)
			(4
				(gCurRoom setScript: bogusScript)
				(yeti loop: 3 cel: 0 cycleSpeed: 1 setCycle: End self)
			)
			(5
				(if (and (>= (gEgo heading:) 0) (<= (gEgo heading:) 180))
					(= temp0 5)
				else
					(= temp0 6)
				)
				(gEgo
					normal: 0
					setPri: (+ (yeti priority:) 1)
					setLoop: temp0
					x: (- (yeti x:) 35)
					y: (- (yeti y:) 2)
					view: 542
					ignoreActors:
					cycleSpeed: 1
					setCycle: Fwd
				)
				((gEgo head:) hide:)
				(gGlobalAudio number: 7064 loop: 1 play:)
				(= seconds 4)
			)
			(6
				(gGlobalSound2 stop:)
				(= global330 470)
				(cls)
				(EgoDead 544)
			)
		)
	)
)

(instance bogusScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0 0)
		)
	)
)

(instance throwPie of Script
	(properties)

	(method (doit)
		(super doit:)
		(if (and (not state) (<= (yeti x:) 250))
			(= cycles 1)
		)
		(if (and (== state 2) (<= (- (yeti x:) (thePie x:)) 18))
			(= cycles 1)
		)
	)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DoAudio audWPLAY 8111)
				(= local42 1)
			)
			(1
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 558
					x: (+ (gEgo x:) 13)
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: CT 6 1 self
				)
			)
			(2
				(HandsOn)
				(gEgo setCycle: End put: 2 36) ; Pie
				(HandsOff)
				(thePie
					posn: (+ (gEgo x:) 31) (- (gEgo y:) 34)
					setCycle: Walk
					setMotion: MoveTo (yeti x:) (- (yeti y:) 34)
					init:
				)
				(gGlobalSound2 stop:)
				(gGlobalSound3 number: 116 loop: 1 vol: 127 play:)
			)
			(3
				(gGlobalAudio number: 8111 loop: 1 play:)
				(thePie dispose:)
				(yeti
					illegalBits: 0
					setLoop: 1
					setScript: 0
					setStep: 2 1
					cycleSpeed: 4
					setCycle: End
					setMotion: MoveTo (yeti x:) 167 self
				)
				(chaseEgo client: 0 dispose:)
			)
			(4
				(gGlobalAudio number: 7064 loop: -1 play:)
				(yeti setLoop: 2 setCycle: Beg self)
			)
			(5
				(yeti
					setLoop: 1
					cycleSpeed: 6
					setCycle: End self
					setMotion: MoveTo (+ (yeti x:) 5) (yeti y:)
				)
			)
			(6
				(yeti setLoop: 2 cel: 0 setCycle: Beg self setStep: 4 3)
			)
			(7
				(gGlobalAudio stop:)
				(gGlobalAudio number: 8115 loop: 1 play:)
				(yeti
					setLoop: 4
					cycleSpeed: 4
					setCycle: End
					setStep: 3 5
					setMotion: MoveTo (yeti x:) 240 self
				)
			)
			(8
				(= seconds 4)
			)
			(9
				(gGlobalAudio number: 8078 loop: 1 play:)
				(ShakeScreen 7 ssFULL_SHAKE)
				(= seconds 3)
			)
			(10
				(gGlobalSound3 number: 0 stop:)
				(gEgo
					normal: 1
					view: 10
					x: (+ (gEgo x:) 11)
					loop: 0
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
				)
				((gEgo head:) show:)
				(= cycles 3)
			)
			(11
				(HandsOn)
				(SetScore 4)
				(client setScript: 0)
			)
		)
	)
)

(instance fallingDown of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 76
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					illegalBits: 0
					ignoreActors: 1
				)
			)
			(1
				(gGlobalAudio loop: 1 number: 7053 play: self)
				(gEgo yStep: 8 setMotion: MoveTo (- (gEgo x:) 20) 230)
			)
			(2
				(gGlobalAudio number: 8892 loop: 1 play:)
				(= seconds 3)
			)
			(3
				(= global330 412)
				(EgoDead)
			)
		)
	)
)

(instance path36 of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $2002))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(if (!= ((gInventory at: 2) owner:) 36) ; Pie
						(Say 472)
					else
						(Say 473)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance entrance of RFeature
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
					(Say 474)
					(event claimed: 1)
				)
				(4 ; Inventory
					(if (not (HaveMouse))
						(switch (gInventory indexOf: (gTheIconBar curInvIcon:))
							(2
								(if (> (gEgo distanceTo: yeti) 100)
									(HandsOff)
									(gCurRoom setScript: throwPie)
								else
									(Say 477 0 1)
								)
								(event claimed: 1)
							)
							(28
								(event claimed: 0)
							)
							(else
								(Say 478)
								(event claimed: 1)
							)
						)
					)
				)
			)
		)
	)
)

(instance thePie of Actor
	(properties
		x -20
		view 558
		loop 1
		priority 14
		signal 10256
		xStep 4
	)
)

(instance yeti of Actor
	(properties
		x 240
		y 149
		yStep 3
		view 542
		signal 16384
		xStep 5
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
					(Say 475)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 476)
					(event claimed: 1)
				)
				(5 ; Talk
					(gEgo setMotion: 0)
					(localproc_0)
					(proc762_1 @local43 7024)
					(event claimed: 1)
				)
				(4 ; Inventory
					(switch (gInventory indexOf: (gTheIconBar curInvIcon:))
						(2
							(if (> (gEgo distanceTo: yeti) 100)
								(HandsOff)
								(gCurRoom setScript: throwPie)
							else
								(Say 477 0 1)
							)
							(event claimed: 1)
						)
						(28
							(event claimed: 0)
						)
						(else
							(Say 478)
							(event claimed: 1)
						)
					)
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

(instance poly2 of Polygon
	(properties
		type PBarredAccess
	)
)

