;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 8)
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
	rm008 0
)

(local
	local0
	[local1 10] = [0 161 173 159 174 170 95 189 0 189]
	[local11 28] = [0 0 139 0 139 93 106 104 88 96 49 93 44 102 88 102 82 108 30 108 22 114 82 115 82 123 0 128]
	[local39 12] = [319 130 187 130 187 109 175 101 175 0 319 0]
	[local51 8] = [182 109 184 122 85 122 85 114]
	[local59 14] = [319 175 269 174 257 163 218 157 217 152 253 146 319 146]
	[local73 8] = [1 3037 0 7017 1 3038 0 0]
	[local81 6] = [1 3039 0 7018 0 0]
	[local87 6] = [1 3040 0 7019 0 0]
	[local93 9] = [1000 110 40 4 11 24 19 23 30]
	[local102 9] = [1003 160 50 4 11 25 23 31 31]
	[local111 9] = [1003 70 67 4 11 25 23 31 31]
)

(instance rm008 of KQ5Room
	(properties
		picture 8
		east 1
		south 7
		west 9
	)

	(method (init)
		(super init:)
		(= global320 123)
		(= global321 75)
		(self setFeatures: hole signPost path8 forest setRegions: 202) ; owl
		(gGlobalSound number: 24 loop: -1 play:)
		(if (IsFlag 61)
			(log cel: 5 setPri: 8)
		else
			(log setPri: 7)
		)
		(log init:)
		(if (!= ((gInventory at: 6) owner:) 200) ; Brass_Bottle
			(gGlobalSound2 number: 31 loop: 1 play:)
		)
		(= global325 3035)
		(switch gPrevRoomNum
			(west
				(gEgo posn: 10 148)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(east
				(gEgo
					illegalBits: $8000
					posn:
						(if (< (gEgo y:) 165) 284 else 316)
						(if (< (gEgo y:) 165) 144 else 180)
				)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(south
				(gEgo posn: 220 188)
				(self setScript: (ScriptID 202 1)) ; stdWalkIn
			)
			(215
				(gEgo
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((gEgo head:) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(81
				(gEgo
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((gEgo head:) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(82
				(gEgo
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((gEgo head:) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(83
				(gEgo
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((gEgo head:) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(84
				(gEgo
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((gEgo head:) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(19
				(gEgo posn: 171 101)
				(HandsOff)
				(self setScript: from19Script)
			)
			(else
				(gEgo posn: 220 188)
			)
		)
		(if
			(and
				(not (== gPrevRoomNum 215))
				(not (== gPrevRoomNum 81))
				(not (== gPrevRoomNum 82))
				(not (== gPrevRoomNum 83))
				(not (== gPrevRoomNum 84))
			)
			(gEgo init: view: 0 setStep: 3 2)
		)
		(poly1 points: @local1 size: 5)
		(poly2 points: @local11 size: 14)
		(poly3 points: @local39 size: 6)
		(poly4 points: @local51 size: 4)
		(poly5 points: @local59 size: 7)
		(self addObstacle: poly1 poly2 poly3 poly4 poly5)
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
				((ScriptID 202 2) register: (gEgo edgeHit:)) ; stdWalkOut
				(self setScript: (ScriptID 202 2)) ; stdWalkOut
			)
			((and (& (= temp1 (gEgo onControl: 0)) $0010) (IsFlag 61))
				(HandsOff)
				(self setScript: enterTunnel)
			)
			((& temp1 $4000)
				(if (not (IsFlag 17))
					(SetFlag 17)
					(HandsOff)
					(self setScript: enter19)
				else
					(gGlobalSound fade:)
					(gCurRoom newRoom: 19)
				)
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

	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
		(gGlobalSound2 fade:)
	)

	(method (dispose)
		(super dispose:)
	)
)

(instance enter19 of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: 0)
				(proc762_0 @local102 @local93 @local73 self)
			)
			(1
				(gEgo cel: 2)
				(= cycles 1)
			)
			(2
				(gEgo cel: 3)
				(= cycles 1)
			)
			(3
				(gGlobalSound fade:)
				(gCurRoom newRoom: 19)
			)
		)
	)
)

(instance from19Script of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 15)
			)
			(1
				(proc762_1 @local93 3036 self)
			)
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance enterTunnel of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 59 100 self)
			)
			(1
				((gEgo head:) hide:)
				(gEgo
					normal: 0
					view: 239
					loop: 1
					signal: 16384
					illegalBits: 0
					cel: 7
					posn: 51 105
					setCycle: Beg self
				)
			)
			(2
				(gCurRoom newRoom: 215)
			)
		)
	)
)

(instance backOnPath of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 3)
			)
			(1
				(if (not (IsFlag 61))
					(log startUpd: setCycle: End self)
					(gGlobalAudio number: 8878 loop: 1 play:)
				else
					(= cycles 1)
				)
			)
			(2
				(gEgo normal: 0 view: 239 cel: 0 loop: 1 setCycle: End self)
			)
			(3
				(gEgo
					view: 0
					normal: 1
					posn: 59 102
					setCycle: KQ5SyncWalk
					setMotion: PolyPath 84 134 self
				)
				((gEgo head:) show:)
			)
			(4
				(gEgo setMotion: 0)
				(= cycles 1)
			)
			(5
				(gEgo cel: 3)
				(log stopUpd:)
				(if (not (IsFlag 61))
					(SetFlag 61)
					(log setPri: 8)
					(proc762_0 @local111 @local93 @local81 self)
				else
					(= cycles 1)
				)
			)
			(6
				(gEgo setMotion: 0 illegalBits: $8000)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance path8 of RFeature
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
					(Say 227)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance signPost of RFeature
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
					(Say 228)
					(event claimed: 1)
				)
				(3 ; Do
					(if (== ((gInventory at: 6) owner:) 200) ; Brass_Bottle
						(proc762_0 @local102 @local93 @local87)
					else
						(proc762_1 @local93 3040)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance forest of RFeature
	(properties
		nsBottom 95
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
					(Say 229)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance log of Prop
	(properties
		x 52
		y 109
		view 239
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
					(Say 230)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 231)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hole of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(and
					(not (& (OnControl CONTROL (event x:) (event y:)) $0010))
					(not (& (OnControl CONTROL (event x:) (event y:)) $2000))
				)
				(not (IsFlag 61))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 232)
					(event claimed: 1)
				)
				(3 ; Do
					(HandsOff)
					(gCurRoom setScript: enterTunnel)
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

