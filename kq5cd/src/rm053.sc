;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 53)
(include sci.sh)
(use Main)
(use Interface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use System)

(public
	rm053 0
)

(local
	local0
	[local1 2]
	[local3 16] = [0 189 0 0 43 0 43 136 59 153 77 163 128 168 76 189]
	[local19 20] = [137 183 115 183 136 166 117 162 111 156 86 156 61 148 62 0 164 0 169 153]
	[local39 9] = [1000 125 54 4 11 24 19 23 30]
	[local48 9] = [1003 122 112 4 11 25 23 31 31]
	[local57 6] = [0 3085 1 7032 0 0]
)

(instance rm053 of KQ5Room
	(properties
		picture 53
		horizon 145
		north 54
		south 52
	)

	(method (init)
		(super init:)
		(gGlobalSound number: 834 loop: -1 play:)
		(if (not (IsFlag 55))
			(= global320 159)
			(= global321 109)
			(self setRegions: 202) ; owl
			(= global325 3083)
		)
		(self
			setFeatures: chasm stairWay aCastle path1 path2 path3
			obstacles: polyListTop
		)
		(gEgo init:)
		(switch gPrevRoomNum
			(north
				(gEgo view: 2 observeControl: 16384 posn: 57 146)
				(= local0 1)
				(if (not (IsFlag 55))
					(global322
						view: 138
						loop: 2
						cel: 0
						cycleSpeed: 3
						setCycle: End
					)
				)
			)
			(else
				(gEgo
					view: 0
					ignoreControl: 16384
					setPri: 15
					setStep: 3 2
					posn: 156 185
				)
				(= local0 0)
				(walkInCode doit:)
			)
		)
		(poly1 points: @local3 size: 8)
		(poly2 points: @local19 size: 10)
		(polyListTop add: poly1 poly2)
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
				(if (not (IsFlag 55))
					((ScriptID 202 2) register: (gEgo edgeHit:)) ; stdWalkOut
					(self setScript: (ScriptID 202 2)) ; stdWalkOut
				else
					(gCurRoom newRoom: temp0)
				)
			)
			(
				(and
					(& (= temp1 (gEgo onControl: 0)) $0080)
					(!= (gEgo view:) 9)
				)
				(self setScript: goingUp)
			)
			((and (& temp1 $0100) (!= (gEgo view:) 9))
				(self setScript: goingDown)
			)
			((& temp1 $0400)
				(gEgo
					priority: 15
					signal: (| (gEgo signal:) $0010)
					ignoreControl: 16384
				)
				(= local0 0)
			)
			((and (& temp1 $2000) (== local0 1))
				(HandsOff)
				(self setScript: fallBottom)
			)
			((& temp1 $0002)
				(= local0 1)
				(gEgo
					priority: -1
					signal: (& (gEgo signal:) $ffef)
					observeControl: 16384
				)
			)
			((and (& temp1 $1000) (== local0 0))
				(HandsOff)
				(self setScript: fallTop)
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
		(polyListTop dispose:)
		(gEgo ignoreControl: 16384)
		(super dispose:)
	)

	(method (newRoom newRoomNumber)
		(if (== newRoomNumber 52)
			(gGlobalSound fade:)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance fallTop of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 68)
				(if (not (IsFlag 55))
					(proc762_1 @local39 3032 self)
				else
					(= cycles 1)
				)
			)
			(1
				(gGlobalAudio number: 7053 loop: 1 play:)
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 68
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: End self
					setPri: 13
					illegalBits: 0
				)
			)
			(2
				(gEgo yStep: 8 setMotion: MoveTo (gEgo x:) 230 self)
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

(instance fallBottom of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 70)
				(if (not (IsFlag 55))
					(proc762_1 @local39 3032 self)
				else
					(= cycles 1)
				)
			)
			(1
				(gGlobalAudio number: 7053 loop: 1 play:)
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 70
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: End self
					setPri: 13
					illegalBits: 0
				)
			)
			(2
				(gEgo yStep: 8 setMotion: MoveTo (gEgo x:) 230 self)
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

(instance goingDown of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 9
					setLoop: 1
					cel: 0
					setCycle: End self
					setMotion: PolyPath 131 164 self
				)
			)
			(1
				((gEgo head:) show:)
				(gEgo
					normal: 1
					view: 2
					setLoop: -1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 123 166 self
				)
			)
			(2
				(gEgo signal: 8194 setMotion: MoveTo 103 160 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance goingUp of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gEgo setMotion: PolyPath 123 169 self)
			)
			(1
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 9
					setLoop: 0
					cel: 0
					setCycle: End self
					setMotion: PolyPath 101 188 self
				)
			)
			(2
				((gEgo head:) show:)
				(gEgo
					normal: 1
					view: 0
					setLoop: -1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 98 186 self
				)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance conversation of Script
	(properties)

	(method (doit)
		(if (== (gCurRoom script:) fallTop)
			(self dispose:)
		else
			(super doit: &rest)
		)
	)

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 6)
				(SetFlag 98)
			)
			(1
				(proc762_0 @local39 @local48 @local57 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance chasm of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0004))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 562)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance stairWay of RFeature
	(properties
		nsTop 168
		nsLeft 90
		nsBottom 181
		nsRight 124
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
					(Say 563)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance aCastle of RFeature
	(properties
		nsLeft 77
		nsBottom 124
		nsRight 319
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
					(Say 564)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path1 of RFeature
	(properties
		nsTop 141
		nsLeft 44
		nsBottom 166
		nsRight 138
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
					(Say 563)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path2 of RFeature
	(properties
		nsTop 150
		nsLeft 157
		nsBottom 189
		nsRight 319
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
					(Say 562)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path3 of RFeature
	(properties
		nsTop 114
		nsLeft 168
		nsBottom 131
		nsRight 273
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
					(Say 562)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance polyListTop of List
	(properties)
)

(instance poly1 of Polygon
	(properties
		type PTotalAccess
	)
)

(instance poly2 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance walkInCode of CodeCue
	(properties)

	(method (cue)
		(if (not (IsFlag 98))
			(gCurRoom setScript: conversation)
		)
	)

	(method (doit)
		(if (not (IsFlag 55))
			(gCurRoom setScript: (ScriptID 202 1) self) ; stdWalkIn
		)
	)
)

