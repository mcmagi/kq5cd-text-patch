;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 17)
(include sci.sh)
(use Main)
(use Interface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Chase)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm017 0
)

(local
	local0
	local1
	local2
	[local3 8] = [165 111 188 111 188 116 164 116]
	[local11 36] = [0 189 0 7 319 7 319 189 192 189 193 152 243 151 241 137 220 136 207 129 220 120 208 108 150 103 129 112 93 123 95 135 129 151 139 189]
	[local47 8] = [130 112 154 112 154 124 94 123]
)

(instance rm017 of KQ5Room
	(properties
		picture 17
		south 216
	)

	(method (init)
		(super init:)
		(self setFeatures: tent)
		(gGlobalSound number: 48 loop: -1 play:)
		(LoadMany rsVIEW 392 394)
		(if (IsFlag 32)
			(if (== ((gInventory at: 7) owner:) 17) ; Staff
				(staff init: stopUpd:)
				(glint init: setScript: glimmer)
			)
			(zzzz init: setScript: snore)
			(bandit init: cycleSpeed: 5 setCycle: Fwd)
			(self addObstacle: poly3)
		)
		(gEgo view: 0 illegalBits: $8000 setStep: 3 2 posn: 174 173 init:)
		(poly1 points: @local3 size: 4)
		(poly2 points: @local11 size: 18)
		(poly3 points: @local47 size: 4)
		(self setScript: walkInScript)
		(self addObstacle: poly1 poly2)
	)

	(method (doit &tmp temp0 temp1)
		(cond
			(script
				(script doit:)
			)
			(
				(and
					(gEgo edgeHit:)
					(= temp0 (self edgeToRoom: (gEgo edgeHit:)))
				)
				(gCurRoom newRoom: temp0)
			)
			((and (& (= temp1 (gEgo onControl: 0)) $0002) (IsFlag 32))
				(HandsOff)
				(self setScript: wakeUp)
			)
			((& temp1 $4000)
				(gCurRoom newRoom: 216)
			)
			((and (< local2 750) (IsFlag 32))
				(++ local2)
			)
			((and (not (== gHowFast 0)) (== local2 750))
				(HandsOff)
				(self setScript: wakeUp)
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
		(DisposeScript 985)
		(super dispose:)
	)

	(method (newRoom newRoomNumber)
		(gGlobalSound fade:)
		(super newRoom: newRoomNumber)
	)
)

(instance walkInScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gEgo setMotion: MoveTo 180 148 self)
			)
			(1
				(HandsOn)
				(if (and (IsFlag 32) (IsFlag 92))
					(self setScript: wakeUp)
				else
					(client setScript: 0)
				)
			)
		)
	)
)

(instance wakeUp of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gEgo setMotion: 0)
				(proc0_7 gEgo bandit 5)
				(gGlobalAudio stop:)
				(zzzz dispose:)
				(gGlobalSound number: 129 loop: 1 vol: 127 play:)
				(bandit
					loop: 1
					cycleSpeed: 0
					cel: 0
					ignoreActors: 1
					setCycle: End self
				)
				(gEgo illegalBits: 0 ignoreActors: 1)
			)
			(1
				(Say 5900)
				(if (> (gEgo distanceTo: bandit) 25)
					(bandit
						view: 392
						setStep: 4 4
						setLoop: -1
						setCycle: Walk
						illegalBits: 0
						setMotion: Chase gEgo 25 self
					)
				else
					(= cycles 1)
				)
			)
			(2
				(bandit view: 394)
				(proc0_7 bandit gEgo 5)
				(= cycles 2)
			)
			(3
				(bandit view: 394 cel: 0 cycleSpeed: 2 setCycle: End self)
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 394
					setLoop: (+ (bandit loop:) 4)
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(4
				(Say 7772 0 1)
			)
			(5
				(= seconds 3)
			)
			(6
				(= global330 318)
				(EgoDead)
			)
		)
	)
)

(instance getStaff of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 143 109 self)
			)
			(1
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 396
					loop: 0
					cel: 0
					setAvoider: 0
					cycleSpeed: 1
					setCycle: CT 1 1 self
				)
			)
			(2
				(gEgo get: 7) ; Staff
				(glint setScript: 0 dispose:)
				(gEgo setCycle: End self)
			)
			(3
				(SetScore 2)
				(staff dispose:)
				(Say 319)
				(gEgo
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					loop: -1
					cel: 7
				)
				((gEgo head:) show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance glimmer of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(glint setCycle: End self)
			)
			(1
				(glint stopUpd:)
				(= seconds 10)
			)
			(2
				(self init:)
			)
		)
	)
)

(instance snore of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGlobalAudio number: 7060 loop: 1 play: self)
				(zzzz setCycle: End self)
			)
			(1)
			(2
				(= seconds 2)
			)
			(3
				(self init:)
			)
		)
	)
)

(instance glint of Prop
	(properties
		x 136
		y 68
		view 390
		loop 3
		cycleSpeed 2
	)
)

(instance staff of View
	(properties
		x 140
		y 105
		view 390
		loop 2
		signal 16385
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
					(Say 323)
					(event claimed: 1)
				)
				(3 ; Do
					(HandsOff)
					(gCurRoom setScript: getStaff)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance zzzz of Prop
	(properties
		x 100
		y 105
		view 390
		loop 4
		cycleSpeed 3
		detailLevel 3
	)

	(method (setScript)
		(if (== (gGame detailLevel:) 3)
			(super setScript: &rest)
		)
	)
)

(instance bandit of Actor
	(properties
		x 118
		y 120
		view 390
		detailLevel 3
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
					(Say 324)
					(event claimed: 1)
				)
				(3 ; Do
					(if (not local1)
						(++ local1)
						(Say 320)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance tent of RFeature
	(properties
		nsTop 39
		nsLeft 65
		nsBottom 151
		nsRight 253
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
					(if (gCast contains: bandit)
						(Say 321)
					else
						(Say 322)
					)
					(event claimed: 1)
				)
				(5 ; Talk
					(if (gCast contains: bandit)
						(Say 9104)
						(gCurRoom setScript: wakeUp)
						(event claimed: 1)
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

