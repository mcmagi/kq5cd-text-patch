;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 29)
(include sci.sh)
(use Main)
(use Interface)
(use CodeCue)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use System)

(public
	rm029 0
)

(local
	local0
	local1
	[local2 32] = [0 162 146 119 223 79 232 60 196 58 192 58 119 55 112 51 117 47 229 38 252 41 286 32 273 0 319 0 319 189 0 189]
	[local34 30] = [0 0 257 0 268 28 190 28 181 35 120 39 99 51 107 62 189 62 190 69 144 81 149 88 121 103 57 119 0 127]
	[local64 9] = [1000 90 40 4 11 24 19 23 30]
)

(instance rm029 of KQ5Room
	(properties
		picture 29
		north 30
		west 2
	)

	(method (init)
		(super init:)
		(= global320 119)
		(= global321 63)
		(= global325 3057)
		(self setFeatures: path29 cliffs setRegions: 202) ; owl
		(switch gPrevRoomNum
			(west
				(gEgo view: 2 posn: 6 151)
				(= local0 1)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
				(gGlobalSound number: 5 loop: -1 play:)
			)
			(north
				(gEgo
					normal: 1
					view: (if (IsFlag 15) 14 else 108)
					posn: 261 35
				)
				(++ local1)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(else
				(gGlobalSound number: 5 loop: -1 play:)
				(gEgo view: 2 posn: 6 151)
				(= local0 1)
			)
		)
		(gEgo setStep: 2 1 init:)
		(poly1 points: @local2 size: 16)
		(poly3 points: @local34 size: 15)
		(self addObstacle: poly1 poly3)
	)

	(method (doit &tmp temp0 temp1 temp2)
		(cond
			(script
				(script doit:)
			)
			((not local1)
				(++ local1)
				(gEgo setScript: timedMess)
			)
			((& (= temp1 (gEgo onControl: 1)) $0004)
				((ScriptID 202 2) register: 1) ; stdWalkOut
				(self setScript: (ScriptID 202 2)) ; stdWalkOut
			)
			(
				(and
					(gEgo edgeHit:)
					(= temp0 (self edgeToRoom: (gEgo edgeHit:)))
				)
				((ScriptID 202 2) register: (gEgo edgeHit:)) ; stdWalkOut
				(gGlobalSound fade:)
				(self setScript: (ScriptID 202 2)) ; stdWalkOut
			)
			(
				(and
					(not (IsFlag 15))
					(not (== (= temp2 (gEgo view:)) 106))
					(not (== temp2 108))
					(> (gEgo x:) 20)
				)
				(Say 410)
				((gEgo head:) hide:)
				(gEgo view: 106)
			)
			((& (gEgo onControl: 0) $2000)
				(self setScript: falling)
			)
			((& temp1 $1000)
				(if (IsFlag 15)
					(gEgo view: 14)
					((gEgo head:) show:)
				else
					(gEgo view: 108)
					((gEgo head:) hide:)
				)
			)
			((& temp1 $4000)
				(gEgo normal: 1)
				(if (IsFlag 15)
					(gEgo view: 12)
				else
					(gEgo view: 106)
				)
				((gEgo head:) show:)
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
						(if (MousedOn gEgo event)
							(switch (gInventory indexOf: (gTheIconBar curInvIcon:))
								(26
									(if (not (IsFlag 15))
										(if (not (IsFlag 48))
											(SetFlag 48)
											(SetScore 4)
										)
										(gGlobalSound2 fade:)
										(SetFlag 15)
										(Say 415)
										(gEgo
											view:
												(if (== (gEgo view:) 108)
													14
												else
													12
												)
										)
										((gEgo head:) show:)
										(event claimed: 1)
									)
								)
								(28
									(event claimed: 0)
								)
								(else
									(if (not (IsFlag 15))
										(Say 416)
										(event claimed: 1)
									)
								)
							)
						)
					)
				)
			)
		)
	)

	(method (dispose)
		(super dispose:)
	)
)

(instance timedMess of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say 411 0 1)
				(proc0_15 (Format @global185 29 0)) ; "A few hours later....."
				(= seconds 30)
			)
			(1
				(proc0_15 0)
				(client setScript: 0)
			)
		)
	)
)

(instance falling of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(proc762_1 @local64 3058 self)
			)
			(1
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view:
						(cond
							((== (gEgo view:) 2)
								(if (IsFlag 15) 78 else 70)
							)
							((IsFlag 15) 80)
							(else 72)
						)
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					setPri:
						(if (< (gEgo y:) 100)
							1
						else
							(gEgo priority:)
						)
					illegalBits: 0
				)
				(gGlobalAudio number: 7053 play:)
				(gGlobalSound3 number: 83 loop: 1 vol: 127 priority: 15 play:)
			)
			(2
				(gEgo
					yStep: 8
					setMotion: MoveTo (- (gEgo x:) 20) 230 self
				)
			)
			(3
				(= seconds 3)
			)
			(4
				(= global330 412)
				(EgoDead)
			)
		)
	)
)

(instance path29 of RFeature
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
					(Say 413)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cliffs of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0040))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 414)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type PTotalAccess
	)
)

(instance poly3 of Polygon
	(properties
		type PBarredAccess
	)
)

