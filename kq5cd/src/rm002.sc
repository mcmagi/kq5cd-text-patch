;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 2)
(include sci.sh)
(use Main)
(use Interface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm002 0
)

(local
	local0
	local1
	local2
	[local3 12] = [319 48 223 77 103 72 183 51 247 0 319 0]
	[local15 22] = [107 63 78 71 132 90 159 90 180 97 149 109 0 121 0 0 238 0 196 27 140 55]
	[local37 10] = [0 155 103 155 139 171 140 189 0 189]
	[local47 12] = [319 189 286 189 141 125 142 118 239 83 319 81]
	[local59 8] = [138 166 178 165 185 173 143 172]
	local67
	[local68 9] = [1003 215 40 4 11 25 23 31 31]
	[local77 9] = [1000 80 10 4 11 24 19 23 30]
	[local86 9] = [1009 235 20 5 11 24 18 24 23]
)

(instance rm002 of KQ5Room
	(properties
		picture 2
		horizon 45
		north 1
		east 29
		south 3
		west 7
	)

	(method (init)
		(super init:)
		(= global320 143)
		(= global321 48)
		(= global325 3023)
		(gEgo normal: 1 setStep: 3 2 view: 0)
		(self
			setFeatures: mountPath room
			setRegions: 202 ; owl
			addObstacle: poly1 poly2 poly3 poly4 poly5
		)
		(if (!= (gGlobalSound number:) 24)
			(gGlobalSound number: 24 vol: 127 loop: -1 play:)
		)
		(switch gPrevRoomNum
			(west
				(gEgo posn: 11 135)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(east
				(gEgo posn: 311 57)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(north
				(gEgo view: 2 posn: 176 51)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(south
				(gEgo posn: 214 186)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(else
				(gEgo posn: 214 186)
			)
		)
		(gEgo illegalBits: $8000 init:)
		(if (not (IsFlag 47))
			(snake cycleSpeed: 4 cel: 0 init: stopUpd:)
			(if (not (IsFlag 87))
				(SetFlag 87)
				(= local67 1)
				(snake setScript: warnScript)
			)
		)
		(poly1 points: @local3 size: 6)
		(poly2 points: @local15 size: 11)
		(poly3 points: @local37 size: 5)
		(poly4 points: @local47 size: 6)
		(poly5 points: @local59 size: 4)
	)

	(method (doit &tmp temp0)
		(cond
			((& (gEgo onControl: 1) $4000)
				(gEgo view: 2)
			)
			((& (gEgo onControl: 1) $2000)
				(gEgo view: 0)
			)
			(script
				(script doit:)
			)
			(
				(and
					(gEgo edgeHit:)
					(= temp0 (self edgeToRoom: (gEgo edgeHit:)))
				)
				((ScriptID 202 2) register: (gEgo edgeHit:)) ; stdWalkOut
				(self setScript: (ScriptID 202 2)) ; stdWalkOut
			)
			((IsFlag 15)
				(Say 177)
				(ClearFlag 15)
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
			(else
				(switch (event message:)
					(4 ; Inventory
						(if
							(and
								(==
									(gInventory indexOf: (gTheIconBar curInvIcon:))
									34
								)
								(MousedOn gEgo event)
							)
							(proc0_15 0)
							(if (gCast contains: snake)
								(SetFlag 47)
								(SetScore 3)
								(HandsOff)
								(gCurRoom setScript: shakeTambourine)
							else
								(Say 174)
							)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance warnScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 10)
			)
			(1
				(gGlobalSound3 number: 38 loop: 1 play:)
				(proc762_1 @local77 5500 self)
			)
			(2
				(= local67 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance strike of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGlobalSound2 number: 27 loop: 1 play:)
				(snake loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(1
				(snake setCycle: CT 4 1)
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 476
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(2
				(= seconds 2)
			)
			(3
				(= global330 178)
				(EgoDead 243)
			)
		)
	)
)

(instance shakeTambourine of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 230 80 self)
			)
			(1
				(= local0 (gEgo view:))
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 476
					loop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(2
				(gGlobalSound3 number: 51 loop: -1 playBed:)
				(gEgo loop: 4 cycleSpeed: 0 setCycle: Fwd)
				(= cycles 1)
			)
			(3
				(= seconds 5)
			)
			(4
				(gGlobalSound4 stop:)
				(gGlobalSound3 stop:)
				(gEgo loop: 3 cel: 2 cycleSpeed: 2 setCycle: Beg)
				(proc762_1 @local68 5501)
				(snake loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(snake dispose:)
				(gEgo
					normal: 1
					view: local0
					loop: 7
					cel: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((gEgo head:) show:)
				(= cycles 2)
			)
			(6
				(gGlobalSound4 stop:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance mountPath of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0002))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 172)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance snake of Actor
	(properties
		x 298
		y 64
		view 476
		loop 9
	)

	(method (doit &tmp temp0)
		(super doit:)
		(cond
			((gCurRoom script:)
				(proc0_15 0)
				(gGlobalSound4 fade:)
			)
			((> (= temp0 (gEgo distanceTo: self)) 70)
				(if local2
					(-- local2)
					(self cel: 0 setCycle: 0)
					(proc0_15 0)
					(gGlobalSound4 stop:)
				)
			)
			((< temp0 30)
				(proc0_15 0)
				(HandsOff)
				(gCurRoom setScript: strike)
			)
			((not local2)
				(proc0_15 0)
				(++ local2)
				(self setCycle: Fwd)
				(gGlobalSound4 number: 38 loop: -1 play:)
			)
		)
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
					(Say 173)
					(event claimed: 1)
				)
				(3 ; Do
					(proc762_1 @local86 5502)
					(event claimed: 1)
				)
				(5 ; Talk
					(Say 176)
					(event claimed: 1)
				)
				(4 ; Inventory
					(switch (gInventory indexOf: (gTheIconBar curInvIcon:))
						(34
							(proc0_15 0)
							(SetFlag 47)
							(SetScore 3)
							(HandsOff)
							(gCurRoom setScript: shakeTambourine)
							(event claimed: 1)
						)
						(28
							(event claimed: 0)
						)
						(else
							(Say 175)
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

(instance poly3 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly4 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly5 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)

	(method (handleEvent event &tmp temp0)
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
					(Say 172)
					(event claimed: 1)
				)
			)
		)
	)
)

